package com.usst.myblog.controller;


import com.usst.myblog.mapper.TBlogMapper;
import com.usst.myblog.mapper.TTagMapper;
import com.usst.myblog.mapper.TTypeMapper;
import com.usst.myblog.pojo.*;
import com.usst.myblog.service.TBlogService;
import com.usst.myblog.service.TCommentService;
import com.usst.myblog.service.TTagService;
import com.usst.myblog.service.TTypeService;
import com.usst.myblog.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Controller
//@RequestMapping("/blog")
public class TBlogController {

    @Autowired
    TBlogService blogService;
    @Autowired
    TTagService tagService;
    @Autowired
    TTypeService typeService;

    @RequestMapping("/")
    public String prepareWork(Model model){
        List<TBlog> blogs = blogService.findByPage(0,6);
        List<TType> allType = blogService.findAllType();
        List<TTag> allTag = blogService.findAllTag();
        int count1 = blogService.findCount();
        Map<String,Object> map = new HashMap<>();
        //获取推荐的blog
        List<TBlog> recommendBlogs = blogService.findRecommendBlog();

        map.put("recommendBlogs",recommendBlogs);

        map.put("blogs",blogs);
        map.put("types",allType);
        map.put("tags",allTag);
        map.put("blogCount",count1);
        map.put("from",0);
        map.put("count",6);
        model.addAllAttributes(map);
        return "index";
    }

    @RequestMapping("/about")
    public String test1(){
        return "about";
    }


    @Autowired
    TCommentService commentService;

    /**
     * 点击后直接调转到blog展示页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/showblog/{id}")
    public String showBlog(@PathVariable("id") Long id , Model model){
        blogService.addView(id);//增加浏览人数
        TBlog blogByLongId = blogService.findBlogByLongId(id);
        TBlog blog = new TBlog();
        BeanUtils.copyProperties(blogByLongId,blog);
        String content = blog.getContent();
        String s = MarkdownUtils.markdownToHtmlExtensions(content);
        blog.setContent(s);
//        System.out.println("s--------------------->"+s);
        model.addAttribute("blog",blog);
        //获取该博客的标签
        List<TTag> tags = blogService.findBlogTag(id);
        model.addAttribute("tags",tags);
        //获取评论信息
        List<TComment> comments = commentService.findComment(id);
        if (comments.size()==0){
            model.addAttribute("comments",0);
        }else
        {
            model.addAttribute("comments",comments);
        }

        return "blog";
    }

    /**
     * 搜索功能
     * @param query
     * @param model
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam String query,Model model){
        //查找到符合条件的blog
        List<TBlog> blogs = blogService.findSearchBlog(query);
//        System.out.println("query------------->"+blogs);
        //查找到总条数
        int blogcount = blogService.findSearchBlogCount(query);
//        System.out.println("count------------->"+blogcount);
        model.addAttribute("blogs",blogs);
        model.addAttribute("count",blogcount);
        return "search";
    }

    @RequestMapping("/prepage/{from}/{count}")
    public String prePage(@PathVariable int from ,@PathVariable int count,Model model){

        List<TBlog> blogs = blogService.findByPage(from,count);
        List<TType> allType = blogService.findAllType();
        List<TTag> allTag = blogService.findAllTag();
        int count1 = blogService.findCount();
        Map<String,Object> map = new HashMap<>();
        //获取推荐的blog
        List<TBlog> recommendBlogs = blogService.findRecommendBlog();
        map.put("recommendBlogs",recommendBlogs);
        map.put("blogs",blogs);
        map.put("types",allType);
        map.put("tags",allTag);
        map.put("blogCount",count1);
        map.put("from",from);
        map.put("count",count);
        model.addAllAttributes(map);
        return "index";
    }

    @RequestMapping("/nextpage/{from}/{count}")
    public String nextPage(@PathVariable int from ,@PathVariable int count,Model model){

        List<TBlog> blogs = blogService.findByPage(from,count);
        List<TType> allType = blogService.findAllType();
        List<TTag> allTag = blogService.findAllTag();
        int count1 = blogService.findCount();
        Map<String,Object> map = new HashMap<>();
        //获取推荐的blog
        List<TBlog> recommendBlogs = blogService.findRecommendBlog();
        map.put("recommendBlogs",recommendBlogs);
        map.put("blogs",blogs);
        map.put("types",allType);
        map.put("tags",allTag);
        map.put("blogCount",count1);
        map.put("from",from);
        map.put("count",count);
        model.addAllAttributes(map);
        return "index";
    }

    @RequestMapping("/toabout")
    public String toAbout(){

        return "about";
    }

    @RequestMapping("/toarchives")
    public String toarchives(Model model) throws ParseException {

        //查找到所有blog
        List<TBlog> all = blogService.findAll();
        model.addAttribute("blogs",all);
        Map<Long,String> map = new HashMap<>();
        for (TBlog blog : all) {
            String[] s2 = blog.getCreateTime().toString().split(" ");
            String s = s2[5];
            map.put(blog.getId(),s);
//            System.out.println(s);
        }
        model.addAttribute("map",map);
        return "archives";
    }
}


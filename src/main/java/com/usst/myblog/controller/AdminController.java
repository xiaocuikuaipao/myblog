package com.usst.myblog.controller;

import com.usst.myblog.pojo.TBlog;
import com.usst.myblog.pojo.TTag;
import com.usst.myblog.pojo.TType;
import com.usst.myblog.service.TBlogService;
import com.usst.myblog.service.TTagService;
import com.usst.myblog.service.TTypeService;
import com.usst.myblog.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    UserService userService;
    @Autowired
    TBlogService blogService;
    @Autowired
    TTypeService typeService;
    @Autowired
    TTagService tagService;

    /**
     * 跳转到管理员登录页
     * @return
     */
    @RequestMapping({"/",""})
    public String toAdmin(){
        return "admin/login";
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model){

        int flag=userService.checkUserByNamePassword(username,password);
        if (flag==1){
            session.setAttribute("user",username);
            return "/admin/index";
        }else {
            model.addAttribute("msg","用户名或密码错误");
        }
        return "/admin/login";
    }

    /**
     * 跳转到博客页面
     * @return
     */
    @RequestMapping("/toblog/{from}/{count}")
    public String toBlog(Model model,
                         @PathVariable("from") int from,
                         @PathVariable("count") int count){

//        System.out.println(from);
//        System.out.println(count);
//        List<TBlog> blogs= blogService.findAll();
        List<TBlog> blogs=blogService.findByPage(from,count);
        int num = blogService.findCount();
        System.out.println("zongtioashu--------->"+num);
        List<TType> allType = blogService.findAllType();
        model.addAttribute("types",allType);
        model.addAttribute("num",num);
        model.addAttribute("blogs",blogs);
        model.addAttribute("from",from);
        model.addAttribute("count",count);
        return "/admin/blogs";
    }

    /**
     * 跳转到编辑blog
     * @return
     */
    @RequestMapping("/editblog/{id}")
    public String editBlog1(@PathVariable("id") int id,Model model){

        TBlog blog = blogService.findBlogById(id);
        List<TType> types = blogService.findAllType();
        List<TTag> tags = blogService.findAllTag();
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        if (blog==null){
            model.addAttribute("msg","此条博客还未发布！");
            TBlog blog1 = blogService.findNotPublishedBlogById(id);
            model.addAttribute("blog",blog1);
        }else {
            model.addAttribute("blog",blog);
        }
//        System.out.println(blog.getFlag());
//        System.out.println(id);
        return "/admin/editBlog";
//        return "/admin/input";
    }

    /**
     * 编辑博客
     * @param published
     * @param blog
     * @param id
     * @return
     */
    @PostMapping("/editblog/{published}/{id}")
    public String hitedit(@PathVariable int published,TBlog blog,@PathVariable("id") Long id){


//        return "/admin/blogs";
        if (blog.getRecommend()==null){
            blog.setRecommend(0);
        }else{
            blog.setRecommend(1);
        }
        if (blog.getAppreciation()==null){
            blog.setAppreciation(0);
        }else {
            blog.setAppreciation(1);
        }
        if (blog.getCommentabled()==null){
            blog.setCommentabled(0);
        }else{
            blog.setCommentabled(1);
        }if (blog.getShareStatement()==null){
            blog.setShareStatement(0);
        }else {
            blog.setShareStatement(1);
        }
        if (published==1){
            blog.setPublished(1);
        }else{
            blog.setPublished(0);
        }
        blog.setId(id);
        System.out.println("blog--------------->"+blog);
        blogService.updateBlog(blog);
        return "redirect:/admin/toblog/0/5";
    }


    /**
     * 删除博客
     * @param id
     * @return
     */
    @RequestMapping("/delblog/{id}")
    public String delBlog(@PathVariable("id") int id){

        int tag = blogService.delBlogById(id);
        return "redirect:/admin/toblog/0/5";
    }

    /**
     * 跳转到新增博客页面
     */
    @RequestMapping("/toaddblog")
    public String toAddBlog(Model model){

        List<TType> types = blogService.findAllType();
        List<TTag> tags = blogService.findAllTag();
        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        return "/admin/blogs-input";
    }

    /**
     * 添加blog
     * @return
     */
    @RequestMapping("/addblog/{published}")
    public String addBlog(@Param("recommend2") String recommend,TBlog blog,
                          @PathVariable("published") int publish){

//        System.out.println(publish);

//        TBlog blog1 = new TBlog();
        if (publish==1){
            blog.setPublished(1);
        }else{
            blog.setPublished(0);
        }
        if ("on".equals(recommend)){
            blog.setRecommend(1);
        }else{
            blog.setRecommend(0);
        }
        blog.setViews(0);
//        blogService.updateBlog(blog)
        int tag = blogService.addBlog(blog);
//        System.out.println("----------------------------------------->"+tag);
        return "redirect:/admin/toblog/0/5";
    }

    /**
     * 搜索
     * @param title
     * @param typeId
     * @param recommend
     * @param model
     * @param from
     * @param count
     * @return
     */
    @RequestMapping("/search/{cur}/{size}")
    public String search(@Param("title") String title,
                         @Param("typeId") Integer typeId,
                         @Param("recommend") String recommend,
                         Model model,
                         @PathVariable("cur") int from,
                         @PathVariable("size") int count){


        System.out.println("------------>"+typeId+recommend);
        List<TBlog> blogs = blogService.findBlogBySearchByPage(from,count,title,typeId,recommend);
//        List<TBlog> blogs=blogService.findByPage(from,count);
//        int num = blogService.findCount();
        List<TType> allType = blogService.findAllType();
        int num = blogService.findSearchCount(title,typeId,recommend);
        model.addAttribute("types",allType);
        model.addAttribute("num",num);
        model.addAttribute("blogs",blogs);
        model.addAttribute("from",from);
        model.addAttribute("count",count);
        return "/admin/blogs";

    }

    /**
     * 注销用户
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin/";
    }

    /**
     * 跳转到type页面
     * @param model
     * @return
     */
    @RequestMapping("/totype")
    public String toTypePage(Model model){

        List<TType> allType = blogService.findAllType();
        model.addAttribute("types",allType);
        return "/admin/types";
    }


    /**
     * 添加类型
     * @param typename
     * @param model
     * @return
     */
    @RequestMapping("/toaddtype")
    public String toaddtype(@Param("typename") String typename,Model model){

//        System.out.println("ceshi------->"+typename);

        int tag = typeService.addtype(typename);
//        List<TType> allType = blogService.findAllType();
//        model.addAttribute("types",allType);
        return "redirect:/admin/totype";
    }

    /**
     * 跳转到编辑type页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toedittype/{id}")
    public String toeditType(@PathVariable("id") Integer id,Model model){

        TType type = typeService.findTypeById(id);
        model.addAttribute("type",type);
        return "/admin/editType";
    }

    /**
     * 修改指定的type
     * @param id
     * @param typename
     * @return
     */
    @RequestMapping("/edittype/{id}")
    public String editType(@PathVariable("id") Long id,
                           @Param("typename") String typename){

        int tag = typeService.editTypeById(id,typename);
        return "redirect:/admin/totype";
    }

    /**
     * 删除type
     * @param id
     * @return
     */
    @RequestMapping("/deltype/{id}")
    public String delType(@PathVariable("id") Long id){
        int tag = typeService.delTypeById(id);
        return "redirect:/admin/totype";
    }

    /**
     * 跳转到tags页面
     * @param model
     * @return
     */
    @RequestMapping("/totag")
    public String toTag(Model model){
        List<TTag> allTag = blogService.findAllTag();
        model.addAttribute("tags",allTag);
        return "/admin/tags";
    }

    /**
     * 添加tag
     * @param tagname
     * @return
     */
    @RequestMapping("/toaddtag")
    public String toAddTag(@Param("tagname")String tagname){
        int tag = tagService.addtag(tagname);
        return "redirect:/admin/totag";
    }

    /**
     * 跳转到edittag
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toedittag/{id}")
    public String editTag(@PathVariable("id") Long id,Model model){

        TTag tag = tagService.findTagById(id);
        model.addAttribute("tag",tag);
        return "/admin/editTag";
    }

    @RequestMapping("/edittag/{id}")
    public String editTag(@PathVariable("id")Long id,@Param("tagname")String tagname){
        tagService.updataTag(id,tagname);
        return "redirect:/admin/totag";
    }

    @RequestMapping("/deltag/{id}")
    public String delTag(@PathVariable("id") Long id){
        tagService.delTag(id);
        return "redirect:/admin/totag";

    }
}

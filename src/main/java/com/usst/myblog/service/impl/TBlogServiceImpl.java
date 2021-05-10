package com.usst.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.usst.myblog.mapper.TBlogMapper;
import com.usst.myblog.mapper.TTagMapper;
import com.usst.myblog.mapper.TTypeMapper;
import com.usst.myblog.pojo.BlogNumByType;
import com.usst.myblog.pojo.TBlog;
import com.usst.myblog.pojo.TTag;
import com.usst.myblog.pojo.TType;
import com.usst.myblog.service.TBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Service
public class TBlogServiceImpl  implements TBlogService {

    @Autowired
    TBlogMapper mapper;

    /**
     * 查询所有blogs
     * @return
     */
    @Override
    public List<TBlog> findAll() {
        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
        wrapper.eq("published",1);
        List<TBlog> tBlogs = mapper.selectList(null);
        return tBlogs;
    }

    /**
     * 分页查询博客
     * @param from
     * @param count
     * @return
     */
    @Override
    public List<TBlog> findByPage(int from, int count) {
//        Page<TBlog> page = new Page<>(5,2);//返回总记录数
////        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
////        wrapper.orderByAsc("id");
//        System.out.println("------>"+from);
//        System.out.println("------>"+count);
//        Page<TBlog> page1 = mapper.selectPage(page, null);

//        System.out.println(page1.getTotal());

        List<TBlog> blogs=mapper.selectByPage(from,count);

        return blogs;
    }

    /**
     * 查询博客数量
     * @return
     */
    @Override
    public int findCount() {
//        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
//        wrapper.eq("published",1);
        return mapper.selectCount(null);
    }

    /**
     * 根据id查询blog
     * @param id
     * @return
     */
    @Override
    public TBlog findBlogById(int id) {
//        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
//        wrapper.eq("published",1);
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("published",1);
//        TBlog blog = (TBlog) mapper.selectByMap(map);
        TBlog blog = mapper.findBlogById(id);
        System.out.println("dao------->"+blog);
        return blog;
    }

    @Autowired
    TTypeMapper typeMapper;
    /**
     * 查询所有类型
     * @return
     */
    @Override
    public List<TType> findAllType() {

        List<TType> tTypes = typeMapper.selectList(null);
        return tTypes;
    }

    /**
     * 查询所有标签
     * @return
     */
    @Autowired
    TTagMapper tagMapper;
    @Override
    public List<TTag> findAllTag() {

        List<TTag> tTags = tagMapper.selectList(null);
        return tTags;
    }

    /**
     * 根据id删除blog
     * @param id
     * @return
     */
    @Override
    public int delBlogById(int id) {
        int i = mapper.deleteById(id);
        return i;
    }

    /**
     * 新增blog
     * @param blog
     * @return
     */
    @Override
    public int addBlog(TBlog blog) {
        int insert = mapper.insert(blog);
        return insert;
    }

    /**
     * 通过查找没有发布的blog
     * @param id
     * @return
     */
    @Override
    public TBlog findNotPublishedBlogById(int id) {
        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
        wrapper.eq("published",0).eq("id",id);
        return mapper.selectOne(wrapper);
    }

    /**
     * search
     *
     * @param
     * @param from
     * @param title
     * @param typeId
     * @param count
     * @return
     */
    @Override
    public List<TBlog> findBlogBySearchByPage(int from, int count, String title,
                                              int typeId, String recommend) {

        List<TBlog> blogs = mapper.searchByPage(from,count,title,typeId,recommend);
        return blogs;
    }

    @Override
    public int findSearchCount(String title, Integer typeId, String recommend) {
        int searchCount = mapper.findSearchCount(title, typeId, recommend);
        return searchCount;
    }

    /**
     * 增加观看人数
     * @param id
     * @return
     */
    @Override
    public int addView(Long id) {
        mapper.updateView(id);
        return 0;
    }

    @Override
    public TBlog findBlogByLongId(Long id) {
        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return mapper.selectOne(wrapper);
    }

    /**
     * 通过博客id找到对应tag的信息
     * @param id
     * @return
     */
    @Override
    public List<TTag> findBlogTag(Long id) {
        String a = mapper.selectTagsById(id);
        String s = "";
//        for (int i = 0; i < a.length; i++) {
//            if (i!=a.length-1){
//                s=s+a[i]+",";
//            }else {
//                s=s+a[i];
//            }
//        }
        String[] split = a.split(",");
        Map<String ,Object> map =new HashMap<>();
        map.put("split",split);

        List<TTag> list = mapper.findBlogTag(map);
        return list;
    }

    /**
     * 更新blog
     * @param blog
     * @return
     */
    @Override
    public int updateBlog(TBlog blog) {
        TBlog blog1 = mapper.selectById(blog.getId());
        blog1.setPublished(blog.getPublished()).setRecommend(blog.getRecommend()).setTypeId(blog.getTypeId()).
                setFirstPicture(blog.getFirstPicture()).setTitle(blog.getTitle()).setTagIds(blog.getTagIds()).
                setShareStatement(blog.getShareStatement()).setFlag(blog.getFlag()).setDescription(blog.getDescription());
        int i = mapper.updateById(blog1);
        return i;
    }

//    @Override
//    public BlogNumByType findBlogNumByType() {
//        BlogNumByType blogNumByType = mapper.findBlogNumByType();
//        return blogNumByType;
//    }

    /**
     * 寻找推荐的博客
     * @return
     */
    @Override
    public List<TBlog> findRecommendBlog() {
        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
        wrapper.eq("recommend",1).orderByAsc("create_time");
        List<TBlog> recommendBlog = mapper.selectList(wrapper);
        return recommendBlog;
    }

    /**
     * 搜索符合条件的blog
     * @param query
     * @return
     */
    @Override
    public List<TBlog> findSearchBlog(String query) {
//        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
//        QueryWrapper<TBlog> wrapper1 = new QueryWrapper<>();
//        wrapper1.orderByDesc("update_time").like("content",query);
//        QueryWrapper<TBlog> wrapper2 = new QueryWrapper<>();
//        wrapper1.orderByDesc("update_time").like("description",query);
//        wrapper.orderByDesc("update_time").like("title",query).or((Consumer<QueryWrapper<TBlog>>) wrapper1)
        return mapper.findSearchBlog(query);
    }

    @Override
    public int findSearchBlogCount(String query) {

        return mapper.findSearchBlogCount(query);
    }

    @Override
    public List<TBlog> findBlogByTypeId(Long activeId) {
        QueryWrapper<TBlog> wrapper = new QueryWrapper<>();
        wrapper.eq("type_id",activeId);
        List<TBlog> blogs = mapper.selectList(wrapper);
        return blogs;
    }

    @Override
    public List<TBlog> findBlogByTagId(Long activeId) {

        List<TBlog> blogs = mapper.findBlogByTagId(activeId);
        return blogs;
    }
}

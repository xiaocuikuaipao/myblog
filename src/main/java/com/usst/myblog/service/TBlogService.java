package com.usst.myblog.service;

import com.usst.myblog.pojo.*;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
public interface TBlogService  {

    List<TBlog> findAll();

    List<TBlog> findByPage(int from, int count);

    int findCount();

    TBlog findBlogById(int id);

    List<TType> findAllType();

    List<TTag> findAllTag();

    int delBlogById(int id);

    int addBlog(TBlog blog);

    TBlog findNotPublishedBlogById(int id);

    List<TBlog> findBlogBySearchByPage(int from, int count, String title, int typeId, String recommend);

    int findSearchCount(String title, Integer typeId, String recommend);

    int addView(Long id);

    TBlog findBlogByLongId(Long id);

    List<TTag> findBlogTag(Long id);

    int updateBlog(TBlog blog);

//    BlogNumByType findBlogNumByType();

    List<TBlog> findRecommendBlog();

    List<TBlog> findSearchBlog(String query);

    int findSearchBlogCount(String query);

    List<TBlog> findBlogByTypeId(Long activeId);

    List<TBlog> findBlogByTagId(Long activeId);

}

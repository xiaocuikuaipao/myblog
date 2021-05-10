package com.usst.myblog.mapper;

import com.usst.myblog.pojo.BlogNumByType;
import com.usst.myblog.pojo.TBlog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.usst.myblog.pojo.TTag;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Repository
public interface TBlogMapper extends BaseMapper<TBlog> {

    @Select("SELECT id,title,content,first_picture,flag,views,appreciation,share_statement,commentabled,published,recommend,create_time,update_time,type_id,user_id,description,tag_ids FROM t_blog ORDER BY id ASC LIMIT #{from},#{count}")
    List<TBlog> selectByPage(@Param("from") int from ,@Param("count") int count );

    @Select("select * from t_blog where id=#{id} and published=1")
    TBlog findBlogById(@Param("id") int id);

//    @Select("SELECT id,title,content,first_picture,flag,views,appreciation,share_statement,commentabled,published,recommend,create_time,update_time,type_id,user_id,description,tag_ids FROM t_blog ORDER BY id ASC LIMIT #{from},#{count}")
    List<TBlog> searchByPage(@Param("from") int from,@Param("count") int  count,
                             @Param("title") String title,@Param("typeId")int typeId,
                             @Param("recommend")String recommend);

    int findSearchCount(String title, Integer typeId, String recommend);

    @Update("update t_blog set views = views + 1 where id =#{id}")
    int updateView(Long id);

//    @Select("select * from t_tag as t where t.id in ("") ")
    List<TTag> findBlogTag(Map map);

    @Select("select tag_ids from t_blog where id = #{id}")
    String selectTagsById(Long id);


//    @Select("select * from t_blog where (published=1) and title like '%${query}%' ")
            @Select("select * from t_blog where (published=1) and (title like '%${query}%' "+
            "or content like '%${query}%' or description like '%${query}%') order by  update_time desc")
    List<TBlog> findSearchBlog(String query);

    @Select("select count(id) from t_blog where (published=1) and (title like '%${query}%' "+
            "or content like '%${query}%' or description like '%${query}%')")
    int findSearchBlogCount(String query);

    @Select("select * from t_blog where (published=1) and tag_ids like '%${activeId}%' ")
    List<TBlog> findBlogByTagId(Long activeId);

//    @Select("select count(*) as count,type_id as id from t_blog as b group by b.type_id ")
//    BlogNumByType findBlogNumByType();
}

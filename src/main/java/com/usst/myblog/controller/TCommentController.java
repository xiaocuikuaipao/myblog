package com.usst.myblog.controller;


import com.usst.myblog.mapper.TCommentMapper;
import com.usst.myblog.pojo.TComment;
import com.usst.myblog.service.TCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Controller
//@RequestMapping("/myblog/tComment")
public class TCommentController {

    @Autowired
    TCommentService commentService;
    @RequestMapping("/comment/{blogId}")
    public String comment(@PathVariable("blogId") Long id, TComment comment){

        comment.setBlogId(id);
        commentService.addComment(comment);

        return "redirect:/showblog/"+id;
    }
}


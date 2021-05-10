package com.usst.myblog.controller;


import com.usst.myblog.pojo.TBlog;
import com.usst.myblog.pojo.TTag;
import com.usst.myblog.pojo.TType;
import com.usst.myblog.service.TBlogService;
import com.usst.myblog.service.TTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 小崔
 * @since 2021-04-03
 */
@Controller
//@RequestMapping("/myblog/tTag")
public class TTagController {

    @Autowired
    TTypeService typeService;
    @Autowired
    TBlogService blogService;

    @RequestMapping("/totag/{activeId}")
    public String toTags(@PathVariable Long activeId, Model model){

        List<TTag> tags = blogService.findAllTag();
        model.addAttribute("tags",tags);
        if (activeId!=-1){
            List<TBlog> blogs = blogService.findBlogByTagId(activeId);
            model.addAttribute("blogs",blogs);
            model.addAttribute("activeId",activeId);
        }else
        {
//            model.addAttribute("blogs","");
            model.addAttribute("activeId",-1);
        }
        return "tags";
    }

    @RequestMapping("/findtag/{activeId}")
    public String findTags(@PathVariable Long activeId){

        return "redirect:/totag/"+activeId;
    }

}


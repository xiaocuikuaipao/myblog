package com.usst.myblog.controller;


import com.usst.myblog.mapper.TTypeMapper;
import com.usst.myblog.pojo.TBlog;
import com.usst.myblog.pojo.TType;
import com.usst.myblog.service.TBlogService;
import com.usst.myblog.service.TTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
public class TTypeController {

    @Autowired
    TTypeService typeService;
    @Autowired
    TBlogService blogService;

    @RequestMapping("/totype/{activeId}")
    public String totype(@PathVariable("activeId") Long activeId, Model model){
        List<TType> types = blogService.findAllType();
        model.addAttribute("types",types);
        if (activeId!=-1){
            List<TBlog> blogs = blogService.findBlogByTypeId(activeId);
            model.addAttribute("blogs",blogs);
            model.addAttribute("activeId",activeId);
        }else
        {
//            model.addAttribute("blogs","");
            model.addAttribute("activeId",-1);
        }
        return "types";
    }

    @RequestMapping("/findtype/{id}")
    public String findType(@PathVariable Long id, Model model){

        //找到对应类型的blog
//        List<TBlog> blogs = blogService.findBlogByTypeId(id);
//        List<TType> types = blogService.findAllType();
//        model.addAttribute("types",types);
//        model.addAttribute("blogs",blogs);
        return "redirect:/totype/"+id;

    }
}


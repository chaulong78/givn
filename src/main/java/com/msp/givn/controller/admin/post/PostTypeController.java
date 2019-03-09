package com.msp.givn.controller.admin.post;

import com.msp.givn.entity.PostType;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.utility.StringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/event/post/type")
public class PostTypeController {

    @Autowired
    private PostTypeService typeService;

    @GetMapping(value = "")
    public ModelAndView showTypeTable() {
        ModelAndView modelAndView = new ModelAndView("admin/post-type/table-type");
        List<PostType> typeList = typeService.findAll();

        modelAndView.addObject("typeList", typeList);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView showAddForm() {
        ModelAndView modelAndView = new ModelAndView("admin/post-type/add-type");
        PostType postType = new PostType();

        modelAndView.addObject("type", postType);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView processCreateCourse(@ModelAttribute("type") PostType type, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/post/type");

        if (!result.hasErrors()) {
            PostType existType = typeService.findByName(type.getName());
            if (existType != null) {
                modelAndView.setViewName("redirect:/admin/event/post/type/add");
                redirectAttributes.addFlashAttribute("message", "Tên thể loại đã tồn tại");
            } else {
                type.setUrlName("/danh-muc-tin/" +StringFunction.convertNameToUrl(type.getName()));
                typeService.save(type);
                redirectAttributes.addFlashAttribute("message", "Tạo thể loại bài viết mới thành công");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Tạo thể loại bài viết mới thất bại");
        }

        return modelAndView;
    }
}

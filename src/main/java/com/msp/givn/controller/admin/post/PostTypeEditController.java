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

@Controller
@RequestMapping("/admin/event/post/type")
public class PostTypeEditController {

    @Autowired
    private PostTypeService typeService;

    @GetMapping(value = "/edit")
    public ModelAndView showEditForm(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/post-type/edit-type");

        if (id != null) {
            PostType postType = typeService.findById(id);
            if (postType != null) {
                modelAndView.addObject("type", postType);
                return modelAndView;
            }
        }

        redirectAttributes.addFlashAttribute("message", "Thể loại không tồn tại");
        modelAndView.setViewName("redirect:/admin/event/post/type");
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView processEditType(@ModelAttribute("type") PostType type, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/post/type");

        if (!result.hasErrors()) {
            if (type.getId() != 0) {
                type.setUrlName("/danh-muc-tin/" + StringFunction.convertNameToUrl(type.getName()));
                typeService.save(type);
                redirectAttributes.addFlashAttribute("message", "Cập nhật thể loại thành công");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Cập nhật thể loại thất bại");
        }

        return modelAndView;
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteCourseType(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttribute) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/event/post/type");

        if (id != null) {
            if (typeService.findById(id) != null) {
//                if (id == 1) {
//                    redirectAttribute.addFlashAttribute("message", "Không thể xóa thể loại mặc định này");
//                    return modelAndView;
//                }
//
//                List<Post> postList = postService.findByTypeId(id);
//                if (postList != null) {
//                    for (Post p : postList) {
//                        p.setTypeId(1);
//                    }
//                    postService.updateList(postList);
//                }

                typeService.deleteById(id);
                redirectAttribute.addFlashAttribute("message", "Xóa thể loại thành công");
                return modelAndView;
            }
        }

        redirectAttribute.addFlashAttribute("message", "Thể loại không tồn tại");
        return modelAndView;
    }
}

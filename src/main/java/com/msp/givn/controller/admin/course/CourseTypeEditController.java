package com.msp.givn.controller.admin.course;

import com.msp.givn.entity.CourseType;
import com.msp.givn.service.course.CourseService;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.utility.StringFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/course/type")
public class CourseTypeEditController {

    @Autowired
    private CourseTypeService typeService;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/edit")
    public ModelAndView showEditForm(@RequestParam(value = "id", required = false) Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/course-type/edit-type");

        if (id != null) {
            CourseType courseType = typeService.findById(id);
            if (courseType != null) {
                modelAndView.addObject("type", courseType);
                return modelAndView;
            }
        }

        redirectAttributes.addFlashAttribute("message", "Thể loại không tồn tại");
        modelAndView.setViewName("redirect:/admin/course/type");
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView processEditType(@ModelAttribute("type") CourseType type, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/course/type");

        if (!result.hasErrors()) {
            if (String.valueOf(type.getId()) != null && type.getId() != 0) {
                type.setUrlName("/danh-muc/" + StringFunction.convertNameToUrl(type.getName()));
                typeService.save(type);
                redirectAttributes.addFlashAttribute("message", "Cập nhật thể loại thành công");
                return modelAndView;
            }
        }

        redirectAttributes.addFlashAttribute("message", "Cập nhật thể loại thất bại");
        return modelAndView;
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteCourseType(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttribute) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/course/type");

        if (id != null) {
            if (typeService.findById(id) != null) {
//                if (id == 1) {
//                    redirectAttribute.addFlashAttribute("message", "Không thể xóa thể loại mặc định này");
//                    return modelAndView;
//                }
//                courseService.updateTypeForCourse(id);
                typeService.deleteById(id);
                redirectAttribute.addFlashAttribute("message", "Xóa thể loại thành công");
                return modelAndView;
            }
        }

        redirectAttribute.addFlashAttribute("message", "Thể loại không tồn tại");
        return modelAndView;
    }
}

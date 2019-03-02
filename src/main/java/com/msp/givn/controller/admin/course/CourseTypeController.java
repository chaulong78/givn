package com.msp.givn.controller.admin.course;

import com.msp.givn.entity.CourseType;
import com.msp.givn.service.course.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/course/type")
public class CourseTypeController {

    @Autowired
    private CourseTypeService typeService;

    @GetMapping(value = "")
    public ModelAndView showTypeTable() {
        ModelAndView modelAndView = new ModelAndView("admin/course-type/table-type");
        List<CourseType> typeList = typeService.findAll();

        modelAndView.addObject("typeList", typeList);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView showAddCourseTypeForm() {
        ModelAndView modelAndView = new ModelAndView("admin/course-type/add-type");
        CourseType type = new CourseType();

        modelAndView.addObject("type", type);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView processCreateCourse(@ModelAttribute("type") CourseType type, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/course/type");

        if (!result.hasErrors()) {
            CourseType existType = typeService.findByName(type.getName());
            if (existType != null) {
                modelAndView.setViewName("redirect:/admin/course/type/add");
                redirectAttributes.addFlashAttribute("message", "Tên thể loại đã tồn tại");
            } else {
                typeService.save(type);
                redirectAttributes.addFlashAttribute("message", "Tạo thể loại khóa học mới thành công");
            }

            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("message", "Tạo thể loại khóa học mới thất bại");
        return modelAndView;
    }
}

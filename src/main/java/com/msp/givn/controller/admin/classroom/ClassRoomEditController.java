package com.msp.givn.controller.admin.classroom;

import com.msp.givn.entity.ClassRoom;
import com.msp.givn.entity.Course;
import com.msp.givn.entity.UserDetail;
import com.msp.givn.service.classroom.ClassRoomService;
import com.msp.givn.service.course.CourseService;
import com.msp.givn.service.user.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/course/class")
public class ClassRoomEditController {

    @Autowired
    private ClassRoomService roomService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping(value = "/edit")
    public ModelAndView showAddClassForm(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/classroom/edit-classroom");

        if (id != null) {
            ClassRoom room = roomService.findById(id);
            if (room != null) {
                List<Course> courseList = courseService.findIdAndName();
                List<UserDetail> teacherList = userDetailService.findAllByRole("ROLE_MENTOR");

                modelAndView.addObject("room", room);
                modelAndView.addObject("courseList", courseList);
                modelAndView.addObject("teacherList", teacherList);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/admin/course/class");
        redirectAttributes.addFlashAttribute("message", "Lớp học không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView processEditRoom(
            @ModelAttribute("room") ClassRoom room
            , @RequestParam(value = "enabled", required = false) String checkBox
            , BindingResult result
            , RedirectAttributes redirectAttribute) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/course/class");

        if (!result.hasErrors()) {
            roomService.setEnable(room, checkBox);
            roomService.save(room);
            redirectAttribute.addFlashAttribute("message", "Cập nhật lớp học thành công");
            return modelAndView;
        }

        redirectAttribute.addFlashAttribute("message", "Cập nhật lớp học thất bại");
        return modelAndView;
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteRoom(@RequestParam(value = "id", required = false) Integer id
            , RedirectAttributes redirectAttribute) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/course/class");

        if (id != null) {
            if (roomService.findById(id) != null) {
                roomService.deleteById(id);
                redirectAttribute.addFlashAttribute("message", "Xóa lớp học thành công");
                return modelAndView;
            }
        }

        redirectAttribute.addFlashAttribute("message", "Lớp học không tồn tại");
        return modelAndView;
    }
}

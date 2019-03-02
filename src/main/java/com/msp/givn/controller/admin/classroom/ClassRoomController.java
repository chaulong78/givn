package com.msp.givn.controller.admin.classroom;

import com.msp.givn.dto.ClassRoomDTO;
import com.msp.givn.entity.ClassRoom;
import com.msp.givn.entity.Course;
import com.msp.givn.entity.UserDetail;
import com.msp.givn.service.classroom.ClassRoomDTOService;
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
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private ClassRoomDTOService roomDTOService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ModelAndView showClassRoomTable() {
        ModelAndView modelAndView = new ModelAndView("admin/classroom/table-classroom");
        List<ClassRoomDTO> classRoomDTOList = roomDTOService.findAll();

        modelAndView.addObject("classRoomDTOList", classRoomDTOList);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView showAddClassForm() {
        ModelAndView modelAndView = new ModelAndView("admin/classroom/add-classroom");
        ClassRoom room = new ClassRoom();
        List<Course> courseList = courseService.findAll();
        List<UserDetail> teacherList = userDetailService.findAllByRole("ROLE_MENTOR");

        modelAndView.addObject("room", room);
        modelAndView.addObject("courseList", courseList);
        modelAndView.addObject("teacherList", teacherList);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView processAddClass(
            @ModelAttribute("room") ClassRoom room
            , @RequestParam(value = "enabled", required = false) String checkBox
            , BindingResult result
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/course/class");

        if (!result.hasErrors()) {
            if (classRoomService.findByName(room.getName()) != null) {
                modelAndView.setViewName("redirect:/admin/course/class/add");
                redirectAttributes.addFlashAttribute("message", "Tên khóa học đã tồn tại");
                return modelAndView;
            }

            classRoomService.setEnable(room, checkBox);
            classRoomService.save(room);
            redirectAttributes.addFlashAttribute("message", "Tạo lớp học mới thành công");
            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("message", "Tạo lớp học mới thất bại");
        return modelAndView;
    }
}

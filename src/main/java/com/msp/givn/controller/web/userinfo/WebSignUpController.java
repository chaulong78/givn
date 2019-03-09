package com.msp.givn.controller.web.userinfo;

import com.msp.givn.dto.UserRegisterDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PostType;
import com.msp.givn.entity.User;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.service.register.RegisterService;
import com.msp.givn.service.user.UserDetailService;
import com.msp.givn.service.user.UserRoleService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/signup")
public class WebSignUpController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("web/signup");

        UserRegisterDTO user = new UserRegisterDTO();
        modelAndView.addObject("user", user);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @PostMapping(value = "")
    public ModelAndView processRegister(@ModelAttribute("user") UserRegisterDTO userRegisterDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/signup");
        String message;

        if (!result.hasErrors()) {
            message = registerService.validUserAndGetError(userRegisterDTO);

            if (message == null) {
                /* Lưu thông tin người dùng + role người dùng */
                User user = userService.createNewUser(userRegisterDTO);
                userRoleService.createRoleForUser(user.getId());
                userDetailService.createDetailForUser(user.getId());

                modelAndView.setViewName("redirect:/login");
                redirectAttributes.addFlashAttribute("message", "Đăng ký tài khoản thành công");
            } else {
                redirectAttributes.addFlashAttribute("message", message);
            }

            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("message", "Lỗi khi đăng ký tài khoản");
        return modelAndView;
    }
}

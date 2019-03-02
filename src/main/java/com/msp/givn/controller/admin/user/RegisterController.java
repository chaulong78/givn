package com.msp.givn.controller.admin.user;

import com.msp.givn.dto.UserRegisterDTO;
import com.msp.givn.entity.User;
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

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping(value = "")
    public ModelAndView showRegisterForm() {
        UserRegisterDTO user = new UserRegisterDTO();

        ModelAndView modelAndView = new ModelAndView("register-form");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @PostMapping(value = "/process")
    public ModelAndView processRegister(@ModelAttribute("user") UserRegisterDTO userRegisterDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/register");
        String message;

        if (!result.hasErrors()) {
            message = registerService.validUserAndGetError(userRegisterDTO);

            if (message == null) {
                /* Lưu thông tin người dùng + role người dùng */
                User user = userService.createNewUser(userRegisterDTO);
                userRoleService.createRoleForUser(user.getId());
                userDetailService.createDetailForUser(user.getId());

                modelAndView.setViewName("redirect:/login");
                message = "Đăng ký tài khoản mới thành công";
            }
        } else {
            message = "Lỗi khi đăng ký tài khoản";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }
}

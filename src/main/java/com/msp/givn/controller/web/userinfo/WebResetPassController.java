package com.msp.givn.controller.web.userinfo;

import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PasswordResetToken;
import com.msp.givn.entity.PostType;
import com.msp.givn.entity.User;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.service.register.RegisterService;
import com.msp.givn.service.user.PasswordResetTokenService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/reset")
public class WebResetPassController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView processConfirmationLink(@RequestParam("key") String token, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/forgot-pass");
        PasswordResetToken passwordResetToken = passwordResetTokenService.findByToken(token);

        if (passwordResetToken != null) {
            if (!passwordResetTokenService.validateExpiry(passwordResetToken)) {
                passwordResetTokenService.deleteById(passwordResetToken.getId());
                redirectAttributes.addFlashAttribute("message", "Mã xác thực không tồn tại");
            } else {
                modelAndView.addObject("key", token);
                modelAndView.setViewName("web/reset-pass");

                /*For menu*/
                List<CourseType> courseTypeList = courseTypeService.findAll();
                modelAndView.addObject("courseTypeList", courseTypeList);
                List<PostType> postTypeList = postTypeService.findAll();
                modelAndView.addObject("postTypeList", postTypeList);
            }

            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("message", "Mã xác thực không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "")
    public ModelAndView processResetPassword(
            @RequestParam("password") String password
            , @RequestParam("pass-again") String again
            , @RequestParam("key") String token, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/reset?key=" + token);
        User user = passwordResetTokenService.findByToken(token).getUser();
        String message;

        if (user != null && password != null && again != null) {
            message = registerService.validPasswordReset(password, again);

            if (message == null) {
                user.setPassword(encoder.encode(password));
                userService.save(user);
                passwordResetTokenService.deleteById(user.getId());

                modelAndView.setViewName("redirect:/login");
                redirectAttributes.addFlashAttribute("message", "Khôi phục mật khẩu thành công");
            } else {
                redirectAttributes.addFlashAttribute("message", message);
            }

            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("message", "Lỗi khi khôi phục mật khẩu");
        return modelAndView;
    }
}

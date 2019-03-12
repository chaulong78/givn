package com.msp.givn.controller.web.userinfo;

import com.msp.givn.dto.PasswordChangeDTO;
import com.msp.givn.entity.CourseType;
import com.msp.givn.entity.PostType;
import com.msp.givn.entity.User;
import com.msp.givn.service.course.CourseTypeService;
import com.msp.givn.service.post.PostTypeService;
import com.msp.givn.service.register.RegisterService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/change-pass")
public class WebChangePassController {

    @Autowired
    private UserService userService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PostTypeService postTypeService;

    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping(value = "")
    public ModelAndView showPassForm() {
        ModelAndView modelAndView = new ModelAndView("web/change-pass");
        PasswordChangeDTO pass = new PasswordChangeDTO();
        modelAndView.addObject("pass", pass);

        /*For menu*/
        List<CourseType> courseTypeList = courseTypeService.findAll();
        modelAndView.addObject("courseTypeList", courseTypeList);
        List<PostType> postTypeList = postTypeService.findAll();
        modelAndView.addObject("postTypeList", postTypeList);
        return modelAndView;
    }

    @PostMapping(value = "")
    public ModelAndView processChangePass(@ModelAttribute(value = "pass") PasswordChangeDTO pass
            , BindingResult result, RedirectAttributes ra) {
        ModelAndView modelAndView = new ModelAndView("redirect:/change-pass");

        int id = userService.getCustomUserDetail().getUserId();
        User user = userService.findById(id);

        String encoded = user.getPassword();
        String old = pass.getOldPassword();
        String newPass = pass.getPassword();
        String again = pass.getPasswordAgain();

        if (!result.hasErrors()) {
            String message = registerService.validPasswordReset(newPass, again);

            if (message == null){
                if (passwordEncoder.matches(old, encoded)){
                    newPass = passwordEncoder.encode(newPass);

                    /*Update local password*/
                    userService.updatePassword(id, newPass);
                    ra.addFlashAttribute("message", "Cập nhật mật khẩu thành công");
                } else {
                    ra.addFlashAttribute("message", "Mật khẩu cũ không đúng");
                }

                return modelAndView;
            } else {
                ra.addFlashAttribute("message", message);
            }

            return modelAndView;
        }

        ra.addFlashAttribute("message", "Lỗi đổi mật khẩu");
        return modelAndView;
    }
}

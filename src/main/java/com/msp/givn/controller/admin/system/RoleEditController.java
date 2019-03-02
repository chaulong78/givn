package com.msp.givn.controller.admin.system;

import com.msp.givn.entity.Role;
import com.msp.givn.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/system")
public class RoleEditController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/role/edit")
    public ModelAndView showEditRoleForm(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("admin/role/role-edit");

        if (id != null) {
            Role role = roleService.findById(id);
            if (role != null) {
                modelAndView.addObject("role", role);
                return modelAndView;
            }
        }

        modelAndView.setViewName("redirect:/admin/system/role");
        redirectAttributes.addFlashAttribute("message", "Quyền không tồn tại");
        return modelAndView;
    }

    @PostMapping(value = "/role/edit")
    public ModelAndView updateRole(
            @ModelAttribute("role") Role role
            , @RequestParam(value = "enabled", required = false) String checkBox
            , BindingResult result
            , RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/system/role");

        if (!result.hasErrors()) {
            setEnableValue(role, checkBox);

            /* Nếu là admin, luôn set enable = true */
            if (role.getName().equals("ROLE_ADMIN")) {
                role.setEnabled(true);
            }
            roleService.save(role);

            redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin quyền thành công");
        } else {
            redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin quyền không thành công");
        }
        return modelAndView;
    }

    private void setEnableValue(Role role, String checkBox) {
        if (checkBox != null) {
            role.setEnabled(true);
        } else {
            role.setEnabled(false);
        }
    }
}

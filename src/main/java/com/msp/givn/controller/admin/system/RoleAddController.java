package com.msp.givn.controller.admin.system;

import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.entity.Role;
import com.msp.givn.service.role.RoleFunctionService;
import com.msp.givn.service.role.RoleService;
import com.msp.givn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/system")
public class RoleAddController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/role/add")
    public ModelAndView showCreateRoleForm() {
        ModelAndView modelAndView = new ModelAndView("admin/role/add-role");

        Role role = new Role();
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @PostMapping(value = "/role/add")
    public ModelAndView processCreateRole(
            @ModelAttribute("role") Role role
            , @RequestParam(value = "enabled", required = false) String checkBox
            , BindingResult result,
            RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/system/role");
        String message = "";

        if (!result.hasErrors()) {
            role = bindRoleData(role, checkBox);
            Role existName = roleService.findByName(role.getName());

            if (existName == null) {
                int roleId = roleService.save(role).getId();

                /* Thêm ttin vào bảng role_function cho role mới - tất cả đều FALSE*/
                List<FunctionDTO> functionDTOList = userService.getCustomUserDetail().getFunctionDTOList();
                roleFunctionService.insertEmptyList(functionDTOList, roleId);
                message = "Tạo quyền mới thành công";
            } else {
                modelAndView.setViewName("redirect:/admin/system/role/add");
                message = "Quyền đã tồn tại";
            }
        } else {
            message = "Tạo quyền mới thất bại";
        }

        redirectAttributes.addFlashAttribute("message", message);
        return modelAndView;
    }

    private Role bindRoleData(Role role, String checkBox) {
        role.setName("ROLE_" + role.getName().toUpperCase());

        if (checkBox != null) {
            role.setEnabled(true);
        } else {
            role.setEnabled(false);
        }

        return role;
    }
}

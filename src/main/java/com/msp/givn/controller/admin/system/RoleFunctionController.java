package com.msp.givn.controller.admin.system;

import com.msp.givn.entity.Role;
import com.msp.givn.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin/system")
public class RoleFunctionController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/function")
    public ModelAndView showFunctionTable() {
        ModelAndView modelAndView = new ModelAndView("admin/function/table-function");
        return modelAndView;
    }

    @GetMapping(value = "/role")
    public ModelAndView showRoleTable() {
        ModelAndView modelAndView = new ModelAndView("admin/role/table-role");
        List<Role> roleList = roleService.findAll();
        modelAndView.addObject("roleList", roleList);
        return modelAndView;
    }
}

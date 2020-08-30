package com.rmiranda.schoolmanagement.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Role;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.RoleService;
import com.rmiranda.schoolmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView mv, User user, @ModelAttribute("roles") List<Role> roles) {
        mv.addObject("user", user);
        mv.addObject("allRoles", roles);
        mv.setViewName("users/create");
        return mv;
    }

    @PostMapping("/store")
    public ModelAndView store(@RequestParam(name = "userRoles") String[] roles, @Valid User user, BindingResult result, ModelAndView mv,
            @ModelAttribute(name = "roles") List<Role> allRoles) {
            
        List<Role> userRoles = new ArrayList<>();

        for (String r : roles) {
            userRoles.add(roleService.getRoleById(Long.valueOf(r)));
        }

        user.setRoles(userRoles);

        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            mv.addObject("allRoles", allRoles);
            mv.setViewName("users/create");
            return mv;
        }

        userService.addUSer(user);

        mv.setViewName("redirect:/users");

        return mv;
    }

}
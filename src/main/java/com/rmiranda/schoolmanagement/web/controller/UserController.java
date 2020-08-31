package com.rmiranda.schoolmanagement.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.Role;
import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.RoleService;
import com.rmiranda.schoolmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("")
    public ModelAndView index(ModelAndView mv) {
        Pageable page = PageRequest.of(0, 10);
        Page<User> users = userService.getAllUSers(page);
        mv.addObject("users", users);
        mv.setViewName("users/index");
        return mv;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView mv, User user, @ModelAttribute("roles") List<Role> roles) {
        mv.addObject("user", user);
        mv.addObject("allRoles", roles);
        mv.setViewName("users/create");
        return mv;
    }

    @PostMapping("/store")
    public ModelAndView store(@RequestParam(name = "userRoles", defaultValue = "") String[] roles, @Valid User user,
            BindingResult result, ModelAndView mv, @ModelAttribute(name = "roles") List<Role> allRoles) {

        List<Role> userRoles = new ArrayList<>();

        for (String r : roles) {
            userRoles.add(roleService.getRoleById(Long.valueOf(r)));
        }

        if (userRoles.size() > 0) {
            user.setRoles(userRoles);
        } else {
            result.addError(new ObjectError("userRoles", "Debe seleccioanar un rol"));
        }

        if (result.hasErrors()) {
            mv.addObject("allRoles", allRoles);
            mv.setViewName("users/create");
            return mv;
        }

        userService.addUSer(user);

        mv.setViewName("redirect:/users");

        return mv;
    }

}
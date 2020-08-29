package com.rmiranda.schoolmanagement.web.controller;

import javax.validation.Valid;

import com.rmiranda.schoolmanagement.model.entity.User;
import com.rmiranda.schoolmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/create")
    public ModelAndView create(ModelAndView mv, User user) {
        mv.addObject("user", user);
        mv.setViewName("users/create");
        return mv;
    }

    @PostMapping("/store")
    public ModelAndView store(@Valid User user, BindingResult result, ModelAndView mv) {

        if (result.hasErrors()) {
            mv.setViewName("users/create");
            return mv;
        }

        userService.addUSer(user);

        mv.setViewName("redirect:/users");

        return mv;
    }
    
}
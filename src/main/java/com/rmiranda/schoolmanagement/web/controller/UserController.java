package com.rmiranda.schoolmanagement.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/create")
    public ModelAndView create(ModelAndView mv) {
        mv.setViewName("users/create");
        return mv;
    }
    
}
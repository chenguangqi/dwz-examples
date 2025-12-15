package com.example.dwz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("redirect:/index.html");
        return mav;
    }
    
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, DWZ-JUI!";
    }
}
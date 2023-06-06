package br.com.register_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoutesController {
    

    @GetMapping("/signup")
    public ModelAndView signUp() {
        ModelAndView signup = new ModelAndView();
        signup.setViewName("register.html");
        return signup;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView loginPage = new ModelAndView();
        loginPage.setViewName("login.html");
        return loginPage;
    }


}

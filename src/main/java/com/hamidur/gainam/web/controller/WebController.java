package com.hamidur.gainam.web.controller;

import com.hamidur.gainam.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@Validated
public class WebController
{
    private final CustomerRepository customerRepository;

    @Autowired
    public WebController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = {"/", "index", "home", "/home.html"})
    public String home(Model model)
    {
        model.addAttribute("customers", customerRepository.findAll());
        return "home";
    }

    @GetMapping(value = "/access-denied")
    @ResponseBody
    public String accessDenied()
    {
        return "You are not allowed to the URL due to not having proper credentials.";
    }
}

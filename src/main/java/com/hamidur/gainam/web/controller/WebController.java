package com.hamidur.gainam.web.controller;

import com.hamidur.gainam.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String home()
    {
        return "home";
    }
}

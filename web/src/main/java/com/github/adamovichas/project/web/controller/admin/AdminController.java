package com.github.adamovichas.project.web.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);



    @GetMapping(value = "")
    public String adminGet(){
        return "admin_page";
    }

}

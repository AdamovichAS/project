package com.github.adamovichas.project.web.controller.admin;

import com.github.adamovichas.project.model.dto.AppCashAccountDTO;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.service.data.IEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final ICashAccountService cashAccountService;
    private final IEventService eventService;

    public AdminController(ICashAccountService cashAccountService, IEventService eventService) {
        this.cashAccountService = cashAccountService;
        this.eventService = eventService;
    }

    //    @RequestParam(value = "action",required = false) String action
    @GetMapping(value = "")
    public ModelAndView adminGet(){
        AppCashAccountDTO appCashAccount = cashAccountService.getAppCashAccount();
        return new ModelAndView("admin_page","appCash",appCashAccount);
    }

}

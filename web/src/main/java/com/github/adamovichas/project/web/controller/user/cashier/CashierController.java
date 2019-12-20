package com.github.adamovichas.project.web.controller.user.cashier;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import com.github.adamovichas.project.service.data.ICashAccountService;
import com.github.adamovichas.project.web.service.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(value = "/user/cashier")
public class CashierController {

    private static final Logger log = LoggerFactory.getLogger(CashierController.class);

    private final ICashAccountService cashAccountService;

    public CashierController(ICashAccountService cashAccountService) {
        this.cashAccountService = cashAccountService;
    }

    @GetMapping(value = "")
    public ModelAndView getCashAccount(HttpServletRequest req, ModelAndView modelAndView){
        modelAndView.setViewName("cashier");
        AuthUser authUser = WebUtil.getUserInSession();
        if(authUser.getStatus().equals(VereficationStatus.VEREF_PASSED)) {
            CashAccountDTO account = cashAccountService.getAccountByLogin(authUser.getLogin());
            modelAndView.addObject("account", account);
        }
        String action = req.getParameter("action");
        if(nonNull(action)){
            modelAndView.addObject("action",action);
        }
        return modelAndView;
    }

    @PostMapping(value = "/deposit")
    public ModelAndView makeDeposit(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("cashier");
        double deposit = Double.parseDouble(req.getParameter("deposit"));
        AuthUser authUser = WebUtil.getUserInSession();
        boolean isDepositDone = cashAccountService.makeDeposit(authUser.getLogin(), deposit);
        String message;
        if(isDepositDone){
            message = String.format("deposit.done",deposit);
        }else {
            message = "makeDeposit.failed";
        }
        modelAndView.addObject("message",message);
        getCashAccount(req,modelAndView);
        return modelAndView;
    }

    @PostMapping(value = "/withdrawal")
    public ModelAndView makeWithdrawal(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("cashier");
        double withdrawal = Double.parseDouble(req.getParameter("withdrawal"));
        AuthUser authUser = WebUtil.getUserInSession();
        boolean isWithdrawalDone = cashAccountService.withdrawal(authUser.getLogin(), withdrawal);
        String message;
        if(isWithdrawalDone){
            message = String.format("withdrawal.done",withdrawal);
        }else {
            message = "withdrawal.failed";
        }
        modelAndView.addObject("message",message);
        getCashAccount(req,modelAndView);
        return modelAndView;
    }

}

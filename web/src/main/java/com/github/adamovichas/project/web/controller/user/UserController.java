package com.github.adamovichas.project.web.controller.user;

import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.dto.CashAccountDTO;
import com.github.adamovichas.project.model.user.Role;
import com.github.adamovichas.project.model.user.passport.VereficationStatus;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.IUserService;
import com.github.adamovichas.project.web.service.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final IUserService userService;
    private final IBetService betService;

    public UserController(IUserService userService, IBetService betService) {
        this.userService = userService;
        this.betService = betService;
    }

    @RequestMapping(value = "")
    public ModelAndView userGet(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("user_page");
        AuthUser authUser = WebUtil.getUserInSession();
        String login = authUser.getLogin();
        if(authUser.getStatus().equals(VereficationStatus.VEREF_PASSED)) {
            CashAccountDTO accountByLogin = userService.getCashAccountByLogin(login);
            modelAndView.addObject("account",accountByLogin);
            String currentPage = req.getParameter("currentPage");
            if(currentPage == null){
                currentPage = "1";
            }
            int numberPage = Integer.parseInt(currentPage);
            modelAndView.addObject("currentPage",numberPage);
            List<BetView> betViews = betService.getBetsByLoginOnCurrentPage(login, Status.RUN_TIME,numberPage);
            if(!betViews.isEmpty()){
                modelAndView.addObject("userBets", betViews);
            }
            Long betMaxPagesByLogin = betService.getBetMaxPagesByLoginAndStatus(login,Status.RUN_TIME);
            modelAndView.addObject("maxPages",betMaxPagesByLogin);
//            }
        }
        return modelAndView;
    }



}

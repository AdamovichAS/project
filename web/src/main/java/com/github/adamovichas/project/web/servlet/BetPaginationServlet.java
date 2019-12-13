package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.impl.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BetPagination", urlPatterns = {"/bet_pagination"})
public class BetPaginationServlet extends HttpServlet {

    @Autowired
    private IBetService betService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String currentPage = req.getParameter("currentPage");
        if(currentPage == null){
            currentPage = "1";
        }
        int numberPage = Integer.parseInt(currentPage);
        req.setAttribute("currentPage",numberPage);
        List<BetView> betViews = betService.getBetsByLoginOnCurrentPage(authUser.getLogin(), Status.RUN_TIME,numberPage);
        if(!betViews.isEmpty()){
                req.setAttribute("userBets", betViews);
        }
        Long betMaxPagesByLogin = betService.getBetMaxPagesByLoginAndStatus(authUser.getLogin(),Status.RUN_TIME);
        req.setAttribute("maxPages",betMaxPagesByLogin);
        req.getRequestDispatcher("/user_bet.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

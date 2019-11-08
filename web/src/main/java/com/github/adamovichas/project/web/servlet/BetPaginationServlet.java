package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.model.dto.AuthUser;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.service.data.IDataBetService;
import com.github.adamovichas.project.service.data.impl.DataBetService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BetPaginationServlet extends HttpServlet {

    private IDataBetService betData = DataBetService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuthUser authUser = (AuthUser) req.getSession().getAttribute("authUser");
        String currentPage = req.getParameter("currentPage");
        if(currentPage == null){
            currentPage = "1";
        }
        int numberPage = Integer.parseInt(currentPage);
        req.setAttribute("currentPage",numberPage);
        List<BetView> betViews = betData.getBetsByLoginOnCorrentPage(authUser.getLogin(),numberPage);
        if(!betViews.isEmpty()){
                req.setAttribute("userBets", betViews);
        }
        Long betMaxPagesByLogin = betData.getBetMaxPagesByLogin(authUser.getLogin());
        req.setAttribute("maxPages",betMaxPagesByLogin);
        req.getRequestDispatcher("/user_bet.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

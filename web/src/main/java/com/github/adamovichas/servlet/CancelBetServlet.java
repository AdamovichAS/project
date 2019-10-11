package com.github.adamovichas.servlet;

import com.github.adamovichas.DAO.DAOBet;
import com.github.adamovichas.DAO.impl.IDAOBet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelBetServlet extends HttpServlet{

    private IDAOBet betData;

    @Override
    public void init() throws ServletException {
        betData = DAOBet.DAO_BET;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long betId = Long.valueOf(req.getParameter("betId"));
        betData.cancelBetById(betId);
        req.getRequestDispatcher("/redirect").forward(req,resp);
    }
}

package com.github.adamovichas.servlet;

import com.github.adamovichas.data.impl.DataBetService;
import com.github.adamovichas.data.IdataBetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CancelBetServlet extends HttpServlet{

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private IdataBetService betData;

    @Override
    public void init() throws ServletException {
        betData = DataBetService.DATA_BET_SERVICE;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long betId = Long.valueOf(req.getParameter("betId"));
        betData.cancelBetById(betId);
        log.info("Bet with Id {} cancel", betId);
        req.getRequestDispatcher("/redirect").forward(req,resp);
    }
}

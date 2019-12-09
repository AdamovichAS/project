package com.github.adamovichas.project.web.servlet;

import com.github.adamovichas.project.service.data.IBetService;
import com.github.adamovichas.project.service.data.impl.BetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class CancelBetServlet extends HttpServlet{

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private IBetService betData;

    @Override
    public void init() throws ServletException {
        betData = BetService.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  //      Long betId = Long.valueOf(req.getParameter("betId"));
        final String[] betIds = req.getParameterValues("betId");
        if(nonNull(betIds)) {
            List<Long> betsId = new ArrayList<>();
            for (String id : betIds) {
                betsId.add(Long.valueOf(id));
            }
            betData.cancelBetById(betsId);
        }
  //      betData.cancelBetById(betId);
 //       log.info("BetDTO with Id {} cancel", betId);
        req.getRequestDispatcher("/bet_pagination").forward(req,resp);
    }
}

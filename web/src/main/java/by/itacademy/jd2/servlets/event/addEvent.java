package by.itacademy.jd2.servlets.event;

import by.itacademy.jd2.DAO.DAOEvent;
import by.itacademy.jd2.DAO.IDAOEvent;
import by.itacademy.jd2.event.Event;
import by.itacademy.jd2.mysql_data.DataEvent;
import by.itacademy.jd2.mysql_data.IDataEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class addEvent extends HttpServlet {

    private IDAOEvent data;

    @Override
    public void init() throws ServletException {
        data = DAOEvent.DAO_EVENT;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = (Event) req.getAttribute("event");
        boolean isExist = data.eventIsExist(event);
        if(!isExist){
            Event savedEvent = data.addEvent(event);
            req.setAttribute("savedEvent",savedEvent);
        }else {
            req.setAttribute("savedEvent","Event is Exist");
        }
        req.getRequestDispatcher("/add_event.jsp").forward(req,resp);
    }
}

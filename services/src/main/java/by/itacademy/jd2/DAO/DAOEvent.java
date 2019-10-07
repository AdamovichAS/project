package by.itacademy.jd2.DAO;


import by.itacademy.jd2.dto.League;
import by.itacademy.jd2.dto.Team;
import by.itacademy.jd2.event.Event;
import by.itacademy.jd2.mysql_data.DataEvent;
import by.itacademy.jd2.mysql_data.IDataEvent;

import java.util.List;

import static java.util.Objects.nonNull;

public enum  DAOEvent implements IDAOEvent{
    DAO_EVENT;

    private IDataEvent dataEvent;

    DAOEvent() {
        dataEvent = DataEvent.DATA_EVENT;
    }

    @Override
    public List<League> getAllLeagues() {
        return dataEvent.getAllLeagues();
    }

    @Override
    public List<Team> getAllTeamsByLeague(Long idLeague) {
        return dataEvent.getAllTeamsByLeague(idLeague);
    }

    @Override
    public boolean eventIsExist(Event event) {
        Event dataBaseEvent = dataEvent.eventIsExist(event);
        return nonNull(dataBaseEvent);
    }

    @Override
    public Event addEvent(Event event) {
        return dataEvent.addEvent(event);
    }
}

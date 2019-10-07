package by.itacademy.jd2.DAO;

import by.itacademy.jd2.dto.League;
import by.itacademy.jd2.dto.Team;
import by.itacademy.jd2.event.Event;

import java.util.List;

public interface IDAOEvent {
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    boolean eventIsExist(Event event);
    Event addEvent(Event event);
}

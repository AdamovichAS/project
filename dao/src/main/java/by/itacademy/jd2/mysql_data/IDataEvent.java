package by.itacademy.jd2.mysql_data;

import by.itacademy.jd2.dto.League;
import by.itacademy.jd2.dto.Team;
import by.itacademy.jd2.event.Event;

import java.util.List;

public interface IDataEvent {
    Event addEvent(Event event);
    void updateEventInfo(Event event);
    void deleteEvent(Event event);
    List<League> getAllLeagues();
    List<Team> getAllTeamsByLeague(Long idLeague);
    Event eventIsExist(Event event);
}

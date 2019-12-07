package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.config.DaoConfig;
import com.github.adamovichas.project.config.HibernateConfig;
import com.github.adamovichas.project.dao.ILeagueDao;
import com.github.adamovichas.project.dao.impl.util.Util;
import com.github.adamovichas.project.model.dto.LeagueDTO;
import com.github.adamovichas.project.model.dto.TeamDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateConfig.class, DaoConfig.class, Util.class})
@Transactional()
//@Rollback(value = false)
public class LeagueDaoTest {

    @Autowired
    private ILeagueDao leagueDao;

    @Test
    void getAllLeagues(){
        int countLeagues = 2;
        List<LeagueDTO> allLeagues = leagueDao.getAllLeagues();
        assertEquals(countLeagues,allLeagues.size());
    }

    @Test
    void getAllTeamsByLeague(){
        Long idLeague = 1L;
        int countTeamsInLeague = 20;
        List<TeamDTO> allTeamsByLeague = leagueDao.getAllTeamsByLeague(idLeague);
        assertEquals(countTeamsInLeague,allTeamsByLeague.size());
    }
}

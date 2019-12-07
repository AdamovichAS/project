package com.github.adamovichas.project.dao.impl.util;


import com.github.adamovichas.project.dao.impl.UserDao;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.*;
import com.github.adamovichas.project.model.factor.FactorDTO;
import com.github.adamovichas.project.model.factor.FactorName;
import com.github.adamovichas.project.model.user.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class Util implements IUtil{

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

//    @Autowired
//    private final ISessionHibernate factory;
//
//    public Util(ISessionHibernate session) {
//        factory = session;
//    }


    public UserDTO createTestUser(){
        UserDTO user = new UserDTO();
        user.setLogin("test");
        user.setPassword("123");
        user.setRole(Role.USER_VER);
        return  user;
    }

//    public void deleteTestUser(UserEntity user){
//        Session session = factory.getSession();
//        session.getTransaction().begin();
//        UserEntity userEntity = session.find(UserEntity.class, user.getUserlogin());
//        session.remove(userEntity);
//        session.getTransaction().commit();
//        session.close();
//    }
//
//    public void deleteDeposit(String login){
//        Session session = factory.getSession();
//        session.getTransaction().begin();
//        CashAccountEntity cashAccountEntity = session.get(CashAccountEntity.class, login);
//        session.delete(cashAccountEntity);
//        session.getTransaction().commit();
//        session.close();
//    }

    public BetDTO createFinishedBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(1L);
        betDTO.setStatus(Status.FINISH);
        return betDTO;
    }

    public BetDTO createNotFinishedBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(1L);
        betDTO.setStatus(Status.RUN_TIME);
        return betDTO;
    }

    public BetDTO createCancelBet(){
        final UserDTO testUser = createTestUser();
        BetDTO betDTO = new BetDTO();
        betDTO.setUserLogin(testUser.getLogin());
        betDTO.setMoney(100);
        betDTO.setFactorId(1L);
        betDTO.setStatus(Status.CANCELD);
        return betDTO;
    }

     public EventDTO createEventTest(){
        EventDTO event = new EventDTO("Arsenal","Aston Vila", 1L, Timestamp.valueOf("2019-12-05 17:00:00"),Timestamp.valueOf("2019-12-05 18:00:00"));
        return event;
    }

    public List<FactorDTO> createFactors(){
        List<FactorDTO> factors= new ArrayList<>();
        factors.add(new FactorDTO(FactorName.win,2.5));
        factors.add(new FactorDTO(FactorName.draw,3));
        factors.add(new FactorDTO(FactorName.lose,2.1));
        return factors;
    }
    @Override
    public UserPassportDTO createTestPassport() {
        UserDTO testUser = createTestUser();
        UserPassportDTO passportDTO = new UserPassportDTO();
        passportDTO.setUserLogin(testUser.getLogin());
        passportDTO.setPassSeries("MP");
        passportDTO.setLastName("Test");
        passportDTO.setFirstName("Test");
        return passportDTO;
    }

    @Override
    public EventStatisticDTO createTestStatistic() {
        EventStatisticDTO statisticDto = new EventStatisticDTO();
        statisticDto.setTeamTwoGoals(2);
        statisticDto.setTeamOneGoals(2);
        return statisticDto;
    }

//    @Override
//    public AppCashAccountDTO createAppCashAccount() {
//        AppCashAccountDTO appCashAccountDTO = new AppCashAccountDTO();
//        appCashAccountDTO.setId(2L);
//        appCashAccountDTO.setBalance(100);
//        return appCashAccountDTO;
//    }

//     public void deleteEvent(Long id) {
//         Session session = factory.getSession();
//         try{
//             session.getTransaction().begin();
//             EventEntity eventEntity = session.find(EventEntity.class, id);
//            session.delete(eventEntity);
//            session.getTransaction().commit();
//         }catch (RollbackException e){
//             log.error("deleteEvent exception, Id -  {}",id);
//             session.getTransaction().rollback();
//         }finally {
//             session.close();
//         }
//     }

//    public Long getCountAllNotFinishedEvents(){
//        Session session = factory.getSession();
//        session.getTransaction().begin();
//        Query query = session.createQuery("SELECT count(*) FROM EventEntity e WHERE e.resultFactorId = null");
//        List list = query.list();
//        if (list.isEmpty()){
//            return 0L;
//        }
//        return (Long) list.get(0);
//    }
}
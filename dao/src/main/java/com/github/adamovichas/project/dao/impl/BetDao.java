package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IBetDao;
import com.github.adamovichas.project.dao.repository.BetRepository;
import com.github.adamovichas.project.dao.repository.FactorRepository;
import com.github.adamovichas.project.dao.repository.UserRepository;
import com.github.adamovichas.project.entity.BetEntity;
import com.github.adamovichas.project.entity.CashAccountEntity;
import com.github.adamovichas.project.entity.FactorEntity;
import com.github.adamovichas.project.entity.UserEntity;
import com.github.adamovichas.project.model.bet.Status;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import com.github.adamovichas.project.util.EntityDtoViewConverter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class BetDao implements IBetDao {

    private static final Logger log = LoggerFactory.getLogger(BetDao.class);

    private final BetRepository betRepository;
    private final UserRepository userRepository;
    private final FactorRepository factorRepository;

    public BetDao(BetRepository betRepository, UserRepository userRepository, FactorRepository factorRepository) {
        this.betRepository = betRepository;
        this.userRepository = userRepository;
        this.factorRepository = factorRepository;
    }

    @Override
    public Long addBet(BetDTO bet) {
        BetEntity betEntity = EntityDtoViewConverter.getEntity(bet);
        UserEntity userEntity = userRepository.getOne(bet.getUserLogin());
        FactorEntity factorEntity = factorRepository.getOne(bet.getFactorId());
        betEntity.setFactor(factorEntity);
        betEntity.setUser(userEntity);
        if(userEntity.getBets() == null){
            userEntity.setBets(new ArrayList<>());
        }
        userEntity.getBets().add(betEntity);
        factorEntity.getBets().add(betEntity);
        betRepository.save(betEntity);
        return betEntity.getId();
    }

    @Override
    public BetView getViewById(Long idBet) {
        BetEntity betEntity = betRepository.findById(idBet).get();
        return EntityDtoViewConverter.getView(betEntity);
    }

    @Override
    public List<BetView> getAllByUserAndStatus(String login, Status status) {
        List<BetView> views = new ArrayList<>();
        List<BetEntity> betEntities = betRepository.getAllByUserLoginAndStatus(login,status);
        if(!betEntities.isEmpty()) {
            views = getViews(betEntities);
        }
        return views;
    }

    @Override
    public List<BetDTO>getAllNotFinishedBetsByFactorId(Long factorId) {
        List<BetDTO>dtos = new ArrayList<>();
        List<BetEntity> betEntities = betRepository.getAllNotFinishedBetsByFactorId(factorId);
        if(!betEntities.isEmpty()){
            for (BetEntity entity : betEntities) {
                dtos.add(EntityDtoViewConverter.getDTO(entity));
            }
        }
        return dtos;
    }

    @Override
    public void updateBetStatus(Long idBet, Status status) {
        BetEntity betEntity = betRepository.getOne(idBet);
        betEntity.setStatus(status);
        betRepository.flush();
    }

    @Override
    public Long getCountBetsByLoginAbdStatus(String login, Status status) {
//        Session session = betRepository.getSession();
//        session.getTransaction().begin();
//        final Long count = session.createQuery("SELECT count (*) FROM BetEntity b WHERE b.userLogin = :login", Long.class)
//                .setParameter("login", login)
//                .getSingleResult();
//        session.getTransaction().commit();
//        session.close();
//        return count;
//        throw new RuntimeException();
        return betRepository.countByUserLoginAndStatus(login,status);
    }

    @Override
    public List<BetView> getBetsOnPageByLoginAndStatus(String login, Status status, int page, int pageSize) {
        List<BetView> betViews = new ArrayList<>();
//        final Session session = betRepository.getSession();
//        try {
//            session.getTransaction().begin();
//            final Query query = session.createQuery("FROM BetEntity b WHERE b.userLogin = :login ORDER BY b.id asc")
//                    .setParameter("login", login)
//                    .setMaxResults(pageSize)
//                    .setFirstResult((page - 1) * pageSize)
//                    .setReadOnly(true);
//            final List<BetEntity> betEntities = query.list();
//            for (BetEntity entity : betEntities) {
//                BetView view = EntityDtoViewConverter.getView(entity);
//                betViews.add(view);
//            }
//            session.getTransaction().commit();
//        }catch (PersistenceException e){
//            log.error("Fail to get list of events from DB at: {}", LocalDateTime.now(), e);
//            throw new RuntimeException(e);
//        }finally {
//            session.close();
//        }
        List<BetEntity> betEntities = betRepository.getAllByUserLoginAndStatus(login, status, PageRequest.of(page, pageSize, Sort.by("id")));
        if(!betEntities.isEmpty()){
            betViews = getViews(betEntities);
        }
        return betViews;


    }

    private List<BetView> getViews(List<BetEntity> entities){
        List<BetView>views = new ArrayList<>();
        for (BetEntity entity : entities) {
            views.add(EntityDtoViewConverter.getView(entity));
        }
        return views;
    }


}


package com.github.adamovichas.project.dao.impl;

import com.github.adamovichas.project.dao.IBetDao;
import com.github.adamovichas.project.dao.repository.BetRepository;
import com.github.adamovichas.project.model.dto.BetDTO;
import com.github.adamovichas.project.model.view.BetView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BetDao implements IBetDao {

    private static final Logger log = LoggerFactory.getLogger(BetDao.class);
    private final BetRepository repository;

    public BetDao(BetRepository repository) {
        this.repository = repository;

    }




    @Override
    public Long addBet(BetDTO bet) {
//        Session session = repository.getSession();
//        Long id = null;
//        try {
//            BetEntity betEntity = EntityDtoViewConverter.getEntity(bet);
//            session.getTransaction().begin();
//            UserEntity userEntity = session.find(UserEntity.class, betEntity.getUserLogin());
//            FactorEntity factorEntity = session.find(FactorEntity.class, betEntity.getFactorId());
//            CashAccountEntity cashAccountEntity = session.find(CashAccountEntity.class, bet.getUserLogin());
//            cashAccountEntity.setValue(cashAccountEntity.getValue()- bet.getMoney());
//            session.saveOrUpdate(cashAccountEntity);
//            betEntity.setFactor(factorEntity);
//            betEntity.setUserEntity(userEntity);
//            userEntity.getBets().add(betEntity);
//            factorEntity.getBets().add(betEntity);
//            id = (Long) session.save(betEntity);
//            session.getTransaction().commit();
//        }catch (RollbackException e){
//            log.error("AddBet exception, BetDTO {}",bet);
//            session.getTransaction().rollback();
//        }finally {
//            session.close();
//        }
//        return id;
        throw new RuntimeException();
    }

    @Override
    public BetView getViewById(Long idBet) {
//        Session session = repository.getSession();
//        session.getTransaction().begin();
//        BetEntity betEntity = session.get(BetEntity.class, idBet);
//        Hibernate.initialize(betEntity.getFactor().getEvent());
//        session.getTransaction().commit();
//        session.close();
//        BetView view = EntityDtoViewConverter.getView(betEntity);
//        return view;
        throw new RuntimeException();
    }

    @Override
    public List<BetView> getNotFinishedBetByLogin(String login) {
//        Session session = repository.getSession();
//        List<BetView>views = new ArrayList<>();
//        BetView betView;
//        session.getTransaction().begin();
//        UserEntity userEntity = session.find(UserEntity.class, login);
//        List<BetEntity> betsEntity = userEntity.getBets();
//        for (BetEntity entity : betsEntity) {
//            Long resultFactorId = entity.getFactor().getEvent().getResultFactorId();
//            if(resultFactorId == null){
//               betView = EntityDtoViewConverter.getView(entity);
//               views.add(betView);
//            }
//        }
//        session.getTransaction().commit();
//        session.close();
//        return views;
        throw new RuntimeException();
    }

    @Override
    public void CancelBetById(Long idBet) {
//        Session session = repository.getSession();
//        try {
//            session.getTransaction().begin();
//            BetEntity betEntity = session.get(BetEntity.class, idBet);
//            session.delete(betEntity);
//            CashAccountEntity cashAccountEntity = session.find(CashAccountEntity.class, betEntity.getUserLogin());
//            cashAccountEntity.setValue(cashAccountEntity.getValue() + betEntity.getMoney());
//            session.getTransaction().commit();
//        }catch (RollbackException e){
//            log.error("CancelBetById exception, idBet {}",idBet);
//            session.getTransaction().rollback();
//        }finally {
//            session.close();
//        }

    }

    @Override
    public Long getCountBetsByLogin(String login){
//        Session session = repository.getSession();
//        session.getTransaction().begin();
//        final Long count = session.createQuery("SELECT count (*) FROM BetEntity b WHERE b.userLogin = :login", Long.class)
//                .setParameter("login", login)
//                .getSingleResult();
//        session.getTransaction().commit();
//        session.close();
//        return count;
        throw new RuntimeException();
    }

    @Override
    public List<BetView> getBetsOnPageByLogin(String login, int page, int pageSize){
        List<BetView>betViews = new ArrayList<>();
//        final Session session = repository.getSession();
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
        return betViews;
    }


}


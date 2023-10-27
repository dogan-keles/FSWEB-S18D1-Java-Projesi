package com.workintech.Burger.Rest.Api.dao;

import com.workintech.Burger.Rest.Api.entity.BreadType;
import com.workintech.Burger.Rest.Api.entity.Burger;
import com.workintech.Burger.Rest.Api.exceptions.BurgerException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BurgerDaoImp implements BurgerDao{
private EntityManager entityManager;


    @Autowired
    public BurgerDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Burger save(Burger burger) {
        entityManager.persist(burger);
        return burger;
    }

    @Override
    public List<Burger> findAll() {
        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b", Burger.class);
        return query.getResultList();
    }

    @Override
    public Burger findById(long id) {
        Burger burger = entityManager.find(Burger.class, id);
        if(burger == null){
            throw new BurgerException("Burger with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }

        return entityManager.find(Burger.class, id);
    }
    @Transactional
    @Override
    public Burger update(Burger burger) {
        return entityManager.merge(burger);
    }
    @Transactional
    @Override
    public Burger remove(long id) {
        Burger burger = findById(id);
        entityManager.remove(burger);
        return burger;
    }

    @Override
    public List<Burger> findByPrice(int price) {
        TypedQuery<Burger> query =
                entityManager.createQuery("SELECT b FROM Burger b WHERE b.price > :price ORDER BY b.price desc"
                        , Burger.class);
        query.setParameter("price", price);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByBreadType(BreadType breadType) {

        TypedQuery<Burger> query =  entityManager.createQuery("SELECT b FROM Burger b WHERE breadType = :breadType " +
                "ORDER BY b.name ASC ", Burger.class);
        query.setParameter("breadType", breadType);
        return query.getResultList();
    }

    @Override
    public List<Burger> findByContent(String content) {

        TypedQuery<Burger> query = entityManager.createQuery("SELECT b FROM Burger b WHERE " +
                "b.contents like CONCAT('%',:content,'%') ORDER BY b.name", Burger.class);
        query.setParameter("content", content);
        return query.getResultList();
    }
}

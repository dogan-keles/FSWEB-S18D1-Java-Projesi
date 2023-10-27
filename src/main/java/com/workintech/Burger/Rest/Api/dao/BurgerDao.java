package com.workintech.Burger.Rest.Api.dao;

import com.workintech.Burger.Rest.Api.entity.BreadType;
import com.workintech.Burger.Rest.Api.entity.Burger;

import java.util.List;

public interface BurgerDao {
    Burger save(Burger burger);
    List<Burger> findAll();
    Burger findById(long id);
    Burger update(Burger burger);
    Burger remove(long id);
    List<Burger> findByPrice(int price);
    List<Burger> findByBreadType(BreadType breadType);
    List<Burger> findByContent(String content);
}
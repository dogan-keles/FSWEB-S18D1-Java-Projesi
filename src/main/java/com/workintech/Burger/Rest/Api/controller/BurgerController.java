package com.workintech.Burger.Rest.Api.controller;

import com.workintech.Burger.Rest.Api.dao.BurgerDao;
import com.workintech.Burger.Rest.Api.dto.BurgerResponse;
import com.workintech.Burger.Rest.Api.entity.BreadType;
import com.workintech.Burger.Rest.Api.entity.Burger;
import com.workintech.Burger.Rest.Api.util.BurgerResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/burger")
public class BurgerController {
    private BurgerDao burgerDao;
@Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }
@PostMapping("/")
    public Burger save(@RequestBody Burger burger){
    return burgerDao.save(burger);
    }
    @GetMapping("/")
    public List<BurgerResponse> findAll(){
        List<Burger> burgers = burgerDao.findAll();
        return BurgerResponseEntity.burgerToBurgerResponse(burgers);
    }

    @GetMapping("/{id}")
    public Burger find(@PathVariable long id){
    return burgerDao.findById(id);
    }
@PutMapping("/")
    public Burger update(@RequestBody Burger burger){
    return burgerDao.update(burger);
    }

@DeleteMapping("/{id}")
    public Burger remove(@PathVariable long id){
    return burgerDao.remove(id);
}
@GetMapping("/findByPrice/{price}")
public List<Burger> findByPrice(@PathVariable int price){
    return burgerDao.findByPrice(price);
}
    @GetMapping("/findByBreadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable String breadType){
        BreadType breadTypeEnum = BreadType.valueOf(breadType);
        return burgerDao.findByBreadType(breadTypeEnum);
    }
    @GetMapping("/findByContent/{content}")
    public List<Burger> findByContent(@PathVariable String content){

        return burgerDao.findByContent(content);
    }
}

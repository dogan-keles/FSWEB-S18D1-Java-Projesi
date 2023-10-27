package com.workintech.Burger.Rest.Api.util;

import com.workintech.Burger.Rest.Api.dto.BurgerResponse;
import com.workintech.Burger.Rest.Api.entity.Burger;
import com.workintech.Burger.Rest.Api.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class BurgerResponseEntity {

    public static List<BurgerResponse> burgerToBurgerResponse(List<Burger> burgers){
        List<BurgerResponse> responses = new ArrayList<>();
        if(burgers.isEmpty()){
            throw new BurgerException("Burger List is empty", HttpStatus.NOT_FOUND);
        }
        for(Burger burger: burgers){
            responses.add(new BurgerResponse(burger.getName(), burger.getPrice()));
        }
        return responses;
    }

}
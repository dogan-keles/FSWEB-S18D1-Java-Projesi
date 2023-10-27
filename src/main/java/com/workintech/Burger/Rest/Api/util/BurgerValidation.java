package com.workintech.Burger.Rest.Api.util;


import com.workintech.Burger.Rest.Api.entity.Burger;
import com.workintech.Burger.Rest.Api.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {

    public static void checkBurgerCredentials(Burger burger){
        System.out.println(burger);
        if(burger.getName() == null || burger.getName().isEmpty() || burger.getPrice() <= 0){
            throw new BurgerException("Burger credentials are not valid", HttpStatus.BAD_REQUEST);
        }
    }

}
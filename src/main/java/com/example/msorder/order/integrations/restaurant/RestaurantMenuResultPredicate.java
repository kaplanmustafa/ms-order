package com.example.msorder.order.integrations.restaurant;

import com.example.msorder.order.integrations.models.MenuPriceInfo;

import java.util.function.Predicate;

public class RestaurantMenuResultPredicate implements Predicate<MenuPriceInfo> {

    @Override
    public boolean test(MenuPriceInfo menuPriceInfo) {

        if(menuPriceInfo.getPort() == 10001){
            return true;
        }

        return false;
    }
}

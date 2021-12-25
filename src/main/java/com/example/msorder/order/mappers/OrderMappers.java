package com.example.msorder.order.mappers;

import com.example.msorder.order.rest.models.Order;
import com.example.msorder.order.rest.models.OrderRestObj;

public class OrderMappers {

    public static Order toOrder(OrderRestObj orderParam) {
        Order orderLoc = new Order();
        orderLoc.setMeals(orderParam.getMeals());
        orderLoc.setName(orderParam.getName());
        orderLoc.setSurname(orderParam.getSurname());
        orderLoc.setNumber(orderParam.getNumber());
        return orderLoc;
    }
}

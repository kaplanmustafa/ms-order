package com.example.msorder.order.services;

import com.example.msorder.order.integrations.models.MenuPriceInfo;
import com.example.msorder.order.integrations.restaurant.RestaurantMenuClient;
import com.example.msorder.order.rest.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManagementService {

    @Autowired
    private RestaurantMenuClient rmc;

    public String place(final Order order) {
        MenuPriceInfo menuPriceInfo = rmc.calculateMenu(order);
        return "Menu Fiyatı: " + menuPriceInfo.getPrice() + " Port:" + menuPriceInfo.getPort();
    }
}

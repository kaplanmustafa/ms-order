package com.example.msorder.order.services;

import com.example.mscommon.error.feign.MyFeignClientException;
import com.example.msorder.order.integrations.models.MenuPriceInfo;
import com.example.msorder.order.integrations.restaurant.RestaurantMenuClient;
import com.example.msorder.order.rest.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderManagementService {

    @Autowired
    private RestaurantMenuClient rmc;

    public String place(final Order order) throws MyFeignClientException {
        MenuPriceInfo menuPriceInfo = rmc.calculateMenu(order);
        return "Menu Fiyatı: " + menuPriceInfo.getPrice() + " Port:" + menuPriceInfo.getPort();
    }
}

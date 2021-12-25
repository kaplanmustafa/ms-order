package com.example.msorder.order.integrations.restaurant;

import com.example.msorder.order.integrations.models.Menu;
import com.example.msorder.order.integrations.models.MenuPriceInfo;
import com.example.msorder.order.rest.models.Order;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestaurantMenuClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    EurekaClient eurekaClient;

    public MenuPriceInfo calculateMenu(final Order order) {
        Menu menu = new Menu();
        menu.setMeals(order.getMeals());
        menu.setMenuName("Menu: " + order.getName() + " " + order.getSurname());
        return restTemplate.postForObject("http://RESTAURANT/api/v1/restaurant/menu/calculate",
                menu,
                MenuPriceInfo.class);
    }

    public MenuPriceInfo calculateMenu2(final Order order) {
        Menu menu = new Menu();
        menu.setMeals(order.getMeals());
        menu.setMenuName("Menu: " + order.getName() + " " + order.getSurname());
        Application application = eurekaClient.getApplication("RESTAURANT");
        List<InstanceInfo> instances = application.getInstances();
        RestTemplate rt = new RestTemplate();
        return rt.postForObject("http://" + instances.get(0).getIPAddr() + ":" + instances.get(0).getPort() + "/api/v1/restaurant/menu/calculate",
                menu,
                MenuPriceInfo.class);
    }
}

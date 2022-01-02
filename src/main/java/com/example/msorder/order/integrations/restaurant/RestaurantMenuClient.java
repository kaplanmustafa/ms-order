package com.example.msorder.order.integrations.restaurant;

import com.example.mscommon.error.feign.MyFeignClientException;
import com.example.msorder.order.integrations.models.Menu;
import com.example.msorder.order.integrations.models.MenuPriceInfo;
import com.example.msorder.order.rest.models.Order;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestaurantMenuClient {

    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;
    private final RestaurantMenuIntegration restaurantMenuIntegration;

    @Autowired
    public RestaurantMenuClient(RestTemplate restTemplate, EurekaClient eurekaClient, RestaurantMenuIntegration restaurantMenuIntegration) {
        this.restTemplate = restTemplate;
        this.eurekaClient = eurekaClient;
        this.restaurantMenuIntegration = restaurantMenuIntegration;
    }

    @Retry(name = "restaurantretry", fallbackMethod = "calculateMenuFallback")
    @CircuitBreaker(name = "cbrestaurant")
    public MenuPriceInfo calculateMenu(final Order order) throws MyFeignClientException {
        Menu menu = new Menu();
        menu.setMeals(order.getMeals());
        menu.setMenuName("Menu: " + order.getName() + " " + order.getSurname());
        menu.setExtra("Extra");
        return restaurantMenuIntegration.calculate(menu);
    }

    public MenuPriceInfo calculateMenuFallback(final Order order, Throwable th) {
        MenuPriceInfo menuPriceInfo = new MenuPriceInfo();
        menuPriceInfo.setPort(0);
        menuPriceInfo.setPrice(1000);
        return menuPriceInfo;
    }

    public MenuPriceInfo calculateMenu1(final Order order) {
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

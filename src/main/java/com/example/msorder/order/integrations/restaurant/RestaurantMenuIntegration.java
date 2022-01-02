package com.example.msorder.order.integrations.restaurant;

import com.example.mscommon.error.feign.MyFeignClientException;
import com.example.msorder.order.integrations.models.Menu;
import com.example.msorder.order.integrations.models.MenuPriceInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("RESTAURANT")
public interface RestaurantMenuIntegration {

    @PostMapping("/api/v1/restaurant/menu/calculate")
    MenuPriceInfo calculate(@RequestBody final Menu menu) throws MyFeignClientException;
}

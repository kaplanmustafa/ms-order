package com.example.msorder.order.rest;

import com.example.mscommon.error.feign.MyFeignClientException;
import com.example.msorder.order.mappers.OrderMappers;
import com.example.msorder.order.rest.models.OrderRestObj;
import com.example.msorder.order.services.OrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order/management")
public class OrderManagementController {

    @Autowired
    private OrderManagementService oms;

    @PostMapping("/place")
    public String place(@RequestBody final OrderRestObj order) throws MyFeignClientException {
        return oms.place(OrderMappers.toOrder(order));
    }
}

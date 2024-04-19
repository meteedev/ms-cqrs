package com.learn.cqrs.controller.command;


import com.learn.cqrs.dto.OrderCommandDto;
import com.learn.cqrs.service.OrderCommandService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("po")
@ConditionalOnProperty(name = "app.write.enabled", havingValue = "true")
public class OrderCommandController {


    private final OrderCommandService orderCommandService;

    public OrderCommandController(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @PostMapping("/sale")
    public void placeOrder(@RequestBody OrderCommandDto dto){
        this.orderCommandService.createOrder(dto.getUserIndex(), dto.getProductIndex());
    }

    @PutMapping("/cancel-order/{orderId}")
    public void cancelOrder(@PathVariable long orderId){
        this.orderCommandService.cancelOrder(orderId);
    }
}

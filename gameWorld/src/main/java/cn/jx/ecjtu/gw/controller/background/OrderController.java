package cn.jx.ecjtu.gw.controller.background;

import cn.jx.ecjtu.gw.model.OrderModel;
import cn.jx.ecjtu.gw.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @PostMapping("addOrder")
    public String addOrder(@RequestBody OrderModel order){
        String msg = orderService.addOrder(order);
        System.out.println(msg);
        return msg;
    }

    @PostMapping("searchOrders")
    public OrderModel[] searchOrders(@RequestBody OrderModel tempList){
        OrderModel[] orderModels = orderService.searchOrders(tempList);
        if ( orderModels!= null){
            return orderModels;
        }
        return null;
    }

    @PostMapping("searchReturnOrders")
    public OrderModel[] searchReturnOrders(@RequestBody OrderModel orderModels){
        OrderModel[] models = orderService.searchReturnOrders(orderModels);
        return models;
    }

    @PostMapping("deleteOne")
    public String deleteOne(@RequestBody OrderModel orderModel){
        String msg = orderService.deleteOne(orderModel.getOrderId());
        System.out.println(msg);
        return msg;
    }

    @PostMapping("refuseOrder")
    public String refuseOrder(@RequestBody OrderModel orderModel){
        String msg = orderService.refuseOrder(orderModel);
        return msg;
    }

    @PostMapping("closeOrder")
    public String closeOrder(@RequestBody OrderModel orderModel){
        String msg = orderService.closeOne(orderModel);
        return msg;
    }

    @PostMapping("agreeOrder")
    public String agreeOrder(@RequestBody OrderModel orderModel){
        String msg = orderService.agreeOrder(orderModel);
        return msg;
    }

}
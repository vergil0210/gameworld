package cn.jx.ecjtu.gw.service;

import cn.jx.ecjtu.gw.model.OrderModel;

/**
 * @author Administrator
 */
public interface IOrderService {
    /**
     * @return 返回插入结果的提示信息
     */
    String addOrder(OrderModel orderModel);

    OrderModel[] searchOrders(OrderModel orderModel);

    String deleteOne(String orderId);

    String closeOne(OrderModel orderModel);

    OrderModel[] searchReturnOrders(OrderModel orderModel);


    /**
     * 拒绝退款
     * @param orderModel
     * @return
     */
    String refuseOrder(OrderModel orderModel);

    /**
     * 同意退款
     * @param orderModel
     * @return
     */
    String agreeOrder(OrderModel orderModel);
}
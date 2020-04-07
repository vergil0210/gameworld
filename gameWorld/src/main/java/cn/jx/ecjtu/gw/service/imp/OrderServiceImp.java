package cn.jx.ecjtu.gw.service.imp;

import cn.jx.ecjtu.gw.dao.OrderDao;
import cn.jx.ecjtu.gw.dao.ProductDao;
import cn.jx.ecjtu.gw.dao.UserDao;
import cn.jx.ecjtu.gw.entity.GOrderEntity;
import cn.jx.ecjtu.gw.entity.GProductEntity;
import cn.jx.ecjtu.gw.entity.GUserEntity;
import cn.jx.ecjtu.gw.model.OrderModel;
import cn.jx.ecjtu.gw.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.*;

@Service
public class OrderServiceImp implements IOrderService {

    @Autowired
    OrderDao orderDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public String addOrder(OrderModel orderModel) {
        GOrderEntity orderEntity = new GOrderEntity();
        List<String> gameNames = orderModel.getGameNames();
        GUserEntity userEntity = userDao.findByUsername(orderModel.getUsername());
        if (userEntity==null) {
            return "该用户不存在";
        }
        Set<GProductEntity> products = new HashSet<>(gameNames.size());
        for (String gameName : gameNames) {
            GProductEntity productEntity = productDao.findByName(gameName);
            products.add(productEntity);
        }
        double orderPrice = 0;
        for (GProductEntity product : products) {
            orderPrice += product.getOriginalPrice();
        }
        if (userEntity.getBalance()<orderPrice) {
            return "非常抱歉，余额不足";
        }else {
            userEntity.setBalance(userEntity.getBalance()-orderPrice);
        }
        if (!StringUtils.isEmpty(orderPrice)){
            orderEntity.setTotalPrice(orderPrice);
        }
        if (products!=null){
            orderEntity.setProducts(products);
        }
        if (userEntity!=null) {
            orderEntity.setUser(userEntity);
        }
        if (StringUtils.isEmpty(orderModel.getPayPattern())){
            orderEntity.setPayPattern("0");
            orderEntity.setStatus("0");
        }else {
            orderEntity.setPayPattern(transformPayPattern(orderModel.getPayPattern()));
            if (StringUtils.isEmpty(orderModel.getStatus())){
                orderEntity.setStatus("1");
            }else {
                orderEntity.setStatus(transformStatus(orderModel.getStatus()));
            }
        }


        orderEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        try {
            orderDao.save(orderEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return "保存订单失败";
        }
        return "";
    }

    @Override
    public OrderModel[] searchOrders(OrderModel orderModel) {
        Specification<GOrderEntity> spec  = new Specification<GOrderEntity>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(orderModel.getOrderId())){
                    list.add(criteriaBuilder.like(root.get("orderId"),orderModel.getOrderId()));
                }
                if (!StringUtils.isEmpty(orderModel.getUsername())){
                    list.add(criteriaBuilder.like(root.get("user").get("username"),orderModel.getUsername()));
                }
                if (!StringUtils.isEmpty(orderModel.getStatus())){
                    list.add(criteriaBuilder.equal(root.get("status"),transformStatus(orderModel.getStatus())));
                }
                if (!StringUtils.isEmpty(orderModel.getPayPattern())){
                    list.add(criteriaBuilder.equal(root.get("payPattern"),transformPayPattern(orderModel.getPayPattern())));
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Pageable pageable = PageRequest.of(orderModel.getPage(), orderModel.getSize());
        Page<GOrderEntity> orderPage = orderDao.findAll(spec, pageable);
        List<GOrderEntity> content = orderPage.getContent();
        OrderModel[] models = transformOrder(content);
        return models;
    }

    @Override
    @Transactional
    public String deleteOne(String orderId) {
        try {
            orderDao.deleteById(Integer.parseInt(orderId));
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
        return "";
    }

    @Override
    public String closeOne(OrderModel orderModel) {
        String msg = null;
        try {
            GOrderEntity order = orderDao.findById(Integer.parseInt(orderModel.getOrderId())).get();
            order.setStatus("5");
            orderDao.save(order);
        } catch (RuntimeException e) {
            e.printStackTrace();
            msg = "修改出现未知错误，请联系管理员";
        }
        return msg;
    }

    @Override
    public OrderModel[] searchReturnOrders(OrderModel orderModel) {
        Specification<GOrderEntity> spec  = new Specification<GOrderEntity>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(orderModel.getOrderId())){
                    list.add(criteriaBuilder.equal(root.get("orderId"),Integer.parseInt(orderModel.getOrderId())));
                }
                if (!StringUtils.isEmpty(orderModel.getUsername())){
                    list.add(criteriaBuilder.like(root.get("user").get("username"),orderModel.getUsername()));
                }
                if (!StringUtils.isEmpty(orderModel.getStatus())){
                    list.add(criteriaBuilder.equal(root.get("status"),transformStatus(orderModel.getStatus())));
                }
                if (!StringUtils.isEmpty(orderModel.getPayPattern())){
                    list.add(criteriaBuilder.equal(root.get("payPattern"),transformPayPattern(orderModel.getPayPattern())));
                }
                //判断 该订单是否处于退货相关状态 3表示申请退款 4表示已退款 2表示拒绝退款 详见数据库备注
                list.add(criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("status"),"2")
                        ,criteriaBuilder.equal(root.get("status"),"3")
                        ,criteriaBuilder.equal(root.get("status"),"4")));
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        Pageable pageable = PageRequest.of(orderModel.getPage(), orderModel.getSize());
        Page<GOrderEntity> orderPage = orderDao.findAll(spec, pageable);
        List<GOrderEntity> content = orderPage.getContent();
        OrderModel[] models = transformOrder(content);
        return models;
    }

    @Override
    public String refuseOrder(OrderModel orderModel) {
        if (!"退款申请中".equals(orderModel.getStatus())){
            return "该订单未处于退款申请状态，无法进行该操作";
        }
        try {
            GOrderEntity orderEntity = orderDao.findById(Integer.parseInt(orderModel.getOrderId())).get();
            orderEntity.setStatus("2");
            orderDao.save(orderEntity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "出错啦，请联系系统管理员~";
        }
        return null;
    }

    @Override
    @Transactional
    public String agreeOrder(OrderModel orderModel) {
        if (!"退款申请中".equals(orderModel.getStatus())){
            return "该订单未处于退款申请状态，无法进行该操作";
        }
        try {
            double num = 0;
            for (GProductEntity product : orderModel.getProducts()) {
                num += product.getOriginalPrice();
            }
            //设置订单状态为已退款
            GOrderEntity orderEntity = orderDao.findById(Integer.parseInt(orderModel.getOrderId())).get();
            orderEntity.setStatus("4");
            orderDao.save(orderEntity);
            //回退用户余额
            GUserEntity userEntity = userDao.findByUsername(orderModel.getUsername());
            double balance = userEntity.getBalance();
            System.out.println("balance" + balance);
            System.out.println("num" + num);
            num += balance;
            userEntity.setBalance(num);
            userDao.save(userEntity);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "出错啦，请联系系统管理员~";
        }
        return null;
    }

    void setPayPattern(GOrderEntity orderEntity,String payPattern){
        if (payPattern != null) {
            if ("支付宝".equals(payPattern)) {
                orderEntity.setPayPattern("1");
            } else if ("微信".equals(payPattern)) {
                orderEntity.setPayPattern("2");
            } else if ("银行卡".equals(payPattern)) {
                orderEntity.setPayPattern("3");
            }
        } else {
            orderEntity.setPayPattern("0");
        }
    }
    private String transformPayPattern(String payPattern){
        if (payPattern==null){
            return null;
        }
        if (payPattern.length()>1){
            if ("支付宝".equals(payPattern)){
                return "1";
            }else if ("微信".equals(payPattern)){
                return "2";
            }else if ("银行卡".equals(payPattern)){
                return "3";
            }else if ("未支付".equals(payPattern)){
                return "0";
            }
        }else{
            if ("1".equals(payPattern)){
                return "支付宝";
            }else if ("2".equals(payPattern)){
                return "微信";
            }else if ("3".equals(payPattern)){
                return "银行卡";
            }else if ("0".equals(payPattern)){
                return "未支付";
            }
        }
        return null;
    }

    private String transformStatus(String status){
        if (null == status) {
            return null;
        }
        if (status.length()>1) {
            if ("正在处理".equals(status)){
                return "1";
            }else if ("拒绝退款".equals(status)){
                return "2";
            }else if ("退款申请中".equals(status)){
                return "3";
            }else if ("已退款".equals(status)){
                return "4";
            }else if ("已关闭".equals(status)){
                return "5";
            }else if ("待付款".equals(status)){
                return "0";
            }

        }else{
            if ("0".equals(status)) {
                return "待付款";
            }else if ("1".equals(status)){
                return "正在处理";
            }else if ("2".equals(status)){
                return "拒绝退款";
            }else if ("3".equals(status)){
                return "退款申请中";
            }else if ("4".equals(status)){
                return "已退款";
            }else if ("5".equals(status)){
                return "已关闭";
            }
        }
        return null;
    }
    public OrderModel[] transformOrder(List<GOrderEntity> content){
        int a = 0;
        OrderModel[] models = new OrderModel[content.size()];
        for (GOrderEntity entity : content) {
            OrderModel model = new OrderModel();
            model.setOrderId(entity.getOrderId()+"");
            model.setPayPattern(transformPayPattern(entity.getPayPattern()));
            model.setStatus(transformStatus(entity.getStatus()));
            model.setUsername(entity.getUser().getUsername());
            model.setUser(entity.getUser());
            List<GProductEntity> productEntities = new ArrayList<>();
            productEntities.addAll(entity.getProducts());
            model.setProducts(productEntities);
            model.setCreateTime(entity.getCreateTime().toString());
            model.setReturnReason(entity.getReturnReason());
            models[a++] = model;
        }
        return models;
    }

}
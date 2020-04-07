package cn.jx.ecjtu.gw.model;

import cn.jx.ecjtu.gw.entity.GProductEntity;
import cn.jx.ecjtu.gw.entity.GUserEntity;

import java.util.Arrays;
import java.util.List;

public class OrderModel {
    private String orderId;
    private String username;
    private String payPattern;
    private String status;
    private List<String> gameNames;
    private GUserEntity user;
    private List<GProductEntity> products;
    private int size;
    private int page;
    private String createTime;
    private String returnReason;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPayPattern() {
        return payPattern;
    }

    public void setPayPattern(String payPattern) {
        this.payPattern = payPattern;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getGameNames() {
        return gameNames;
    }

    public void setGameNames(List<String> gameNames) {
        this.gameNames = gameNames;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public GUserEntity getUser() {
        return user;
    }

    public void setUser(GUserEntity user) {
        this.user = user;
    }

    public List<GProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<GProductEntity> products) {
        this.products = products;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }
}
package cn.jx.ecjtu.gw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListWrapper<E> implements Serializable {
    private static final long serialVersionUID = -3431785316336840076L;

    private List<E> productModels = new ArrayList<>();
    private String msg;

    public List<E> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<E> productModels) {
        this.productModels = productModels;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ListWrapper{" +
                "productModels=" + productModels +
                ", msg='" + msg + '\'' +
                '}';
    }
}
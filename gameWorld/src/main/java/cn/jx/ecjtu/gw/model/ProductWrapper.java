package cn.jx.ecjtu.gw.model;

import cn.jx.ecjtu.gw.model.ProductModel;

import java.io.Serializable;
import java.util.List;

public class ProductWrapper implements Serializable {

    private static final long serialVersionUID = -2479155636208835298L;

    private ProductModel product;

    private List<ProductModel> products;

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }


    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductWrapper{" +
                "product=" + product +
                ", products=" + products +
                '}';
    }
}
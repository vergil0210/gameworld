package cn.jx.ecjtu.gw.model;

import java.io.Serializable;
import java.util.Arrays;

public class ProductModel implements Serializable {
    private static final long serialVersionUID = 2651625173226483187L;
    private String name;
    private String developer;
    private String publisher;
    private String publishDate;
    private boolean delivery;
    private String[] type = new String[10];
    private String price;
    private String desc;
    private int page;
    private int size;

    public ProductModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "name='" + name + '\'' +
                ", developer='" + developer + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", delivery=" + delivery +
                ", type=" + Arrays.toString(type) +
                ", price='" + price + '\'' +
                ", desc='" + desc + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
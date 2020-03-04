package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Administrator
 */
@Entity
@Table(name = "g_product", schema = "game_world")
public class GProductEntity {
    private int productId;
    private String name;
    private String desc;
    private double originalPrice;
    private Integer salesVolume;
    private String developers;
    private String publisher;
    private Double promotePrice;
    private int categoryId;
    private byte isOnSale;
    private byte isHot;
    private byte showInFront;

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "original_price")
    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Basic
    @Column(name = "sales_volume")
    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    @Basic
    @Column(name = "developers")
    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    @Basic
    @Column(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "promote_price")
    public Double getPromotePrice() {
        return promotePrice;
    }

    public void setPromotePrice(Double promotePrice) {
        this.promotePrice = promotePrice;
    }

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "is_on_sale")
    public byte getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(byte isOnSale) {
        this.isOnSale = isOnSale;
    }

    @Basic
    @Column(name = "is_hot")
    public byte getIsHot() {
        return isHot;
    }

    public void setIsHot(byte isHot) {
        this.isHot = isHot;
    }

    @Basic
    @Column(name = "show_in_front")
    public byte getShowInFront() {
        return showInFront;
    }

    public void setShowInFront(byte showInFront) {
        this.showInFront = showInFront;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GProductEntity that = (GProductEntity) o;
        return productId == that.productId &&
                Double.compare(that.originalPrice, originalPrice) == 0 &&
                categoryId == that.categoryId &&
                isOnSale == that.isOnSale &&
                isHot == that.isHot &&
                showInFront == that.showInFront &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(salesVolume, that.salesVolume) &&
                Objects.equals(developers, that.developers) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(promotePrice, that.promotePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, desc, originalPrice, salesVolume, developers, publisher, promotePrice, categoryId, isOnSale, isHot, showInFront);
    }
}
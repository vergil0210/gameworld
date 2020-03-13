package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "g_product", schema = "game_world", catalog = "")
public class GProductEntity {
    private int productId;
    private String name;
    private String desc;
    private double originalPrice;
    private Integer salesVolume;
    private String developer;
    private String publisher;
    private Double promotePrice;
    private byte isOnSale;
    private byte isHot;
    private byte showInFront;
    private Timestamp publishDate;
    private Set<GTypeEntity> types;

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "description")
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
    @Column(name = "developer")
    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
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

    @Basic
    @Column(name = "publish_date")
    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    @ManyToMany(targetEntity = GTypeEntity.class)
    @JoinTable(name = "g_product__type"
            ,joinColumns = @JoinColumn(name = "g_product_id")
            ,inverseJoinColumns = @JoinColumn(name = "g_type_id"))
    public Set<GTypeEntity> getTypes() {
        return types;
    }

    public void setTypes(Set<GTypeEntity> types) {
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GProductEntity that = (GProductEntity) o;
        return productId == that.productId &&
                Double.compare(that.originalPrice, originalPrice) == 0 &&
                isOnSale == that.isOnSale &&
                isHot == that.isHot &&
                showInFront == that.showInFront &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(salesVolume, that.salesVolume) &&
                Objects.equals(developer, that.developer) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(promotePrice, that.promotePrice) &&
                Objects.equals(publishDate, that.publishDate) &&
                Objects.equals(types,that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, desc, originalPrice, salesVolume, developer, publisher, promotePrice, isOnSale, isHot, showInFront, publishDate);
    }

}
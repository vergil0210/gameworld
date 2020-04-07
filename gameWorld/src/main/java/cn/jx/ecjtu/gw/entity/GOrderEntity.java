package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "g_order", schema = "game_world", catalog = "")
public class GOrderEntity {
    private int orderId;
    private Timestamp createTime;
    private String status;
    private String payPattern;
    private double totalPrice;
    private String returnReason;
    private Set<GProductEntity> products;
    private GUserEntity user;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "pay_pattern")
    public String getPayPattern() {
        return payPattern;
    }

    public void setPayPattern(String payPattern) {
        this.payPattern = payPattern;
    }

    @Column(name = "return_reason")
    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    @Basic
    @Column(name = "total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToMany(targetEntity = GProductEntity.class)
    @JoinTable(name = "g_order_items",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    public Set<GProductEntity> getProducts() {
        return products;
    }

    public void setProducts(Set<GProductEntity> products) {
        this.products = products;
    }

    @OneToOne(targetEntity = GUserEntity.class)
    @JoinColumn(name = "user_Id",referencedColumnName = "user_id")
    public GUserEntity getUser() {
        return user;
    }

    public void setUser(GUserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GOrderEntity that = (GOrderEntity) o;
        return orderId == that.orderId &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, createTime, status);
    }
}
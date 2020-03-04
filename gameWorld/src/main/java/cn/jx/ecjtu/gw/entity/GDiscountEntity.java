package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "g_discount", schema = "game_world", catalog = "")
public class GDiscountEntity {
    private int discountId;
    private int type;
    private double value;
    private Double quantityDemanded;

    @Id
    @Column(name = "discount_id")
    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "value")
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "quantity_demanded")
    public Double getQuantityDemanded() {
        return quantityDemanded;
    }

    public void setQuantityDemanded(Double quantityDemanded) {
        this.quantityDemanded = quantityDemanded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GDiscountEntity that = (GDiscountEntity) o;
        return discountId == that.discountId &&
                type == that.type &&
                Double.compare(that.value, value) == 0 &&
                Objects.equals(quantityDemanded, that.quantityDemanded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountId, type, value, quantityDemanded);
    }
}
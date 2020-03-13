package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "g_product_attribute", schema = "game_world", catalog = "")
public class GProductAttributeEntity {
    private int gProdAttriId;
    private String name;
    private String value;

    @Id
    @Column(name = "g_prod_attri_id")
    public int getgProdAttriId() {
        return gProdAttriId;
    }

    public void setgProdAttriId(int gProdAttriId) {
        this.gProdAttriId = gProdAttriId;
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
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GProductAttributeEntity that = (GProductAttributeEntity) o;
        return gProdAttriId == that.gProdAttriId &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gProdAttriId, name, value);
    }
}
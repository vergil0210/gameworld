package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "g_category", schema = "game_world", catalog = "")
public class GCategoryEntity {
    private int categoryId;
    private String name;

    @Id
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GCategoryEntity that = (GCategoryEntity) o;
        return categoryId == that.categoryId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name);
    }
}
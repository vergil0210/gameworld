package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "g_type", schema = "game_world", catalog = "")
public class GTypeEntity {

    public GTypeEntity() {
    }

    public GTypeEntity(String name) {
        this.name = name;
    }

    private int typeId;
    private String name;

    @Id
    @Column(name = "type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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
        GTypeEntity that = (GTypeEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, name);
    }

    @Override
    public String toString() {
        return "GTypeEntity{" +
                "typeId=" + typeId +
                ", name='" + name + '\'' +
                '}';
    }
}
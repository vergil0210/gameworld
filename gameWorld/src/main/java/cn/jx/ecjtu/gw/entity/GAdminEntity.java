package cn.jx.ecjtu.gw.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "g_admin", schema = "game_world", catalog = "")
public class GAdminEntity {
    private String adminId;
    private String gPassword;
    private String gPermissionLevel;
    private String gUsername;

    @Id
    @Column(name = "admin_id")
    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "g_password")
    public String getgPassword() {
        return gPassword;
    }

    public void setgPassword(String gPassword) {
        this.gPassword = gPassword;
    }

    @Basic
    @Column(name = "g_permission_level")
    public String getgPermissionLevel() {
        return gPermissionLevel;
    }

    public void setgPermissionLevel(String gPermissionLevel) {
        this.gPermissionLevel = gPermissionLevel;
    }

    @Basic
    @Column(name = "g_username")
    public String getgUsername() {
        return gUsername;
    }

    public void setgUsername(String gUsername) {
        this.gUsername = gUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GAdminEntity that = (GAdminEntity) o;
        return Objects.equals(adminId, that.adminId) &&
                Objects.equals(gPassword, that.gPassword) &&
                Objects.equals(gPermissionLevel, that.gPermissionLevel) &&
                Objects.equals(gUsername, that.gUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminId, gPassword, gPermissionLevel, gUsername);
    }
}
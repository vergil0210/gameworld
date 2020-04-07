package cn.jx.ecjtu.gw.dao;

import cn.jx.ecjtu.gw.entity.GAdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao extends JpaRepository<GAdminEntity,String>, JpaSpecificationExecutor<GAdminEntity> {

}

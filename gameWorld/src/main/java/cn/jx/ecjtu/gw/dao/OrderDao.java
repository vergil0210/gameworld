package cn.jx.ecjtu.gw.dao;

import cn.jx.ecjtu.gw.entity.GProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDao extends JpaSpecificationExecutor<GProductEntity>, JpaRepository<GProductEntity,Integer> {
}

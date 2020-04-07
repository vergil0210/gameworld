package cn.jx.ecjtu.gw.dao;

import cn.jx.ecjtu.gw.entity.GOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaSpecificationExecutor<GOrderEntity>, JpaRepository<GOrderEntity,Integer> {



}

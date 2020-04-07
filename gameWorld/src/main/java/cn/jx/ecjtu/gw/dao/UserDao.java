package cn.jx.ecjtu.gw.dao;

import cn.jx.ecjtu.gw.entity.GUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<GUserEntity,Integer>, JpaSpecificationExecutor<GUserEntity> {

    GUserEntity findByUsername(String username);
}

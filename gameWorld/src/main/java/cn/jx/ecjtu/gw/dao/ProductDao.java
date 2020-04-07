package cn.jx.ecjtu.gw.dao;

import cn.jx.ecjtu.gw.entity.GProductEntity;
import cn.jx.ecjtu.gw.entity.GTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductDao extends JpaSpecificationExecutor<GProductEntity>, JpaRepository<GProductEntity,Integer> {


    /**
     * @return 查询包含该 名称字段、发行商、开发商的游戏
     */
//    List<GProductEntity> findAllByNameContainingAndPublisherAndDeveloperAndTypes
//        (String name, String publisher, String developer, Set<GTypeEntity> types);
    List<GProductEntity> findByTypesContaining(String type);

    GProductEntity findByName(String name);


}

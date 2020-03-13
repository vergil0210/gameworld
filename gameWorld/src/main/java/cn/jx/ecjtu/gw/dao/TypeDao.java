package cn.jx.ecjtu.gw.dao;

import cn.jx.ecjtu.gw.entity.GTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDao extends JpaRepository<GTypeEntity,Integer>, JpaSpecificationExecutor<GTypeEntity> {

    /**
     * @return 查询所有
     */
    @Override
    List<GTypeEntity> findAll();

    @Query(value = "select * from g_product as g \n" +
            "inner join g_product__type pt on g.product_id=pt.g_product_id\n" +
            "inner join g_type t on t.type_id = pt.g_type_id\n", nativeQuery = true)
    List<String> findIt();

}

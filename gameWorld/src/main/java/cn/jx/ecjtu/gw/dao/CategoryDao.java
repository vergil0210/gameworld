package cn.jx.ecjtu.gw.dao;

import cn.jx.ecjtu.gw.entity.Category;
import cn.jx.ecjtu.gw.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer>, JpaSpecificationExecutor {

    @Query("select name from Category ")
    List<String> findAllNames();
}

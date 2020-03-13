package cn.jx.ecjtu.gameWorld.dao;

import cn.jx.ecjtu.gameWorld.*;
import cn.jx.ecjtu.gw.GameWorldApplication;
import cn.jx.ecjtu.gw.dao.ProductDao;
import cn.jx.ecjtu.gw.dao.TypeDao;
import cn.jx.ecjtu.gw.entity.GProductEntity;
import cn.jx.ecjtu.gw.entity.GTypeEntity;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameWorldApplication.class)
public class DaoTest {

    @Autowired
    TypeDao typeDao;
    @Autowired
    ProductDao productDao;

    @Test
    @Transactional
    public void test01(){
        PageRequest page = PageRequest.of(0, 1);

        Specification<GProductEntity> spec = new Specification<GProductEntity>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Join<GProductEntity,GTypeEntity> join = root.join("types",JoinType.INNER);
                Predicate equal = criteriaBuilder.equal(join.get("name"), "冒险");
                return equal;
            }
        };
        PageRequest pageable = PageRequest.of(0, 5);
        Page<GProductEntity> all = productDao.findAll(spec, pageable);

        System.out.println(all);
    }

    @Test
    @Transactional
    public void test02(){
//        List<GTypeEntity> all = typeDao.findAll();
//        List<GProductEntity> all = productDao.findAll();
//        for (GProductEntity productEntity : all) {
//            System.out.println(productEntity.getTypes());
//        }
//        GProductEntity searchEntity = productDao.findByName("ASTRONEER");
//        System.out.println(searchEntity);
        Timestamp timestamp = Timestamp.valueOf("2020-03-06");
        System.out.println(timestamp);
    }

}
package cn.jx.ecjtu.gameWorld.dao;

import cn.jx.ecjtu.gw.GameWorldApplication;
import cn.jx.ecjtu.gw.dao.CategoryDao;
import cn.jx.ecjtu.gw.entity.Category;
import cn.jx.ecjtu.gw.entity.Product;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = GameWorldApplication.class)
@RunWith(SpringRunner.class)
public class Test {
    @Autowired
    private CategoryDao categoryDao;

    @org.junit.Test
    public void CategoryDaoTest(){
//        List<Category> all = categoryDao.findAll();
//        System.out.println(all);
        List allNames = categoryDao.findAllNames();
        System.out.println(allNames);
    }
}

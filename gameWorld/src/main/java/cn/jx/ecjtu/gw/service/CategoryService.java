package cn.jx.ecjtu.gw.service;

import cn.jx.ecjtu.gw.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;
    public List<String> getCategories(){
        return categoryDao.findAllNames();
    }
}

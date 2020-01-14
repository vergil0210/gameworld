package cn.jx.ecjtu.gw.controller;

import cn.jx.ecjtu.gw.entity.Category;
import cn.jx.ecjtu.gw.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/homePage")
public class HomePageController {

    @Autowired
    private CategoryService categoryService;


    /**
     * @return 返回所有游戏类型的集合
     */
    @GetMapping("/getCategories")
    public List<String> getCategories(){
        return categoryService.getCategories();
    }

}

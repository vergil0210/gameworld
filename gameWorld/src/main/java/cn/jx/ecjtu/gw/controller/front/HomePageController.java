package cn.jx.ecjtu.gw.controller.front;

import cn.jx.ecjtu.gw.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/homePage")
public class HomePageController {

    @Autowired
    ITypeService typeService;

    /**
     * @return 返回所有游戏类型的集合
     */
    @GetMapping("/types")
    public String[] getCategories(){
        return typeService.getAllTypes();
    }

}

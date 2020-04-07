package cn.jx.ecjtu.gw.controller.background;

import cn.jx.ecjtu.gw.entity.GProductEntity;
import cn.jx.ecjtu.gw.model.ListWrapper;
import cn.jx.ecjtu.gw.model.ProductModel;
import cn.jx.ecjtu.gw.model.ProductWrapper;
import cn.jx.ecjtu.gw.model.TypeModel;
import cn.jx.ecjtu.gw.service.IProductService;
import cn.jx.ecjtu.gw.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ITypeService typeService;
    @Autowired
    IProductService productService;

    @GetMapping("/getTest")
    public String getTest(String str){
        System.out.println(str);
        return "success";
    }

    @PostMapping("/testPost1")
    public String testPost1(@RequestBody String paramName) {
        System.out.println("paramName:" + paramName);
        return paramName;
    }
    @GetMapping("getAllNames")
    public List<String> getAllNames() {
        return productService.findAllNames();
    }

    @GetMapping("/getAllTypes")
    public String[] getAllTypes(){
        return typeService.getAllTypes();
    }

    @GetMapping("getPublishers")
    public Set<String> getPublishers(){
        return productService.findAllPublishers();
    }

    @GetMapping("getDevelopers")
    public Set<String> getDevelopers(){
        return productService.findAllDevelopers();
    }

    /**
     *
     * @param product productModel的包装类，用于方便绑定json数据
     * @return 如果参数正确则返回success ，不正确则返回错误
     */
    @PostMapping("addProduct")
    public String addProduct(@RequestBody ProductWrapper product){
        ProductModel productModel = product.getProduct();
        if (productModel!=null){
            System.out.println(productModel);
            //调用service

            String msg = productService.addProduct(productModel);

            return msg;
        }
        return "params is empty";
    }
    @PostMapping("searchProduct")
    public ProductModel[] searchProduct(@RequestBody ProductWrapper product){
        ProductModel productModel = product.getProduct();
        if (productModel!=null) {
            System.out.println(productService.findProductByCondition(productModel));
            return productService.findProductByCondition(productModel);
        }
        return null;
    }

    @PostMapping("deleteOneByName")
    public String deleteOneByName(@RequestBody ProductWrapper product){
        String msg = productService.deleteOneByName(product.getProduct().getName());
        return msg;
    }
    @PostMapping("updateProduct")
    public String updateProduct(@RequestBody List<ProductModel> productModels){
        return productService.updateProducts(productModels);
    }

    @PostMapping("addType")
    public String addType(@RequestBody TypeModel type){
        return typeService.addType(type.getType());
    }



}
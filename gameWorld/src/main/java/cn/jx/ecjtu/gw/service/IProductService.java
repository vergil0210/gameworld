package cn.jx.ecjtu.gw.service;

import cn.jx.ecjtu.gw.model.ProductModel;

import java.util.List;
import java.util.Set;

/**
 * @author Administrator
 */
public interface IProductService {

    /**
     * @param productModel
     * @return msg 异常问题信息 或 成功
     */
    String addProduct(ProductModel productModel);


    /**
     * @return 发行商集合
     */
    Set<String> findAllPublishers();

    /**
     * @return 开发商集合
     */
    Set<String> findAllDevelopers();

    /**
     * 通过前台传入的输入条件返回结果
     * @param productModel 前台传入的model对象
     * @return  实体类集合
     */
    ProductModel[] findProductByCondition(ProductModel productModel);

    String deleteOneByName(String name);

    String updateProducts(List<ProductModel> productModels);
}

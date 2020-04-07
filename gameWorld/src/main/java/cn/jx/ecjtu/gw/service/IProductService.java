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

    /**
     * 通过输入条件删除product
     * @param name 传入游戏名
     * @return 返回删除结果
     */
    String deleteOneByName(String name);

    /**
     * 根据productModel的name属性更新product
     * @param productModels
     * @return
     */
    String updateProducts(List<ProductModel> productModels);

    /**
     * @return 返回所有游戏名
     */
    List<String> findAllNames();
}

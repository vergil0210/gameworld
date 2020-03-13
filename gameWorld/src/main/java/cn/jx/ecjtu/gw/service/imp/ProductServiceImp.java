package cn.jx.ecjtu.gw.service.imp;

import cn.jx.ecjtu.gw.dao.ProductDao;
import cn.jx.ecjtu.gw.dao.TypeDao;
import cn.jx.ecjtu.gw.entity.GProductEntity;
import cn.jx.ecjtu.gw.entity.GTypeEntity;
import cn.jx.ecjtu.gw.model.ProductModel;
import cn.jx.ecjtu.gw.service.IProductService;
import cn.jx.ecjtu.gw.util.ProductUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.swing.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    ProductDao productDao;
    @Autowired
    TypeDao typeDao;

    @Override
    @Transactional
    public String addProduct(ProductModel productModel){
        System.out.println(productModel);
        GProductEntity productEntity = new GProductEntity();
        productEntity.setName(productModel.getName());
        productEntity.setPublisher(productModel.getPublisher());
        productEntity.setDeveloper(productModel.getDeveloper());
        productEntity.setIsOnSale(productModel.isDelivery() ? (byte)1 : (byte)0);
        //规范时间格式为df2
//            if (!StringUtils.isEmpty(productModel.getPublishDate())){
//                DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
//                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                productEntity.setPublishDate(Timestamp.valueOf(df2.format(df1.parse(productModel.getPublishDate()))));
//            } catch (ParseException e) {
//                e.printStackTrace();
//                try {
//                    productEntity.setPublishDate(Timestamp.valueOf(productModel.getPublishDate()));
//                } catch (ParseException e2) {
//                    e.printStackTrace();
//
//                }
//            }
//        }
        //判断 如果isDelivery为true则将上线时间设置为当前时间 （即 立即上线）
        if (productModel.isDelivery()){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            productEntity.setPublishDate(Timestamp.valueOf(df.format(System.currentTimeMillis())));
        }else {
            if (ProductUtil.isValidDate(productModel.getPublishDate())){
                productEntity.setPublishDate(Timestamp.valueOf(productModel.getPublishDate()));
            }else {
                return "日期格式不匹配,规定格式为 yyyy-MM-dd HH:mm:ss,获取数据为：" + productModel.getPublishDate();
            }
        }
        productEntity.setOriginalPrice(Float.parseFloat(productModel.getPrice()));
        productEntity.setDesc(productModel.getDesc());
        //插入游戏类型集合
        Set<GTypeEntity> types = new HashSet<GTypeEntity>();
        List<GTypeEntity> baseTypes = typeDao.findAll();
        String[] modelTypes = productModel.getType();
        for (String modelType : modelTypes) {
            for (GTypeEntity baseType : baseTypes) {
                if (baseType.getName().equals(modelType)){
                    types.add(baseType);
                    continue;
                }
            }
        }

//        for (String type : productModel.getType()) {
//            GTypeEntity typeEntity = new GTypeEntity(type);
//            types.add(typeEntity);
//        }

        productEntity.setTypes(types);
        //插入g_product表
        System.out.println("entity"+productEntity);
        GProductEntity save;
        try{
            save = productDao.save(productEntity);
        }catch (RuntimeException e){
            e.printStackTrace();
            return "游戏插入异常";
        }
        System.out.println("save = " + save);

        return "添加成功！";
    }

    @Override
    public Set<String> findAllPublishers() {
        List<GProductEntity> products = productDao.findAll();
        Set<String> set = new HashSet<String>();
        for (GProductEntity product : products) {
            set.add(product.getPublisher());
        }
        return set;
    }

    @Override
    public Set<String> findAllDevelopers() {
        List<GProductEntity> products = productDao.findAll();
        Set<String> set = new HashSet<String>();
        for (GProductEntity product : products) {
            set.add(product.getDeveloper());
        }
        return set;
    }

    @Override
    @Transactional
    public ProductModel[] findProductByCondition(ProductModel productModel) {
        Specification<GProductEntity> spec = new Specification<GProductEntity>() {
            //设置查询条件
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(productModel.getName())){
                    //按游戏名筛选
                    list.add(criteriaBuilder.like(root.get("name"),"%"+productModel.getName()+"%"));
                }
                if (!StringUtils.isEmpty(productModel.getPublisher())){
                    //按发行商筛选
                    list.add(criteriaBuilder.equal(root.get("publisher"), productModel.getPublisher()));
                    System.out.println("p1 add ...");
                }
                if (!StringUtils.isEmpty(productModel.getDeveloper())){
                    //按开发商筛选
                    list.add(criteriaBuilder.equal(root.get("developer"), productModel.getDeveloper()));
                    System.out.println("p2 add ...");
                }
                if (productModel.getType()!=null && productModel.getType().length>0){
                    //按游戏类型筛选
                    Join<GProductEntity,GTypeEntity> join = root.join("types",JoinType.INNER);

                    list.add(criteriaBuilder.equal(join.get("name"), productModel.getType()[0]));
                    System.out.println("p3 add ...");
                }
                //返回筛选条件
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
        //创建分页对象
        Pageable pageable = PageRequest.of(productModel.getPage(), productModel.getSize());
        Page<GProductEntity> pages = productDao.findAll(spec,pageable);
        List<GProductEntity> list = pages.getContent();
        ProductModel[] models = new ProductModel[list.size()];
        for (int i = 0; i < list.size(); i++) {
            models[i] = ProductUtil.transformProduct(list.get(i));
        }
        return models;
    }

    @Override
    @Transactional
    public String deleteOneByName(String name) {

        try{
            GProductEntity entity = productDao.findOne(new Specification<GProductEntity>() {
                @Override
                public Predicate toPredicate(Root<GProductEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    if (!StringUtils.isEmpty(name)) {
                        Predicate pre = criteriaBuilder.equal(root.get("name"), name);
                        return pre;
                    }
                    return null;
                }
            }).get();
            productDao.delete(entity);
        } catch (RuntimeException e){
            e.printStackTrace();
            return "delete failed";
        }
        return "delete success";
    }

    @Override
    @Transactional
    public String updateProducts(List<ProductModel> productModels) {
        try {
            for (ProductModel model : productModels) {
                GProductEntity searchEntity = productDao.findByName(model.getName());
                GProductEntity resultEntity = new GProductEntity();
                ProductUtil.transformProduct(model, resultEntity);
                resultEntity.setProductId(searchEntity.getProductId());
                resultEntity.setTypes(searchEntity.getTypes());
                productDao.save(resultEntity);
            }
        } catch (RuntimeException e){
            e.printStackTrace();
            return "修改异常,请联系后台管理员";
        }

        return "修改成功";
    }


}
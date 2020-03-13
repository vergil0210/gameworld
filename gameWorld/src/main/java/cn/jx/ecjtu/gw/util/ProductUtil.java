package cn.jx.ecjtu.gw.util;

import cn.jx.ecjtu.gw.entity.GProductEntity;
import cn.jx.ecjtu.gw.entity.GTypeEntity;
import cn.jx.ecjtu.gw.model.ProductModel;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  和product相关的工具类
 */public class ProductUtil {
    public static ProductModel transformProduct(GProductEntity entity){
        ProductModel model = new ProductModel();
        if (!StringUtils.isEmpty(entity.getDeveloper())){
            model.setDeveloper(entity.getDeveloper());
        }
        if (!StringUtils.isEmpty(entity.getPublisher())){
            model.setPublisher(entity.getPublisher());
        }
        if (!StringUtils.isEmpty(entity.getDesc())){
            model.setDesc(entity.getDesc());
        }
        if (!StringUtils.isEmpty(entity.getIsOnSale()+"")){
            model.setDelivery(entity.getIsOnSale()==1);
        }
        if (!StringUtils.isEmpty(entity.getName())) {
            model.setName(entity.getName());
        }
        if (entity.getPublishDate()!=null){
            model.setPublishDate(entity.getPublishDate().toString());
        }
        if (entity.getTypes()!=null && entity.getTypes().size()>0){
            List<GTypeEntity> list = new ArrayList<>(entity.getTypes().size());
            list.addAll(entity.getTypes());
            for (int i = 0; i <list.size(); i++) {
                model.getType()[i] = list.get(i).getName();
            }
        }
        if (entity.getOriginalPrice()!=0){
            model.setPrice(""+entity.getOriginalPrice());
        }
        return model;
    }
    public static GProductEntity transformProduct(ProductModel model,GProductEntity entity){
        if (!StringUtils.isEmpty(model.getDeveloper())){
            entity.setDeveloper(model.getDeveloper());
        }
        if (!StringUtils.isEmpty(model.getPublisher())){
            entity.setPublisher(model.getPublisher());
        }
        if (!StringUtils.isEmpty(model.getDesc())){
            entity.setDesc(model.getDesc());
        }
        if (model.isDelivery()){
            entity.setIsOnSale((byte) 1);
        }else {
            entity.setIsOnSale((byte) 2);
        }
        if (!StringUtils.isEmpty(model.getName())) {
            entity.setName(model.getName());
        }
        if (model.getPublishDate()!=null){
            entity.setPublishDate(Timestamp.valueOf(model.getPublishDate()));
        }
        if (model.getType()!=null && model.getType().length>0){
            Set<GTypeEntity> set = new HashSet<>();
            for (String type : model.getType()) {
                set.add(new GTypeEntity(type));
            }
            entity.setTypes(set);
        }
        if (!StringUtils.isEmpty(model.getPrice())){
            entity.setOriginalPrice(Double.parseDouble(model.getPrice()));
        }
        return entity;
    }

    public static boolean isValidDate(String str){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return str.equals(dateFormat.format(dateFormat.parse(str)));
        } catch (ParseException e) {
            return false;
        }
    }
}
package cn.jx.ecjtu.gw.service.imp;

import cn.jx.ecjtu.gw.dao.TypeDao;
import cn.jx.ecjtu.gw.entity.GTypeEntity;
import cn.jx.ecjtu.gw.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImp implements ITypeService {

    @Autowired
    TypeDao typeDao;

    @Override
    public String[] getAllTypes() {
        List<GTypeEntity> typeEntities = typeDao.findAll();
        String[] types = new String[typeEntities.size()];
        for (int i = 0; i < typeEntities.size(); i++) {
            types[i] = typeEntities.get(i).getName();
        }
        return types;
    }

    @Override
    public String addType(String name) {
        try {
            GTypeEntity type = new GTypeEntity();
            type.setName(name);
            typeDao.save(type);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "添加失败";
        }
        return "添加成功";
    }


}
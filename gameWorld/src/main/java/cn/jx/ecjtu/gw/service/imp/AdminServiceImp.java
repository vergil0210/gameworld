package cn.jx.ecjtu.gw.service.imp;

import cn.jx.ecjtu.gw.dao.AdminDao;
import cn.jx.ecjtu.gw.entity.GAdminEntity;
import cn.jx.ecjtu.gw.model.AdminModel;
import cn.jx.ecjtu.gw.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImp implements IAdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public List<AdminModel> searchAdminByName(String name) {
        List<GAdminEntity> list = adminDao.findAll();
        List<AdminModel> models = new ArrayList<>(list.size());
        for (GAdminEntity adminEntity : list) {
            AdminModel model = new AdminModel();
            model.setId(Integer.parseInt(adminEntity.getAdminId()));
            model.setRole(adminEntity.getgPermissionLevel());
            model.setUsername(adminEntity.getgUsername());
            models.add(model);
        }
        return models;
    }

    @Override
    public String updateAdmin(AdminModel adminModel) {
        Optional<GAdminEntity> opt = adminDao.findById(adminModel.getId() + "");
        if (!opt.isPresent()) return "该用户不存在，请联系管理员";
        GAdminEntity adminEntity = opt.get();
        adminEntity.setgPermissionLevel(adminModel.getRole());
        adminDao.save(adminEntity);
        return null;
    }
}
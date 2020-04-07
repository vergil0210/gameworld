package cn.jx.ecjtu.gw.service;

import cn.jx.ecjtu.gw.model.AdminModel;

import java.util.List;

public interface IAdminService {
    List<AdminModel> searchAdminByName(String username);

    String updateAdmin(AdminModel adminModel);
}

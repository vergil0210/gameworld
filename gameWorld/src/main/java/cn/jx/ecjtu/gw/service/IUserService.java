package cn.jx.ecjtu.gw.service;

import cn.jx.ecjtu.gw.model.AdminModel;
import cn.jx.ecjtu.gw.model.Response;

public interface IUserService {
    public Response<AdminModel> adminLogin(AdminModel adminModel);

    Response<Integer> adminReg(AdminModel adminModel);
}

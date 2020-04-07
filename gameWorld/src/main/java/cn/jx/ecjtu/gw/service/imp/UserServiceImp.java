package cn.jx.ecjtu.gw.service.imp;

import cn.jx.ecjtu.gw.dao.AdminDao;
import cn.jx.ecjtu.gw.entity.GAdminEntity;
import cn.jx.ecjtu.gw.model.AdminModel;
import cn.jx.ecjtu.gw.model.Response;
import cn.jx.ecjtu.gw.service.IUserService;
import cn.jx.ecjtu.gw.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    AdminDao adminDao;
    @Autowired
    Random random;

    @Override
    public Response<AdminModel> adminLogin(AdminModel adminModel) {
        Response<AdminModel> response = new Response<>();
        AdminModel respAdmin = new AdminModel();
        String msg = null;
        int id = adminModel.getId();
        String password = adminModel.getPassword();
        //验证输入参数
        if (id == 0 || password==null || "".equals(password)){
            msg = "输入有误，请重新确认";
            response.setMsg(msg);
            return response;
        }
        Optional<GAdminEntity> opt = adminDao.findById(adminModel.getId() + "");
        //验证该用户是否存在
        if (!opt.isPresent()) {
            msg = "该账号不存在";
            response.setMsg(msg);
            return response;
        }
        GAdminEntity adminEntity = opt.get();
        //传入Token
        if (adminEntity.getgPassword().equals(password)){
            String token = JwtUtil.sign(adminModel);
            response.setToken(token);
        }else {
            msg = "用户名及密码不匹配";
            response.setMsg(msg);
            return response;
        }
        //传入用户信息
        respAdmin.setId(Integer.parseInt(adminEntity.getAdminId()));
        respAdmin.setUsername(adminEntity.getgUsername());
        respAdmin.setRole(adminEntity.getgPermissionLevel());
        response.setData(respAdmin);
        response.setMsg(msg);
        return response;
    }

    @Override
    public Response<Integer> adminReg(AdminModel adminModel) {
        Response<Integer> resp = null;
        try {
            String username = adminModel.getUsername();
            String password = adminModel.getPassword();
            int id = generateId();
            GAdminEntity adminEntity = new GAdminEntity();
            adminEntity.setAdminId(id+"");
            adminEntity.setgUsername(username);
            adminEntity.setgPassword(password);
            //默认创建T2d等级的管理员
            adminEntity.setgPermissionLevel("T2");
            adminDao.save(adminEntity);
            resp = new Response<Integer>();
            resp.setData(id);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setMsg("注册出错，请联系管理员");
            return resp;
        }
        return resp;
    }

    private int generateId(){
        int id = random.nextInt(999999);
        Optional<GAdminEntity> opt = adminDao.findById(id + "");
        if (opt.isPresent()) generateId();
        return id;
    }
}
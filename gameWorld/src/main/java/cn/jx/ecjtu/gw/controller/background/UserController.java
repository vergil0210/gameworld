package cn.jx.ecjtu.gw.controller.background;

import cn.jx.ecjtu.gw.model.AdminModel;
import cn.jx.ecjtu.gw.model.Response;
import cn.jx.ecjtu.gw.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("adminLogin")
    public Response<AdminModel> adminLogin(@RequestBody AdminModel adminModel){
        Response<AdminModel> resp = userService.adminLogin(adminModel);
        return resp;
    }

    @PostMapping("adminRegister")
    public Response<Integer> adminRegister(@RequestBody AdminModel adminModel){

        return userService.adminReg(adminModel);
    }
}
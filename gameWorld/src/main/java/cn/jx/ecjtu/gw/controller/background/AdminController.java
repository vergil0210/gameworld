package cn.jx.ecjtu.gw.controller.background;

import cn.jx.ecjtu.gw.entity.GAdminEntity;
import cn.jx.ecjtu.gw.model.AdminModel;
import cn.jx.ecjtu.gw.model.UsernameWrapper;
import cn.jx.ecjtu.gw.service.IAdminService;
import cn.jx.ecjtu.gw.service.imp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    IAdminService adminService;

    @PostMapping("searchAdmin")
    public List<AdminModel> searchAdmin(@RequestBody UsernameWrapper wrapper){
        return adminService.searchAdminByName(wrapper.getUsername());
    }

    @PostMapping("updateAuth")
    public String updateAuth(@RequestBody AdminModel adminModel){
        return adminService.updateAdmin(adminModel);
    }
}
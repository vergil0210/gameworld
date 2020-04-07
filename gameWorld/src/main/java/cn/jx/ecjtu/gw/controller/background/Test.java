package cn.jx.ecjtu.gw.controller.background;

import cn.jx.ecjtu.gw.model.AdminModel;
import cn.jx.ecjtu.gw.util.JwtUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(2);
//        System.out.println(list);
//        Set<Integer> set = new HashSet<>();
//        set.addAll(list);
//        System.out.println(set);
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(System.currentTimeMillis()));
//        AdminModel model = new AdminModel();
//        model.setUsername("vergil");
//        model.setPassword("123123");
//        model.setRole("管理员");
//        model.setId(1998);
//        String token = null;
//        token = JwtUtil.sign(model);
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            JwtUtil.verify(token);
//            System.out.println("token: " + token);
//            System.out.println("共消耗时间: " + (System.currentTimeMillis()-startTime));
//            Thread.sleep(1000);
        Random random = new Random();
        for (int i = 0; i < 5; i++) {

        int j = random.nextInt(999999);
        System.out.println(j);
        }
    }
}
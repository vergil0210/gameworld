package cn.jx.ecjtu.gw.controller.background;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(2);
//        System.out.println(list);
//        Set<Integer> set = new HashSet<>();
//        set.addAll(list);
//        System.out.println(set);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(System.currentTimeMillis()));
    }
}
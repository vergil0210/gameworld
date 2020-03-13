package cn.jx.ecjtu.gw.service;

/**
 * @author Administrator
 */
public interface ITypeService {

    /**
     * @return 获取所有游戏类型的数组
     */
    String[] getAllTypes();

    String addType(String name);
}

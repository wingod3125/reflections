package biz.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import entity.Admin;
import biz.AdminBiz;

public class AdminBizImpl implements AdminBiz {
    AdminDao dao = new AdminDaoImpl();

    @Override
    public int login(String userName, String pwd) {
        // 调用底层方法实现登录功能
        return dao.login(userName, pwd);
    }

    @Override
    public boolean isUsedUserName(String userName) {
        // 判断该用户名是否被占用
        return dao.isUsedUserName(userName);
    }

    @Override
    public int save(Admin admin) {
        // 添加新的用户信息
        return dao.save(admin);
    }

}

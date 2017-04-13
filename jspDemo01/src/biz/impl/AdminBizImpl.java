package biz.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import entity.Admin;
import biz.AdminBiz;

public class AdminBizImpl implements AdminBiz {
    AdminDao dao = new AdminDaoImpl();

    @Override
    public int login(String userName, String pwd) {
        // ���õײ㷽��ʵ�ֵ�¼����
        return dao.login(userName, pwd);
    }

    @Override
    public boolean isUsedUserName(String userName) {
        // �жϸ��û����Ƿ�ռ��
        return dao.isUsedUserName(userName);
    }

    @Override
    public int save(Admin admin) {
        // ����µ��û���Ϣ
        return dao.save(admin);
    }

}

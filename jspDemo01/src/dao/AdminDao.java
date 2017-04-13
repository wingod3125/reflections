package dao;

import java.util.List;

import util.PageUtil;
import entity.Admin;

/**
 * ���ݷ��ʽӿڣ�ֻԼ������
 * 
 * @author Administrator
 * 
 */
public interface AdminDao {
    int login(String userName, String pwd);

    boolean isUsedUserName(String userName);

    int save(Admin admin);

    PageUtil<Admin> getAdminPage(int pageIndex, int pageSize);
}

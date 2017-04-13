package dao;

import java.util.List;

import util.PageUtil;
import entity.Admin;

/**
 * 数据访问接口，只约定方法
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

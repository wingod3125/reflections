package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.PageUtil;
import dao.AdminDao;
import dao.BaseDao;
import entity.Admin;

/**
 * 数据访问实现类，针对某个数据库实现; 比如说，这个类只针对MySQL数据库进行实现;
 * 
 * @author Administrator
 * 
 */

public class AdminDaoImpl implements AdminDao {

    /**
     * 判断用户名和密码是否正确
     */
    @Override
    public int login(String userName, String pwd) {
        Connection conn = BaseDao.getConnection();
        String sql = "select count(*) from admin where userName=? and password=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return 0;
    }

    /**
     * 判断该用户名是否被占用
     */
    @Override
    public boolean isUsedUserName(String userName) {
        int result = 0;
        Connection conn = BaseDao.getConnection();
        String sql = "select count(*) from admin where userName=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return result > 0;
    }

    /**
     * 保存用户信息
     */
    @Override
    public int save(Admin admin) {
        String sql = "insert into admin values(null,?,?)";
        return BaseDao.executeUpdate(sql, admin.getUserName(),
                admin.getPassword());
    }

    /**
     * 分页查询用户信息
     */
    @Override
    public PageUtil<Admin> getAdminPage(int pageIndex, int pageSize) {
        PageUtil<Admin> pu = new PageUtil<>();
        return pu;
    }

}

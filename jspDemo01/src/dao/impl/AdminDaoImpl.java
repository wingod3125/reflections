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
 * ���ݷ���ʵ���࣬���ĳ�����ݿ�ʵ��; ����˵�������ֻ���MySQL���ݿ����ʵ��;
 * 
 * @author Administrator
 * 
 */

public class AdminDaoImpl implements AdminDao {

    /**
     * �ж��û����������Ƿ���ȷ
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
     * �жϸ��û����Ƿ�ռ��
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
     * �����û���Ϣ
     */
    @Override
    public int save(Admin admin) {
        String sql = "insert into admin values(null,?,?)";
        return BaseDao.executeUpdate(sql, admin.getUserName(),
                admin.getPassword());
    }

    /**
     * ��ҳ��ѯ�û���Ϣ
     */
    @Override
    public PageUtil<Admin> getAdminPage(int pageIndex, int pageSize) {
        PageUtil<Admin> pu = new PageUtil<>();
        return pu;
    }

}

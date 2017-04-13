package dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.GradeDao;
import entity.Grade;

public class GradeDaoImpl implements GradeDao {

    /**
     * 通过ID查询年级信息
     */
    @Override
    public Grade findById(int id) {
        Grade g = new Grade();
        Connection conn = BaseDao.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from grade where id=?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                g.setGid(rs.getInt(1));
                g.setGname(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }

        return g;
    }

    /**
     * 查询所有年级信息
     */
    @Override
    public List<Grade> find() {
        List<Grade> list = new ArrayList<Grade>();
        String sql = "select * from grade";
        Connection conn = BaseDao.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGid(rs.getInt(1));
                grade.setGname(rs.getString(2));
                list.add(grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return list;
    }

    /**
     * 使用存储过程查询所有年级信息
     */
    @Override
    public List<Grade> procFind() {
        List<Grade> list = new ArrayList<>();
        String sql = "{call p_grade()}";
        Connection conn = BaseDao.getConnection();
        CallableStatement st = null;// 用于执行存储过程的命令对象
        ResultSet rs = null;
        try {
            st = conn.prepareCall(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGid(rs.getInt(1));
                grade.setGname(rs.getString(2));
                list.add(grade);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }

        return list;
    }

}

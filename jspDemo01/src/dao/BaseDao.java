package dao;

import java.sql.*;

/**
 * ����ฺ�����������ݿ�Ľ���
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
    /**
     * ��̬������������
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * �������ݿ����ӣ�����������
     * 
     * @return ���ݿ�����
     */
    public static Connection getConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/1219?characterEncoding=utf-8";
        String name = "root";
        String pwd = "123123";
        try {
            conn = DriverManager.getConnection(url, name, pwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * �ر�������Դ
     * 
     * @param conn
     *            �����Ӷ���
     * @param st
     *            ���������
     * @param rs
     *            �������
     */
    public static void closeAll(Connection conn, Statement st, ResultSet rs) {
        try {
            if (conn != null)
                conn.close();
            if (st != null)
                st.close();
            if (rs != null)
                rs.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    /**
     * ����ִ����ɾ�Ĳ����ķ���
     * 
     * @param sql
     *            ��SQLָ��
     * @param obj
     *            ��SQLָ����ռλ������Ҫ�����ݣ�Object...obj ��ʾ��һ��Object���͵����飬...�в���������
     * @return �������ݿ�����Ӱ�������
     */
    public static int executeUpdate(String sql, Object... obj) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // ��ռλ����ֵ
            if (obj.length > 0) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            return ps.executeUpdate();// ִ��SQL������Ӱ�������
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeAll(conn, ps, null);
        }
        return 0;
    }
}

package dao;

import java.sql.*;

/**
 * 这个类负责所有与数据库的交互
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
    /**
     * 静态方法加载驱动
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
     * 创建数据库连接，并返回连接
     * 
     * @return 数据库连接
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
     * 关闭所有资源
     * 
     * @param conn
     *            ：连接对象
     * @param st
     *            ：命令对象
     * @param rs
     *            ：结果集
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
     * 用于执行增删改操作的方法
     * 
     * @param sql
     *            ：SQL指令
     * @param obj
     *            ：SQL指令中占位符所需要的数据；Object...obj 表示是一个Object类型的数组，...叫不定长参数
     * @return 返回数据库中受影响的行数
     */
    public static int executeUpdate(String sql, Object... obj) {
        Connection conn = getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            // 给占位符赋值
            if (obj.length > 0) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
            return ps.executeUpdate();// 执行SQL并返回影响的行数
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            closeAll(conn, ps, null);
        }
        return 0;
    }
}

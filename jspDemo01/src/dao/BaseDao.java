package dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 这个类负责所有与数据库的交互
 * 
 * @author Administrator
 * 
 */
public class BaseDao {
    /**
     * 创建数据库连接，并返回连接，使用连接池
     * 
     * @return 数据库连接
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // 初始化上下文
            Context ct = new InitialContext();
            // 通过上下文对象获取连接池的数据对象,注意：java:comp/env/是固定的写法
            DataSource ds = (DataSource) ct.lookup("java:comp/env/conn1219");
            // 从连接池中取出一个连接对象
            conn = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

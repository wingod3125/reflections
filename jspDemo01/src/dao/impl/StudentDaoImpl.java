package dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import util.PageUtil;
import dao.BaseDao;
import dao.StudentDao;
import entity.Grade;
import entity.Student;

public class StudentDaoImpl implements StudentDao {

    @Override
    /**
     * 查询所有学生对象，返回对象集合
     */
    public List<Student> find() {
        List<Student> list = new ArrayList<Student>();
        Connection conn = BaseDao.getConnection();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from student";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Student stu = new Student();
                stu.setStudentNo(rs.getInt(1));
                stu.setStudentName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setTelephone(rs.getString(4));
                stu.setBirthday(rs.getDate(5));
                stu.setAddress(rs.getString(6));
                stu.setEmail(rs.getString(7));
                Grade g = new GradeDaoImpl().findById(rs.getInt(8));
                stu.setGrade(g);
                list.add(stu);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }
        return list;
    }

    /**
     * 添加学生信息
     */
    @Override
    public int add(Student student) {
        String sql = "insert into student values(null,?,?,?,?,?,?,?)";
        return BaseDao.executeUpdate(sql, student.getStudentName(),
                student.getSex(), student.getTelephone(),
                student.getBirthday(), student.getAddress(),
                student.getEmail(), student.getGrade().getGid());
    }

    /**
     * 执行存储过程查询所有学员信息
     */
    @Override
    public List<Student> procFind() {
        List<Student> list = new ArrayList<Student>();
        String sql = "{call p_student}";
        Connection conn = BaseDao.getConnection();
        // 用于执行存储过程的对象
        CallableStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareCall(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setStudentNo(rs.getInt(1));
                stu.setStudentName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setTelephone(rs.getString(4));
                stu.setBirthday(rs.getDate(5));
                stu.setAddress(rs.getString(6));
                stu.setEmail(rs.getString(7));
                Grade g = new GradeDaoImpl().findById(rs.getInt(8));
                stu.setGrade(g);
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }
        return list;
    }

    /**
     * 通过性别查询学生信息; 执行带输入参数的存储过程
     */
    @Override
    public List<Student> procFindBySex(String sex) {
        List<Student> list = new ArrayList<>();
        String sql = "{call p_studentbySex(?)}";// 带输入参数的存储过程
        Connection conn = BaseDao.getConnection();
        // 用于执行存储过程的对象
        CallableStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareCall(sql);
            // 给输入参数进行赋值，赋值方式与占位符的方式一样
            st.setString(1, sex);
            rs = st.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setStudentNo(rs.getInt(1));
                stu.setStudentName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setTelephone(rs.getString(4));
                stu.setBirthday(rs.getDate(5));
                stu.setAddress(rs.getString(6));
                stu.setEmail(rs.getString(7));
                Grade g = new GradeDaoImpl().findById(rs.getInt(8));
                stu.setGrade(g);
                list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }
        return list;
    }

    /**
     * 查询学生数量; 执行带输出参数的存储过程
     */
    @Override
    public int getStudentCount() {
        int count = 1;
        Connection conn = BaseDao.getConnection();
        String sql = "{call p_studentCount(?)}";
        CallableStatement st = null;
        try {
            st = conn.prepareCall(sql);
            // Types表示数据里面的数据类型，是一个枚举类型
            st.registerOutParameter(1, Types.INTEGER);
            st.execute();// 执行存储过程
            // 获取存储过程执行后的输出参数值
            // getInt(1)表示取出的是一个整数，1表示第一个参数的值
            count = st.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, null);
        }

        return count;
    }

    /**
     * 分页的方法
     * 
     * @param pageIndex
     *            当前页
     * @param pageSize
     *            每页大小
     */
    @Override
    public PageUtil<Student> getStudentPage(int pageIndex, int pageSize) {
        // SQL用于获取总记录数
        String sql = "select count(*) from student";
        // SQL用于取本页的数据
        String sql1 = "select * from student limit ?,?";
        PageUtil<Student> pu = new PageUtil<Student>(); // 分页的工具类
        List<Student> list = new ArrayList<>();// 本页数据的集合
        Connection conn = BaseDao.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            if (rs.next()) {
                // int count = ;
                pu.setPageNumber(rs.getInt(1));
            }
            st = conn.prepareStatement(sql1);
            st.setInt(1, (pageIndex - 1) * pageSize);
            st.setInt(2, pageSize);
            rs = st.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
                stu.setStudentNo(rs.getInt(1));
                stu.setStudentName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setTelephone(rs.getString(4));
                stu.setBirthday(rs.getDate(5));
                stu.setAddress(rs.getString(6));
                stu.setEmail(rs.getString(7));
                Grade g = new GradeDaoImpl().findById(rs.getInt(8));
                stu.setGrade(g);
                list.add(stu);
            }
            pu.setList(list);// 数据集合封装
            pu.setPageIndex(pageIndex);
            pu.setPageSize(pageSize);
            // 计算总页数
            int pageCount = pu.getPageNumber() % pu.getPageSize() == 0 ? pu
                    .getPageNumber() / pageSize : pu.getPageNumber() / pageSize
                    + 1;
            pu.setPageCount(pageCount);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }
        return pu;
    }

    /**
     *通过ID删除指定的学生信息 
     */
    @Override
    public int delete(int id) {
        String sql = "delete from student where studentNo = ?";
        return BaseDao.executeUpdate(sql, id);
    }

    /**
     * 通过ID查询指定的学生信息
     */
    @Override
    public Student findById(int id) {
        Connection conn = BaseDao.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from student where studentNo = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Student stu = new Student();
                stu.setStudentNo(rs.getInt(1));
                stu.setStudentName(rs.getString(2));
                stu.setSex(rs.getString(3));
                stu.setTelephone(rs.getString(4));
                stu.setBirthday(rs.getDate(5));
                stu.setAddress(rs.getString(6));
                stu.setEmail(rs.getString(7));
                Grade g = new GradeDaoImpl().findById(rs.getInt(8));
                stu.setGrade(g);
                return stu;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, rs);
        }
        return null;
    }

    /**
     * 更新指定的学生信息
     */
    @Override
    public int update(Student student) {
        String sql = "update student set name=?,sex=?,phone=?,birthday=?,address=?,email=?,gid=? where studentNo=?";
        return BaseDao.executeUpdate(sql, student.getStudentName(),
                student.getSex(), student.getTelephone(),
                student.getBirthday(), student.getAddress(),
                student.getEmail(), student.getGrade().getGid(),
                student.getStudentNo());
    }
}

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
     * ��ѯ����ѧ�����󣬷��ض��󼯺�
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
     * ���ѧ����Ϣ
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
     * ִ�д洢���̲�ѯ����ѧԱ��Ϣ
     */
    @Override
    public List<Student> procFind() {
        List<Student> list = new ArrayList<Student>();
        String sql = "{call p_student}";
        Connection conn = BaseDao.getConnection();
        // ����ִ�д洢���̵Ķ���
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
     * ͨ���Ա��ѯѧ����Ϣ; ִ�д���������Ĵ洢����
     */
    @Override
    public List<Student> procFindBySex(String sex) {
        List<Student> list = new ArrayList<>();
        String sql = "{call p_studentbySex(?)}";// ����������Ĵ洢����
        Connection conn = BaseDao.getConnection();
        // ����ִ�д洢���̵Ķ���
        CallableStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareCall(sql);
            // ������������и�ֵ����ֵ��ʽ��ռλ���ķ�ʽһ��
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
     * ��ѯѧ������; ִ�д���������Ĵ洢����
     */
    @Override
    public int getStudentCount() {
        int count = 1;
        Connection conn = BaseDao.getConnection();
        String sql = "{call p_studentCount(?)}";
        CallableStatement st = null;
        try {
            st = conn.prepareCall(sql);
            // Types��ʾ����������������ͣ���һ��ö������
            st.registerOutParameter(1, Types.INTEGER);
            st.execute();// ִ�д洢����
            // ��ȡ�洢����ִ�к���������ֵ
            // getInt(1)��ʾȡ������һ��������1��ʾ��һ��������ֵ
            count = st.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, st, null);
        }

        return count;
    }

    /**
     * ��ҳ�ķ���
     * 
     * @param pageIndex
     *            ��ǰҳ
     * @param pageSize
     *            ÿҳ��С
     */
    @Override
    public PageUtil<Student> getStudentPage(int pageIndex, int pageSize) {
        // SQL���ڻ�ȡ�ܼ�¼��
        String sql = "select count(*) from student";
        // SQL����ȡ��ҳ������
        String sql1 = "select * from student limit ?,?";
        PageUtil<Student> pu = new PageUtil<Student>(); // ��ҳ�Ĺ�����
        List<Student> list = new ArrayList<>();// ��ҳ���ݵļ���
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
            pu.setList(list);// ���ݼ��Ϸ�װ
            pu.setPageIndex(pageIndex);
            pu.setPageSize(pageSize);
            // ������ҳ��
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
     *ͨ��IDɾ��ָ����ѧ����Ϣ 
     */
    @Override
    public int delete(int id) {
        String sql = "delete from student where studentNo = ?";
        return BaseDao.executeUpdate(sql, id);
    }

    /**
     * ͨ��ID��ѯָ����ѧ����Ϣ
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
     * ����ָ����ѧ����Ϣ
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

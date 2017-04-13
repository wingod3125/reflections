package biz.impl;

import java.util.List;

import util.PageUtil;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;

import entity.Student;
import biz.StudentBiz;

/**
 * 业务接口实现类，负责实现具体业务方法
 * 
 * @author Administrator
 * 
 */
public class StudentBizImpl implements StudentBiz {
    StudentDao dao = new StudentDaoImpl();

    @Override
    public int add(Student student) {
        // 添加学生信息
        return dao.add(student);
    }

    @Override
    public int delete(int id) {
        // 删除学生信息
        return dao.delete(id);
    }

    @Override
    public int update(Student student) {
        // 修改学生信息
        return dao.update(student);
    }

    @Override
    public List<Student> find() {
        // 调用数据访问类的方法，并向上一层返回结果集。查询所有的学生信息
        return dao.find();
    }

    @Override
    public Student findById(int id) {
        // 通过ID查询学生信息
        return dao.findById(id);
    }

    @Override
    public List<Student> procFind() {
        // 使用存储过程查询所有学生信息
        return dao.procFind();
    }

    @Override
    public List<Student> procFindBySex(String sex) {
        // 使用存储过程查询指定性别的学生信息
        return dao.procFindBySex(sex);
    }

    @Override
    public int getStudentCount() {
        // 查询学生总人数
        return dao.getStudentCount();
    }

    @Override
    public PageUtil<Student> getStudentPage(int pageIndex, int pageSize) {
        // 分页查询学生信息
        return dao.getStudentPage(pageIndex, pageSize);
    }

}

package dao;

import java.util.List;

import util.PageUtil;

import entity.Student;

public interface StudentDao {
    List<Student> find();
    
    Student findById(int id);

    List<Student> procFind();

    List<Student> procFindBySex(String sex);

    int add(Student student);
    
    int delete(int id);
    
    int update(Student student);

    int getStudentCount();

    PageUtil<Student> getStudentPage(int pageIndex, int pageSize);
}

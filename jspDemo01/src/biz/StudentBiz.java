package biz;

import java.util.List;

import util.PageUtil;

import entity.Student;

/**
 * ҵ����ӿڣ�ֻ���𷽷���Լ��
 * 
 * @author Administrator
 * 
 */
public interface StudentBiz {
    int add(Student student);

    int delete(int id);

    int update(Student student);

    List<Student> find();

    Student findById(int id);

    List<Student> procFind();

    List<Student> procFindBySex(String sex);

    int getStudentCount();

    PageUtil<Student> getStudentPage(int pageIndex, int pageSize);
}

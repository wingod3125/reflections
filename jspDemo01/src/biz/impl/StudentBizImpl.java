package biz.impl;

import java.util.List;

import util.PageUtil;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;

import entity.Student;
import biz.StudentBiz;

/**
 * ҵ��ӿ�ʵ���࣬����ʵ�־���ҵ�񷽷�
 * 
 * @author Administrator
 * 
 */
public class StudentBizImpl implements StudentBiz {
    StudentDao dao = new StudentDaoImpl();

    @Override
    public int add(Student student) {
        // ���ѧ����Ϣ
        return dao.add(student);
    }

    @Override
    public int delete(int id) {
        // ɾ��ѧ����Ϣ
        return dao.delete(id);
    }

    @Override
    public int update(Student student) {
        // �޸�ѧ����Ϣ
        return dao.update(student);
    }

    @Override
    public List<Student> find() {
        // �������ݷ�����ķ�����������һ�㷵�ؽ��������ѯ���е�ѧ����Ϣ
        return dao.find();
    }

    @Override
    public Student findById(int id) {
        // ͨ��ID��ѯѧ����Ϣ
        return dao.findById(id);
    }

    @Override
    public List<Student> procFind() {
        // ʹ�ô洢���̲�ѯ����ѧ����Ϣ
        return dao.procFind();
    }

    @Override
    public List<Student> procFindBySex(String sex) {
        // ʹ�ô洢���̲�ѯָ���Ա��ѧ����Ϣ
        return dao.procFindBySex(sex);
    }

    @Override
    public int getStudentCount() {
        // ��ѯѧ��������
        return dao.getStudentCount();
    }

    @Override
    public PageUtil<Student> getStudentPage(int pageIndex, int pageSize) {
        // ��ҳ��ѯѧ����Ϣ
        return dao.getStudentPage(pageIndex, pageSize);
    }

}

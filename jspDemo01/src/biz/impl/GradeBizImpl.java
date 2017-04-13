package biz.impl;

import java.util.List;

import biz.GradeBiz;
import dao.GradeDao;
import dao.impl.GradeDaoImpl;
import entity.Grade;

public class GradeBizImpl implements GradeBiz {
	GradeDao dao = new GradeDaoImpl();

	@Override
	public List<Grade> find() {
	    //��ѯ���е��꼶��Ϣ
		return dao.find();
	}

    @Override
    public List<Grade> procFind() {
        //ʹ�ô洢���̲�ѯ���е��꼶��Ϣ
        return dao.procFind();
    }

}

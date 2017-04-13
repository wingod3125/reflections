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
	    //查询所有的年级信息
		return dao.find();
	}

    @Override
    public List<Grade> procFind() {
        //使用存储过程查询所有的年级信息
        return dao.procFind();
    }

}

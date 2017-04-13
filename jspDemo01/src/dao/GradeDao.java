package dao;

import java.util.List;

import entity.Grade;

public interface GradeDao {
	Grade findById(int id);

	List<Grade> find();
	
	List<Grade> procFind();
}

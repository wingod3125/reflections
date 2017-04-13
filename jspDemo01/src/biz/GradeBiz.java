package biz;

import java.util.List;

import entity.Grade;

public interface GradeBiz {
    List<Grade> find();

    List<Grade> procFind();
}

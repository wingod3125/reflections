package biz;

import entity.Admin;

public interface AdminBiz {
	int login(String userName, String pwd);

	boolean isUsedUserName(String userName);

	int save(Admin admin);
}

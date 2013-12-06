package com.inzpiral.loginjoshuamvc.daos;

import java.util.ArrayList;

import com.inzpiral.loginjoshuamvc.models.UserVo;

public class UserDao {
	
	private UserVo dummyUser1 = new UserVo(1, "claudio@alvarez.com", "123");
	private UserVo dummyUser2 = new UserVo(1, "jp@lazcano.cl", "321");
	
	public ArrayList<UserVo> getAll() {
		ArrayList<UserVo> users = new ArrayList<UserVo>();
		users.add(dummyUser1);
		users.add(dummyUser2);
		
		return users;
	}
	
	public UserVo get(int id) {
		// bla bla
		return new UserVo();
	}
	
	public long insert(UserVo counterVo) {
		// bla bla
		return 0;
	}
	
	public int update(UserVo counterVo) {
		// bla bla
		return 0;
	}
	
	public void delete(UserVo counterVo) {
		delete(counterVo.getId());
	}
	public void delete(int id) {
		// bla bla
	}
	
	public void deleteAll() {
		// bla bla
	}

}

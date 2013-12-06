package com.inzpiral.loginjoshuamvc.models;


public class UserVo extends SimpleObservable<UserVo> {
	
	private int mId;
	private String mUsername;
	private String mPassword;
	
	public UserVo() {
		mId = -1;
		mUsername = "";
		mPassword = "";
	}
	
	public UserVo(int id, String username, String password) {
		mId = id;
		mUsername = username;
		mPassword = password;
	}
	
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
		notifyObservers(this);
	}
	
	public String getUsername() {
		return mUsername;
	}
	public void setUsername(String username) {
		this.mUsername = username;
		notifyObservers(this);
	}
	
	public String getPassword() {
		return mPassword;
	}
	public void setPassword(String password) {
		this.mPassword = password;
		notifyObservers(this);
	}
	
	
	@Override
	synchronized public UserVo clone() {
		UserVo vo = new UserVo();
		vo.setId(mId);
		vo.setUsername(mUsername);
		vo.setPassword(mPassword);
		return vo;
	}
	
	synchronized public void consume(UserVo vo) {
		mId = vo.getId();
		mUsername = vo.getUsername();
		mPassword = vo.getPassword();
		notifyObservers(this);
	}

	public boolean isEquals(UserVo anotherUser) {
		return anotherUser.getUsername().equals(mUsername) && 
				anotherUser.getPassword().equals(mPassword);
	}
	
}

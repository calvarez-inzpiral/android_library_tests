package com.inzpiral.loginjoshuamvc.models;

import com.inzpiral.loginjoshuamvc.events.EventDispatcher;
import com.inzpiral.loginjoshuamvc.events.SimpleEvent;


public class UserVo extends EventDispatcher {
	
	private int mId;
	private String mUsername;
	private String mPassword;
	
	public static class ChangeEvent extends SimpleEvent {
		public static final String EVENT_USERNAME_CHANGED = "EVENT_USERNAME_CHANGED";
		public static final String EVENT_PASSWORD_CHANGED = "EVENT_PASSWORD_CHANGED";
		
		public ChangeEvent(String type) {
			super(type);
		}
	}
	
	private void notifyChange(String type) {
		dispatchEvent(new ChangeEvent(type));
	}
	
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
	}
	
	public String getUsername() {
		return mUsername;
	}
	public void setUsername(String username) {
		this.mUsername = username;
		notifyChange(ChangeEvent.EVENT_USERNAME_CHANGED);
	}
	
	public String getPassword() {
		return mPassword;
	}
	public void setPassword(String password) {
		this.mPassword = password;
		notifyChange(ChangeEvent.EVENT_PASSWORD_CHANGED);
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
	}

	public boolean isEquals(UserVo anotherUser) {
		return anotherUser.getUsername().equals(mUsername) && 
				anotherUser.getPassword().equals(mPassword);
	}
	
}

package com.inzpiral.loginjoshuamvc.controllers;

import java.util.ArrayList;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.inzpiral.loginjoshuamvc.daos.UserDao;
import com.inzpiral.loginjoshuamvc.models.UserVo;


public class LoginController extends Controller {
	
	// Basics
	private static final String TAG = LoginController.class.getSimpleName();
	private ArrayList<UserVo> mModel;
	private HandlerThread workerThread;
	private Handler workerHandler;
	
	// Messages
	public static final int MESSAGE_USERNAME_CHANGED = 1;
	public static final int MESSAGE_ATTEMPT_LOGIN = 2;
	public static final int MESSAGE_CREDENTIALS_MATCH = 3;
	public static final int MESSAGE_CREDENTIALS_DONT_MATCH = 4;
	public static final int MESSAGE_BAD_EMAIL = 5;
	
	public ArrayList<UserVo> getModel() {
		return mModel;
	}
	
	public LoginController(ArrayList<UserVo> model) {
		this.mModel = model;
		workerThread = new HandlerThread("Worker Thread");
		workerThread.start();
		workerHandler = new Handler(workerThread.getLooper());
		loadUsers();
	}
	
	@Override
	public boolean handleMessage(int what, Object data) {
		Log.i(TAG, "handling message code of " + what);
		switch(what) {
			case MESSAGE_USERNAME_CHANGED:
				displayError((String)data);
				return true;
			case MESSAGE_ATTEMPT_LOGIN:
				attemptLogin((UserVo)data);
				return true;
		}
		return false;
	}

	private void displayError(String username) {
		if(!username.contains("@")) {
			notifyOutboxHandlers(MESSAGE_BAD_EMAIL, 0, 0, null);
		}
	}

	private void attemptLogin(final UserVo attempt) {
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (mModel) {
					for (UserVo user : mModel) {
						if( user.isEquals(attempt) ) {							
							notifyOutboxHandlers(MESSAGE_CREDENTIALS_MATCH, 0, 0, null);
							return;
						}
					}
				}
				notifyOutboxHandlers(MESSAGE_CREDENTIALS_DONT_MATCH, 0, 0, null);
			}
		});
	}
	
	private void loadUsers() {
		workerHandler.post(new Runnable() {
			@Override
			public void run() {
				synchronized (mModel) {
					UserDao userDao = new UserDao();
					ArrayList<UserVo> storedUsers = userDao.getAll();
					for (UserVo user : storedUsers) {
						mModel.add(user);
					}
				}
			}
		});
	}
	
}

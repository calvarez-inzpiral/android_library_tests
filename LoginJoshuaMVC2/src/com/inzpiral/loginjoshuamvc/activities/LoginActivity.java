package com.inzpiral.loginjoshuamvc.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;

import com.inzpiral.loginjoshuamvc.R;
import com.inzpiral.loginjoshuamvc.daos.UserDao;
import com.inzpiral.loginjoshuamvc.models.UserVo;
import com.inzpiral.loginjoshuamvc.views.LoginView;

public class LoginActivity extends Activity {
	
	// Basics
	private static final String TAG = LoginActivity.class.getSimpleName();
	private LoginView mView;
	private ArrayList<UserVo> mModel;
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mModel = new ArrayList<UserVo>();
		mView = (LoginView)View.inflate(this, R.layout.activity_login, null);
		mView.setViewListener(viewListener);
		setContentView(mView);
		mHandler = new Handler();
		loadUsers();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	private void loadUsers() {
		UserDao userDao = new UserDao();
		ArrayList<UserVo> storedUsers = userDao.getAll();
		for (UserVo user : storedUsers) {
			mModel.add(user);
		}
	}
	
	/**
	 * This is how we receive events from the view.
	 * The view takes user actions
	 * The controller/activity responds to user actions
	 */
	private LoginView.ViewListener viewListener = new LoginView.ViewListener() {

		@Override
		public void onAttemptLogin(UserVo attempt) {
			for (UserVo user : mModel) {
				if( user.isEquals(attempt) ) {
					mView.onCredentialsMatch(LoginActivity.this);
					return;
				}
			}
			
			mView.onCredentialsDontMatch(LoginActivity.this);
		}

		@Override
		public void onUsernameChanged() {
//			mUsernameView.setError("Debe ser un email!");
		}
		
	};

}

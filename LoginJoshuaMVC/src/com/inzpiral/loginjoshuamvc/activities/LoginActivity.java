package com.inzpiral.loginjoshuamvc.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inzpiral.loginjoshuamvc.R;
import com.inzpiral.loginjoshuamvc.controllers.LoginController;
import com.inzpiral.loginjoshuamvc.models.UserVo;

public class LoginActivity extends Activity implements Handler.Callback {
	
	// Basics
	private static final String TAG = LoginActivity.class.getSimpleName();
	private LoginController mController;
	private ArrayList<UserVo> mUsers;

	// UI references.
	private EditText mUsernameView;
	private EditText mPasswordView;
	private Button mSubmitButton;
	
	// Messages
	public static final String EXTRA_TAP_ID = "tapId";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
        mUsers = new ArrayList<UserVo>();
        mController = new LoginController(mUsers);
        mController.addOutboxHandler(new Handler(this));
        
        mUsernameView = (EditText)findViewById(R.id.email);
        mPasswordView = (EditText)findViewById(R.id.password);
        mSubmitButton = (Button)findViewById(R.id.sign_in_button);
        
        mUsernameView.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mController.handleMessage(LoginController.MESSAGE_USERNAME_CHANGED, s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
        
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		UserVo user = new UserVo(-1, mUsernameView.getText().toString(), mPasswordView.getText().toString());
        		mController.handleMessage(LoginController.MESSAGE_ATTEMPT_LOGIN, user);
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch(msg.what) {
			case LoginController.MESSAGE_BAD_EMAIL:
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mUsernameView.setError("Debe ser un email!");
					}
				});
				return true;
			case LoginController.MESSAGE_CREDENTIALS_MATCH:
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(LoginActivity.this, "SUCCESS!", Toast.LENGTH_LONG).show();
	//					startActivity(new Intent(LoginActivity.this, LoginActivity.class));
					}
				});
				return true;
			case LoginController.MESSAGE_CREDENTIALS_DONT_MATCH:
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(LoginActivity.this, "ERROR!", Toast.LENGTH_LONG).show();
					}
				});
				return true;
		}
		return false;
	}

}

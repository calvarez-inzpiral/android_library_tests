package com.inzpiral.loginjoshuamvc.views;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.inzpiral.loginjoshuamvc.R;
import com.inzpiral.loginjoshuamvc.models.UserVo;

public class LoginView extends LinearLayout {
	
	/**
	 * The interface to send events from the view to the controller
	 */
	public static interface ViewListener {
		public void onAttemptLogin(UserVo user);
		public void onUsernameChanged();
	}
	
	private static final String TAG = LoginView.class.getSimpleName();
	
	private ViewListener viewListener;
	private EditText mUsername;
	private EditText mPassword;
	private Button mSubmit;
	private UserVo model;
	
	/**
	 * The listener reference for sending events
	 */
	public void setViewListener(ViewListener viewListener) {
		this.viewListener = viewListener;
	}
	
	/**
	 * Constructor for xml layouts
	 */
	public LoginView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * Remove the listener from the model
	 */
	public void destroy() {
		
	}
	
	/**
	 * Find our references to the objects in the xml layout
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mUsername = (EditText)findViewById(R.id.email);
		mPassword = (EditText)findViewById(R.id.password);
		mSubmit = (Button)findViewById(R.id.sign_in_button);

        mUsername.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				viewListener.onUsernameChanged();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
			
			@Override
			public void afterTextChanged(Editable s) { }
		});
        
		mSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
        		UserVo user = new UserVo(-1, mUsername.getText().toString(), mPassword.getText().toString());
				viewListener.onAttemptLogin(user);
			}
		});
		
//		model.addListener(UserVo.ChangeEvent.ELAPSED_TIME_CHANGED, elapsedTimeListener);
		bind();
	}
	
	/**
	 * Does the work to update the view when the model changes. 
	 */
	private void bind() {
		// view's changes
	}
	

	public void onBadEmail() {
		
	}
	
	public void onCredentialsMatch(Context c) {
		Toast.makeText(c, "SUCCESS!", Toast.LENGTH_LONG).show();
	}
	
	public void onCredentialsDontMatch(Context c) {
		Toast.makeText(c, "ERROR!", Toast.LENGTH_LONG).show();
	}
	
}
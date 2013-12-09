package pn.eu.karinas.androidmvcsimpleexample.controller;

import pn.eu.karinas.androidmvcsimpleexample.constant.ErrorConstants;
import pn.eu.karinas.androidmvcsimpleexample.view.LoginView;
import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * LoginController intercepts the on click login button event, verify the inputs 
 * @author yo
 *
 */
@SuppressLint("NewApi")
public class LoginController implements OnClickListener{

	private final String TAG = "Login controller";
	
	private LoginView loginView;
	private LoginControllerListener listener;
	

	public LoginController(LoginView loginView, LoginControllerListener listener) {
		this.loginView = loginView;
		this.listener = listener;
	}

	/**
	 * Login button was clicked
	 */
	@Override
	public void onClick(View v) {
		Log.i(TAG, "onClick");
		
		// Check for a valid email address.
		if(loginView.getLogin().isEmpty() || loginView.getLogin().equals(""))
			loginView.setLoginError(ErrorConstants.ERROR_FIELD_REQUIRED);
		else
			if(!loginView.getLogin().contains("@"))
				loginView.setLoginError(ErrorConstants.ERROR_INVALID_EMAIL);

		// Check for a valid password.
		if(loginView.getPassword().isEmpty() || loginView.getPassword().equals(""))
			loginView.setPasswordError(ErrorConstants.ERROR_FIELD_REQUIRED);
		else
			if(loginView.getPassword().length() < 3)
				loginView.setPasswordError(ErrorConstants.ERROR_INVALID_PASSWORD);
		else

			listener.onLoginSuccess();
	}
	
	
	public interface LoginControllerListener {
		/**
		 * The method is called by Login controller to inform the 
		 * Login Activity about the successful login
		 */
		public void onLoginSuccess();
	}


}

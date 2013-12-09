package pn.eu.karinas.androidmvcsimpleexample.model;



public class ModelUsers {
    
    private String mUsername;
    private String mPassword;
     

	public ModelUsers(String username,String password)
    {
        this.mUsername  = username;
        this.mPassword  = password;
      
    }


	public String getUsername() {
		return mUsername;
	}


	public void setUsername(String username) {
		mUsername = username;
	}


	public String getPassword() {
		return mPassword;
	}


	public void setPassword(String password) {
		mPassword = password;
	}
	
	public boolean isEquals(ModelUsers anotherUser) {
		return anotherUser.getUsername().equals(mUsername) && 
		anotherUser.getPassword().equals(mPassword);
	}

         
}
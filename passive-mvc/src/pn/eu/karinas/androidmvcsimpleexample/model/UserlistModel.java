package pn.eu.karinas.androidmvcsimpleexample.model;

import java.util.ArrayList;

public class UserlistModel {

	private  ArrayList<ModelUsers> users = new ArrayList<ModelUsers>();
	   
		public ModelUsers getUser(int pPosition) {
	         
	        return users.get(pPosition);
	    }
	     
	    public void setUser(ModelUsers User) {
	        
	        users.add(User);
	         
	    }
	     
	    public ModelUsers getPassword(int pPosition) {
	         
	        return users.get(pPosition);
	    }
	     
	    public void setPassword(ModelUsers Password) {
	        
	        users.add(Password);
	        
	         
	    }
	  
	    public boolean checkUserExist(ModelUsers User) {
	          for (ModelUsers user : users) {
				if (user.isEquals(User)){
					return true;
				}
			}  
	    	return false;
	
	   }
		 
}

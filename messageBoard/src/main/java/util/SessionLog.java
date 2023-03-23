package util;
import datamodel.Users;

public class SessionLog {
	public Users currentUser;
	
	public SessionLog() {
		super();
	}

	public Users getCurrentUser(){
		return currentUser;
	}
	
	public void setUser(Users user) {
		if(currentUser != null) {
			System.out.println("Changing user from " + currentUser.getName() + " to " + user.getName());
		}
		currentUser = user;
	}
}

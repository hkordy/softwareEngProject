package ClientServerProject;

public class messages {

	//Case: user connects to server
	String failedConnection = "Connection Faild, please try again";
	String serverDown = "the server is currently down";
	
	//Case: user disconnects from server
	String disconnected = "Dissconnceted successfully";
	
	
	//Case: user registers new account (client side)
	String invalidUser = "Invalid username";
	String invalidPwd = "Password entered is invalid";
	String invalidEmai = "Email address is invalid";
	String registered = "The account is registered successfully";
	
	//Case: user register new account (server side)
	String usernameTaken = "Username not available";
	
	
	//Case: User logs in 
	String notFound = "Username does not exist in database";
	String locked = "Account is locked ";
	String wrongPwd = "Wrong password";
	String loggedIn = "You are succefully logged in";
	
	//Case: User logs out
	String loggedOut = "You have logged out successfully";

	
	//Case: user accesses system database 
	String accessDB = "You can now access system database";
	
	
	//Case: user changes pwd 
	String enterNewPwd = "Enter new password";
	String pwdChanged = "Password has been changed successfully";
	// --- for the case when password is not correct, use the invalidPwd string 
	
	
	//Case: user password recovery 
	// notFound;
	// locked;
	String emailSent = " Check your inbox for the temporary password. We recommend that you change the password when you log in";


	public String getFailedConnection() {
		return failedConnection;
	}


	public String getServerDown() {
		return serverDown;
	}


	public String getDisconnected() {
		return disconnected;
	}


	public String getInvalidUser() {
		return invalidUser;
	}


	public String getInvalidPwd() {
		return invalidPwd;
	}


	public String getInvalidEmai() {
		return invalidEmai;
	}


	public String getRegistered() {
		return registered;
	}


	public String getUsernameTaken() {
		return usernameTaken;
	}


	public String getNotFound() {
		return notFound;
	}


	public String getLocked() {
		return locked;
	}


	public String getWrongPwd() {
		return wrongPwd;
	}


	public String getLoggedIn() {
		return loggedIn;
	}


	public String getLoggedOut() {
		return loggedOut;
	}


	public String getAccessDB() {
		return accessDB;
	}


	public String getEnterNewPwd() {
		return enterNewPwd;
	}


	public String getPwdChanged() {
		return pwdChanged;
	}


	public String getEmailSent() {
		return emailSent;
	}
	
}

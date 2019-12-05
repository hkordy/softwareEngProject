package BasicClientServer;

public class messages{

	String message;
	String test;
	public String getFailedConnection() {
		return message = "Connection Faild, please try again";
	}


	public String getServerDown() {
		return message = "the server is currently down";
	}


	public String getDisconnected() {
		return message = "Dissconnceted successfully";
	}


	public String getInvalidUser() {
		return message = "Invalid username";
	}


	public String getInvalidPwd() {
		return message = "Password entered is invalid";
	}


	public String getInvalidEmai() {
		return message = "Email address is invalid";
	}


	public String getRegistered() {
		return message = "The account is registered successfully";
	}


	public String getUsernameTaken() {
		return message = "Username not available";
	}


	public String getNotFound() {
		return message = "Username does not exist in database";
	}


	public String getLocked() {
		return message = "Account is locked ";
	}


	public String getWrongPwd() {
		return message = "Wrong password";
	}


	public String getLoggedIn() {
		return message = "You are succefully logged in";
	}


	public String getLoggedOut() {
		return message = "You have logged out successfully" ;
	}


	public String getAccessDB() {
		return message = "You can now access system database";
	}


	public String getEnterNewPwd() {
		return message = "Enter new password";
	}


	public String getPwdChanged() {
		return message = "Password has been changed successfully";
	}


	public String getEmailSent() {
		return message = " Check your inbox for the temporary password. We recommend that you change the password when you log in";
	}
	
}

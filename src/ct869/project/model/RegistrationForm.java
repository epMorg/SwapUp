package ct869.project.model;

import java.util.*;

public class RegistrationForm {
	//data members
	private String userName;
	private String userEmail;
	private String userPassword1;
	private String userPassword2;
	private String userGender;
	private String userStudentID;
	private String chooseGender;
	private HashMap<String, String> errorMessage;
	
	public RegistrationForm() {
		errorMessage = new HashMap<String, String>();
	}
	
	public void setUserName(String uName) {
		userName = uName;
	}
	public String getUserName() {
		return userName;
	}
	public void setFirstPassword(String password) {
		userPassword1 = password;
	}
	public String getFirstPassword() {
		return userPassword1;
	}
	public void setSecondPassword(String password) {
		userPassword2 = password;
	}
	public String getSecondPassword() {
		return userPassword2;
	}
	public void setUserGender(String gender) {
		userGender = gender;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setStudentID(String studentID) {
		userStudentID = studentID;
	}
	public String getStudentID() {
		return userStudentID;
	}
	public void setUserEmail(String email) {
		userEmail = email;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public boolean isStudentIDInt() {
		try {
			String studentID = getStudentID();
			Integer.parseInt(studentID);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean isRegFormValid() {
		boolean everythingOK = true;
		if (userName.equals("")) {
			errorMessage.put("userName", "Please enter your username");
			userName = "";
			everythingOK = false;
		}
		if (userEmail.equals("") || userEmail.indexOf("@") == -1) {
			errorMessage.put("userEmail", "Please enter your email address");
			userEmail = "";
			everythingOK = false;
		}
		if (userPassword1.equals("")) {
			errorMessage.put("userPassword1", "Please enter your password");
			userPassword1 = "";
			everythingOK = false;
		}
		if (!userPassword1.equals("") && ((userPassword2.equals("")) || !userPassword1.equals(userPassword2))) {
			errorMessage.put("userPassword2", "Please confirm your password");
			userPassword2 = "";
			everythingOK = false;
		}
		if (userGender.equals("")) {
			errorMessage.put("userGender", "Please select your gender");
			userGender = "";
			everythingOK = false;
		}
		if (userStudentID.equals(null) || userStudentID.length() != 8){
			errorMessage.put("userStudentID", "Please enter a valid student ID");
		
			userStudentID = null;
			everythingOK = false;
		}
		return everythingOK;
	}
	public String getErrorMsg(String str) {
		String errorMsg = (String)errorMessage.get(str.trim());
		return (errorMsg == null) ? "":errorMsg;
	}
	public String isRadioSelected(String str) {
		return (chooseGender.equals(str)) ?"checked" : "";
	}
	public void setErrors(String key, String msg) {
		errorMessage.put(key, msg);
	}
	public void setChooseGender(String chooseGen){
		chooseGender = chooseGen;
	}
	public String getChooseGender() {
		return chooseGender;
	}
	
}

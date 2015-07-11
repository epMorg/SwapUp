package ct869.project.model;

import java.io.File;
import java.util.Date;
import java.util.List;

public class User {
	
	
	private String username;
	private String email;
	private String password;
	private int studentID;
	private String gender;
	//private SocialRole socialRole;
	private String socialRole;
	private int approvalPoints;
	private int positiveAP;
	private int negativeAP;
	private int neutralAP;
	private String profileBlurb;
	private String transaction;
	private String comment;
	private File photo;
	private boolean valid;
	private List<User> users;
	private Date dateOfJoin;
	
	public String getUserName() {
		return username;
	}
	public void setUserName(String uname) {
		username = uname;
	}
	public String getUserEmail() {
		return email;
	}
	public void setUserEmail(String anEmail) {
		email = anEmail;
	}
	public String getUserPassword() {
		return password;
	}
	public void setUserPassword(String aPassword) {
		password = aPassword;
	}
	public int getUserStudentID() {
		return studentID;
	}
	public void setUserStudentID(int ID) {
		studentID = ID;
	}
	public String getUserGender() {
		return gender;
	}
	public void setUserGender(String gndr) {
		gender = gndr;
	}
	public String getProfileBlurb() {
		return profileBlurb;
	}
	public void setProfileBlurb(String newBlurb) {
		profileBlurb = newBlurb;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transactionID) {
		transaction = transactionID;
	}
	public String getUserComment() {
		return comment;
	}
	public void setUserComment(String userComment) {
		comment = userComment;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean newValid) {
		valid = newValid;
	}
	public void setDefaultSocialRole(String gndr) {
		if (gndr.equals("male")) {
			socialRole = "Tribesman";
		}else {
			socialRole = "Tribeswoman";
		}
	}
	public String getSocialRole() {
		return socialRole;
	}
	public void setSocialRole(String socRole) {
		socialRole = socRole;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photograph) {
		photo = photograph;
	}
	public int getApprovalPoints() {
		return approvalPoints;
	}
	public void setApprovalPoints(int points) {
		approvalPoints = points;
	}
	public void setUsers(List<User> usrs) {
		users = usrs;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setDate(Date date) {
		dateOfJoin = date;
	}
	public Date getDate() {
		return dateOfJoin;
	}
	/*public static void main(String[] args) {
		User user = new User();
		String gender = "male";
		user.setUserGender(gender);
		user.setDefaultSocialRole(gender);
		System.out.println("This user's default social role is: " + user.getSocialRole());
	}*/
	public int getNegativeAP() {
		return negativeAP;
	}
	public void setNegativeAP(int negativeAP) {
		this.negativeAP = negativeAP;
	}
	public int getPositiveAP() {
		return positiveAP;
	}
	public void setPositiveAP(int positiveAP) {
		this.positiveAP = positiveAP;
	}
	public int getNeutralAP() {
		return neutralAP;
	}
	public void setNeutralAP(int neutralAP) {
		this.neutralAP = neutralAP;
	}
}

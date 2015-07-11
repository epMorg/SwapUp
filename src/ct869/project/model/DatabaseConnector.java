package ct869.project.model;

//import java.text.*;
//import java.util.*;
import java.sql.*;
import java.util.Calendar;
//import ct869.project.model.*;
import com.mysql.jdbc.PreparedStatement;

public class DatabaseConnector {
	static Connection currentConn = null;
	static ResultSet rs = null;
	static boolean success;

	//static method that returns a user object
	public static User userLogin(User user) {
		//preparing objects for connection
		java.sql.PreparedStatement pstmt = null;
		
		String username = user.getUserName();
		String password = user.getUserPassword();
		
		//check on console
		System.out.println("This is your DatabaseConnector Java Bean reporting.");
		System.out.println("The username is " + username);
		System.out.println("Your password is " + password);
		
		try {
				String sql = "SELECT * FROM user " +
					"WHERE username = ? AND passwd = MD5(?)";
			System.out.println("The SQL query: " + sql);
			
			//connect to DB
			currentConn = ConnectionManager.getConnection();
			pstmt = currentConn.prepareStatement(sql);
			System.out.println("Connecting to the DB.");
			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
		
			boolean more = rs.next();
			
			//if user does not exist set the isValid variable to false
			if(!more) {
				System.out.println("Sorry, you are not a registered user! Please register.");
				user.setValid(false);
				}
			//if user exists set the isValid variable to true
			else if(more) {
				int userID = rs.getInt("user_ID");
				user.setUserStudentID(userID);
				System.out.println("Student ID: " + userID);
				String email = rs.getString("email");
				user.setUserEmail(email);
				System.out.println("Email: " + email);
				String gender = rs.getString("gender");
				user.setUserGender(gender);
				System.out.println("Gender: " + gender);
				String profileBlurb = rs.getString("profile_blurb");
				user.setProfileBlurb(profileBlurb);
				System.out.println("Profile Blurb: " + profileBlurb);
				int approvalPoints = rs.getInt("approval_points");
				user.setApprovalPoints(approvalPoints);
				System.out.println("Approval points: " + approvalPoints);
				int positiveAP = rs.getInt("positive_AP");
				user.setPositiveAP(positiveAP);
				int negativeAP = rs.getInt("negative_AP");
				user.setNegativeAP(negativeAP);
				int neutralAP = rs.getInt("neutral_AP");
				user.setNeutralAP(neutralAP);
				String socialRole = rs.getString("social_role");
				user.setSocialRole(socialRole);
				System.out.println("socialRole: " + socialRole);
				
				user.setValid(true);	
			}
		
		}catch (Exception e) {
			System.out.println("Login failed: An exception has occured" + e);
		}//exception handling routines
		finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (Exception e) {}
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch (Exception e) {
					pstmt = null;
				}
			
			if (currentConn != null) {
				try {
					currentConn.close();
				}catch (Exception e) {}
				currentConn = null;
			}
		}
		
	}
		return user;
}
	public static boolean usernameTaken(String username) {
		
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT username FROM user WHERE username =?";
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement) currentConn.prepareStatement(sql);
			System.out.println("Connecting to the DB.");
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			if (!rs.isBeforeFirst()) {
				 success = false;
			}else{
				success = true;
			} 
		}catch (Exception e) {
			System.out.println("Login failed: An exception has occured" + e);
		}//exception handling routines
		finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (Exception e) {}
				rs = null;
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch (Exception e) {
					pstmt = null;
				}
			}
			if (currentConn != null) {
				try {
					currentConn.close();
				}catch (Exception e) {}
				currentConn = null;
			}
		}
		return success;	 
		
	}
	
	public static boolean userRegistration(User user) {

		PreparedStatement pstmt = null;
		
		int studentID = user.getUserStudentID();
		String username = user.getUserName();
		String password = user.getUserPassword();
		String email = user.getUserEmail();
		String gender = user.getUserGender();
		user.setDefaultSocialRole(gender);
		String socialRole = user.getSocialRole();
		
		System.out.println("This is Database connector!");
		System.out.println(studentID);
		System.out.println(username);
		System.out.println(password);
		System.out.println(email);
		System.out.println(gender);
		System.out.println(socialRole);
		
		try {
			String sql = "INSERT INTO user (user_ID, username, passwd, email, date_of_join, gender, social_role) " +
					"VALUES (?, ?, MD5(?), ?, ?, ?, ?)";
			System.out.println(sql);
			
			//Create a Java timestamp object that represents the current time
			//(i.e., a "current timestamp")
			Calendar calendar = Calendar.getInstance();
			java.sql.Timestamp timestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			System.out.println("Connecting to the database");
			
			pstmt.setInt(1, studentID);
			pstmt.setString(2, username);
			pstmt.setString(3, password);
			pstmt.setString(4, email);
			pstmt.setTimestamp(5, timestampObject);
			pstmt.setString(6, gender);
			pstmt.setString(7, socialRole);
			
			pstmt.executeUpdate();
			System.out.println("Data has been inserted succesfully!");
			return true;
			
			}
			catch (Exception e) {
			
			System.out.println("Login failed: An exception has occured" + e);
			return false;
		}//exception handling routines
		finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch (Exception e) {}
				pstmt = null;
			}
			if (currentConn != null) {
				try {
					currentConn.close();
				}catch (Exception e) {}
				currentConn = null;
			}
		}
	}
			
}



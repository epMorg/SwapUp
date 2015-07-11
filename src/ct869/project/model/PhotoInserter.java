package ct869.project.model;

//import java.text.*;
//import java.util.*;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
//import ct869.project.model.*;
import com.mysql.jdbc.PreparedStatement;

public class PhotoInserter {
	private static Connection currentConn = null;
	static ResultSet rs = null;
	private static boolean success;
	private static final int BUFFER_SIZE = 4096;
	
	public static boolean insertPhoto(String username, InputStream inputStream) {
		
		PreparedStatement pstmt = null;
		String sql = "UPDATE user SET user_photo = ?" +
				" WHERE username = ?";
		
		try {
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			System.out.println("Connecting to the database");
			
			pstmt.setBlob(1, inputStream);
			pstmt.setString(2, username);
			
			int row = pstmt.executeUpdate();
			if (row > 0) {
				System.out.println("File saved into database");
				success = true;
				
			}
		} catch (SQLException e) {
			System.out.println("There was an exception thrown: " + e);
			success = false;
		} finally {
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
		return success;
	}
	public static InputStream getPhoto(String username) throws IllegalArgumentException, SQLException, ClassNotFoundException {
		
		System.out.println("This is the getPhoto method. The username is: " + username);
		InputStream inputStream = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT user_photo FROM user WHERE username = ?";
		
		try {
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			System.out.println("Connecting to the database");
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			System.out.println("Executing DB query");
			if (rs.next()) {
				Blob blob = rs.getBlob("user_photo");
				if (blob != null) {
				inputStream = blob.getBinaryStream();
				//binaryStream = rs.getBinaryStream("user_photo");
				} else {
					System.out.println("The user has not yet uploaded a photo");
				}
			}
			
		}catch (SQLException e) {
			System.out.println("There was an exception thrown: " + e);
		} finally {
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
		return inputStream;
	}
	public static boolean checkPhoto(String username) {
		System.out.println("This is the checkPhotoMethod");
		PreparedStatement pstmt = null;
		String sql = "SELECT user_photo FROM user WHERE username = ?";
		
		try {
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			System.out.println("Connecting to the database");
			
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			System.out.println("Executing DB query");
			if (rs.next()) {
				Blob blob = rs.getBlob("user_photo");
				if (blob != null) {
					success = true;
					System.out.println("The user has uploaded a photo.");
			} else {
				success = false;
				System.out.println("The user has not yet uploaded a photo");
			}
			}
		}catch (SQLException e) {
			System.out.println("There was an exception thrown: " + e);
		} finally {
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
	
		
		return success;
	}
}
		


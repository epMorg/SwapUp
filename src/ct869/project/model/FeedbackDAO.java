package ct869.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ct869.project.model.*;

public class FeedbackDAO {
	private static boolean success;
	private static Connection currentConn = null;
	private static ResultSet rs = null;
	private static String SQLQuery;
	private static String SQLUpdate;
	
	public static boolean updateFeedbackTable(int listingID, String feedbackType, String feedbackValue, 
			String comment, String username) {
		
		PreparedStatement pstmt = null;
		
		System.out.println("This is your DatabaseConnector Java Bean reporting.");
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp timestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		
		try {
			String sql = "INSERT INTO listingFeedback (feedback_type, feedback_value, feedback_comment, listing_ID, date, username) " +
					"VALUES (?, ?, ?, ?, ?, ?)";
			
			//connect to DB
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, feedbackType);
			pstmt.setString(2, feedbackValue);
			pstmt.setString(3, comment);
			pstmt.setInt(4, listingID);
			pstmt.setTimestamp(5, timestampObject);
			pstmt.setString(6, username);
			
			System.out.println("Connecting to the DB.");
			
			pstmt.executeUpdate();
			System.out.println("Executing update.");	
			success = true;
		}catch (Exception e) {
			System.out.println("Login failed: An exception has occured" + e);
			success = false;
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

		return success;
	}
		public static Listing getListingData(int listingID, String username) {
			//preparing objects for connection
			PreparedStatement pstmt = null;
			Listing listing = new Listing();
			
			//check on console
			System.out.println("This is your DatabaseConnector Java Bean reporting.");
			System.out.println("The listing ID is " + listingID);
			
			
			
			try {
				String sql = "SELECT offerer_username, bidder_username FROM listing WHERE listing_ID = ? " 
						+ " AND (offerer_username = ? OR bidder_username = ?)";
				//connect to DB
				currentConn = ConnectionManager.getConnection();
				pstmt = currentConn.prepareStatement(sql);
				pstmt.setInt(1, listingID);
				System.out.println("Connecting to the DB.");
				
				rs = pstmt.executeQuery();
				System.out.println("Executing query.");
				boolean more = rs.next();
				
				if(!more) {
					System.out.println("No such data found.");
					}
				//if user exists set the isValid variable to true
				else if(more) {
					
					listing.setOffererUsername(rs.getString("offerer_username"));
					listing.setBidderUsername(rs.getString("bidder_username"));
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
			return listing;
		}
		public static ApprovalPoints getAPData(String feedbackValue, String transactionPartner) {
			
			PreparedStatement pstmt = null;
			ApprovalPoints approvalPoints = new ApprovalPoints();
			
			try {
				setSQLQuery(feedbackValue, transactionPartner);
				String sql = getSQLQuery();
				currentConn = ConnectionManager.getConnection();
				pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
				
				pstmt.setString(1, transactionPartner);
				System.out.println("Connecting to the DB.");
				
				System.out.println(pstmt.toString());
				rs = pstmt.executeQuery();
				System.out.println("Executing query.");
				boolean more = rs.next();
				
				if(!more) {
					System.out.println("No such data found.");
					}
				else if(more) {
					
					approvalPoints.setApprovalPoints(rs.getInt("approval_points"));
					
					if (feedbackValue.equals("positive")) {
						approvalPoints.setPositiveFeedbackCount(rs.getInt("positive_AP"));
						}
					else if (feedbackValue.equals("neutral")) {
						approvalPoints.setNeutralFeedbackCount(rs.getInt("neutral_AP"));
					}
					else {
						approvalPoints.setNegativeFeedbackCount(rs.getInt("negative_AP"));
					}
				}
				
			} catch (Exception e) {
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
			
			return approvalPoints;
		}
		public static void setSQLQuery (String feedbackValue, String transactionPartner) {
			if (feedbackValue.equals("positive")) {
				SQLQuery = "SELECT approval_points, positive_AP FROM user WHERE username = ?";
			}
			else if (feedbackValue.equals("neutral")) {
				SQLQuery = "SELECT approval_points, neutral_AP FROM user WHERE username = ?";
						
			}
			else if (feedbackValue.equals("negative")) {
				SQLQuery = "SELECT approval_points, negative_AP FROM user WHERE username = ?";
			}
		}
		public static String getSQLQuery () {
			return SQLQuery;
		}
		public static boolean updateUsersAP(ApprovalPoints aps, String transactionPartner, String feedbackValue){
			
			PreparedStatement pstmt = null;

			System.out.println("This is the updateUsersAP method reporting.");
			int approvalPoints = aps.getApprovalPoints();
			int positiveAP = 0;
			int neutralAP = 0;
			int negativeAP = 0;
			
			try {
				System.out.println("The value of feedback is:" + feedbackValue);
				String sql = getSQLUpdate(feedbackValue);
				System.out.println("The sql query: " + sql);
				
				currentConn = ConnectionManager.getConnection();
				pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
				
				pstmt.setInt(1, approvalPoints);
				
				if (feedbackValue.equals("positive")) {
					positiveAP = aps.getPositiveFeedbackCount();
					pstmt.setInt(2, positiveAP);
				}
				else if (feedbackValue.equals("neutral")) {
					neutralAP = aps.getNeutralFeedbackCount();
					pstmt.setInt(2, neutralAP);
				}
				else if (feedbackValue.equals("negative")) {
					negativeAP = aps.getNegativeFeedbackCount();
					pstmt.setInt(2, negativeAP);
				}
				
				pstmt.setString(3, transactionPartner);
				System.out.println(pstmt.toString());
				System.out.println("Connecting to the DB.");
				
				pstmt.executeUpdate();
				System.out.println("Executing update.");	
				success = true;
			}catch (Exception e) {
				System.out.println("Login failed: An exception has occured" + e);
				success = false;
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

			return success;
		}

	public static String getSQLUpdate (String feedbackValue) {
		System.out.println("This is the setSQLUpdate method. The feedbackValue here is: " + feedbackValue);
		
		if(feedbackValue.equals("positive")) {
			SQLUpdate = "UPDATE user SET approval_points = ?, positive_AP = ? WHERE username = ?";
		}
		else if (feedbackValue.equals("neutral")) {
			SQLUpdate = "UPDATE user SET approval_points = ?, neutral_AP = ? WHERE username = ?";
		}
		else if (feedbackValue.equals("negative")) {
			SQLUpdate = "UPDATE user SET approval_points = ?, negative_AP = ? WHERE username = ?";;
		}
		return SQLUpdate;
	}
	public static List<Feedback> getFeedbackData(int listingID) {
		
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		PreparedStatement pstmt = null;
		currentConn = ConnectionManager.getConnection();
		
		System.out.println("GetFeedbackData method connecting to the database");
				
				try {
					String sql = "SELECT feedback_value, feedback_comment, date, username FROM listingFeedback " +
							"WHERE listing_ID = ? ORDER BY date ASC";
					
					pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
					pstmt.setInt(1, listingID);
					
					ResultSet rs = pstmt.executeQuery();
					System.out.println("Executing query");
					
					if (!rs.isBeforeFirst()) {
						System.out.println("No such data can be found");
					} else {
						while(rs.next()) {
							System.out.println("This is the while loop!");
							//create new instance with each loop iteration
							Feedback feedback = new Feedback();
							Timestamp timestamp = rs.getTimestamp("date");
								if(timestamp != null) {
									Date date = new Date(timestamp.getTime());
									feedback.setDate(date);
								}
							feedback.setAuthor(rs.getString("username"));
							feedback.setFeedbackValue(rs.getString("feedback_value"));
							feedback.setFeedbackComment(rs.getString("feedback_comment"));
							
							feedbacks.add(feedback);
							
							}
						Feedback feedback = new Feedback();
						feedback.setFeedbacksList(feedbacks);
						for (Feedback f : feedbacks) {
							System.out.println(f);
						}
					}
				} catch (SQLException e) {
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
		return feedbacks;		
	}
}




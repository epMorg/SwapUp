package ct869.project.model;
//import java.text.*;
//import java.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
//import ct869.project.model.*;
import com.mysql.jdbc.PreparedStatement;


	
public class TransactionDataRetriever {
	
	private static Connection currentConn = null;
	static ResultSet rs = null;
	
	public static List<Listing> getClosedTransactions(String username) {
		
		ArrayList<Listing> closedlistings = new ArrayList<Listing>();
		PreparedStatement pstmt = null;
		currentConn = ConnectionManager.getConnection();
		
		try {
			String sql = "SELECT COUNT(listingFeedback.feedback_value) AS feedback_quantity, listing.listing_ID, listing.listing_keywords, listing.offerer_username, " +
					"listing.bidder_username " +
					"FROM listing LEFT JOIN listingFeedback ON listing.listing_ID=listingFeedback.listing_ID " +
					"WHERE (offerer_username = ? OR bidder_username = ?) AND listing.activity='closed'" +
					" GROUP BY listing.listing_ID";
			
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Executing query");
			
			while(rs.next()) {
				System.out.println("This is the while loop!");
				//create new instance with each loop iteration
				Listing listing = new Listing();
				
				listing.setFeedbackQuantity(rs.getInt("feedback_quantity"));
				listing.setListingID(rs.getInt("listing_ID"));
				listing.setListingKeywords(rs.getString("listing_keywords"));
				listing.setOffererUsername(rs.getString("offerer_username"));
				listing.setBidderUsername(rs.getString("bidder_username"));
				
				closedlistings.add(listing);	
				}
				Listing listing = new Listing();
				listing.setListingsList(closedlistings);
				for (Listing l : closedlistings) {
					System.out.println(l);
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

	return closedlistings;
	}

	public static List<Listing> getActiveTransactions(String username) {
		
		ArrayList<Listing> activelistings = new ArrayList<Listing>();
		PreparedStatement pstmt = null;
		currentConn = ConnectionManager.getConnection();
		
		try {
			String sql = "SELECT listing_ID, listing_description, listing_keywords, start_time FROM listing " +
					"WHERE activity='active' AND (offerer_username=? OR bidder_username=?)";
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Executing query");
			
			while(rs.next()) {
				System.out.println("This is the while loop!");
				//create new instance with each loop iteration
				Listing listing = new Listing();
				
				listing.setListingID(rs.getInt("listing_ID"));
				listing.setListingDescription(rs.getString("listing_description"));
				listing.setStartTime(rs.getString("start_time"));
				listing.setListingKeywords(rs.getString("listing_keywords"));
				
				activelistings.add(listing);	
				}
				Listing listing = new Listing();
				listing.setListingsList(activelistings);
				for (Listing l : activelistings) {
					System.out.println(l);
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

	return activelistings;
	}
}




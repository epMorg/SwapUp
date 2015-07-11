package ct869.project.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class IndividualListingDAO {
	static boolean success;
	static Connection currentConn = null;
	static ResultSet rs = null;


	//static method that returns a listing object
	//Receives a listingID as an argument
	public static Listing getIndividualListing(int soughtListingID) {

		//preparing objects for connection
		PreparedStatement pstmt = null;

		//create Listing object to store data retrieved from db
		Listing listing = new Listing();

		// listingID received as argument is used in SQL statement to define the particular listing being sought
		try {
			String sql = "SELECT * FROM listing WHERE listing_ID = " + soughtListingID;

			//Connection object and PreparedStatement object initialised. PS object receives SQL string as argument.
			currentConn = ConnectionManager.getConnection();
			pstmt = currentConn.prepareStatement(sql);

			System.out.println("Connecting to the DB.");

			// SQL query is executed and the retrieved data is stored in a ResultSet object
			rs = pstmt.executeQuery(sql);

			//establish a flag which signals whether there is data in the ResultSet object or not
			boolean more = rs.next();

			// if there is not data
			if(!more) {
				System.out.println("No such data found.");
			}

			//if Listing does exist, retrieve the data and assign values to various Listing attributes.
			else if(more) {

				listing.setListingID(rs.getInt("listing_ID"));
				listing.setListingDescription(rs.getString("listing_description"));
				listing.setListingState(rs.getString("activity"));
				listing.setOffererUsername(rs.getString("offerer_username"));
				//listing.setFeedback(rs.getString("listing_feedback"));
				//listing.setFeedbackComment(rs.getString("feedback_comment"));
			}

		}catch (Exception e) {
		}
		
		//exception handling routines
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
		// return Listing object to IndividualListingController.java
		return listing;
	}

	public static boolean changeListingStatus(int listingToBeRemovedID) throws SQLException{
		/*System.out.println("This is the individual listing DAO, removeAcceptedListing method");	
		currentConn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;
		try {
		String sql = "DELETE FROM listing WHERE listing_ID = ?";
		System.out.println("sql string initialised");
		pstmt = (PreparedStatement) currentConn.prepareStatement(sql);
		pstmt.setInt(1, listingToBeRemovedID);
		System.out.println("SQL statement has been set");
		
		pstmt.executeUpdate();
		/*System.out.println("sql executed");
		System.out.println("The value of the rowsUpdated variable " + rowsUpdated);*/
		PreparedStatement pstmt = null;  
		try {
			currentConn = ConnectionManager.getConnection();
			
	        
	       String sql = "UPDATE listing SET activity='closed' WHERE listing_ID=?";
	        sql.toString();
	        pstmt = (PreparedStatement) currentConn.prepareStatement(sql);
	        pstmt.setInt(1, listingToBeRemovedID);
	        
	        int rowsUpdated = pstmt.executeUpdate();
		
		if (rowsUpdated == 0) {
		    success = false;
			System.out.println("Listing updated.");
		} else {
		    success = true;
			System.out.println("Listing updated.");
		}
		
	}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//exception handling routines
		finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (Exception e) {}
				rs = null;
			}
			/*if (stmt != null) {
				try {
					stmt.close();
				}catch (Exception e) {}
				stmt = null;
			}*/
			if (currentConn != null) {
				try {
					currentConn.close();
				}catch (Exception e) {}
				currentConn = null;
			}
		}
		
		return success;
		
	}
	public static Listing getListingData(int listingID) {
	
		        //preparing objects for connection
		        PreparedStatement pstmt = null;
		        Listing listing = new Listing();
		        
		        
		        //check on console
		        System.out.println("This is your DatabaseConnector Java Bean reporting.");
		        System.out.println("The listing ID is " + listingID);
		        //System.out.println("Query: " + searchQuery);
		        
		        
		        try {
		            String sql = "SELECT * FROM listing WHERE listing_ID = ?";
		            
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
		                
		                listing.setListingID(rs.getInt("listing_ID"));
		                listing.setListingDescription(rs.getString("listing_description"));
		                listing.setListingState(rs.getString("activity"));
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
}

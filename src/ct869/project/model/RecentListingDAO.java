package ct869.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class RecentListingDAO {

	//Connection and ResultSet objects declared
	static Connection conn = null;
	static ResultSet rs = null;


	//Retrieves recent listings from db and returns and ArrayList of Listing objects.
	// User input which determines how many records to retrieve is passed as argument.
	public static ArrayList<Listing> retrieveRecentListings(int noOfListings) throws SQLException{

		// for storing recent Listing objects that will be retrieved from the db
		ArrayList <Listing> recentListings = new ArrayList <Listing>();
		
		// create database connection object
		conn = ConnectionManager.getConnection();

		// the received argument 'noOfListings' is used in SQL statement to set the number of Listings to be retrieved
		String sql  = ("SELECT offerer_username, listing_ID, activity, listing_description, start_time FROM listing WHERE activity  = 'active' ORDER BY start_time DESC LIMIT "+noOfListings);  

		//declare Statement object
		Statement stmt = null;

		// statement object is initialised. sql string is passed to the statement object's executeQuery method.
		// the result is stored in the ResultSet object
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// iterate through ResultSet object's rows
		try {
			while (rs.next()){

				//each iteration a new Listing object is created which will be added to the ArrayList
				// the required data is retrieved from the Resultset object and then set to the various attributes of the Listing object
				
				Listing recentListing = new Listing();

				
				int listingID = rs.getInt("listing_ID");
				recentListing.setListingID(listingID);
				

				String offererUsername = rs.getString("offerer_username");
				recentListing.setOffererUsername(offererUsername);
				
				String listingDescription = rs.getString("listing_description");
				recentListing.setListingDescription(listingDescription);
			
				String startTime = rs.getString("start_time");
				recentListing.setStartTime(startTime);
			
				String listingState = rs.getString("activity");
				recentListing.setListingState(listingState);
	
				// add Listing object to Listing object ArrayList
				recentListings.add(recentListing);

			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}

		// ArrayList 'recentListings' is returned to the RecentListings.java Controller
		return recentListings;

	}
}




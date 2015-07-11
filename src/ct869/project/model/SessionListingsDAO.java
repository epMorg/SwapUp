package ct869.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionListingsDAO {
	static Connection conn = null;
	static ResultSet rs = null;


	public static ArrayList<Listing> getListings(Listing aListing) throws SQLException{

		ArrayList <Listing> myListings = new ArrayList <Listing> ();

		String username = aListing.getOffererUsername();

		String sql = "SELECT * FROM listing WHERE offerer_username = '"+ username+"'";
		sql.toString();
		conn = (Connection) ConnectionManager.getConnection();

		Statement stmt = null;

		stmt = (Statement) conn.createStatement();

		rs = stmt.executeQuery(sql);

		while (rs.next()){

			Listing listing = new Listing();

			int listingID = rs.getInt("listing_ID");
			listing.setListingID(listingID);

			String offererUsername = rs.getString("offerer_username");
			listing.setOffererUsername(offererUsername);

			String listingDescription = rs.getString("listing_description");
			listing.setListingDescription(listingDescription);

			String startTime = rs.getString("start_time");
			listing.setStartTime(startTime);

			String listingState = rs.getString("activity");
			listing.setListingState(listingState);

			// add Listing object to Listing object ArrayList
			myListings.add(listing);

		}
		return myListings;

	}
}

package ct869.project.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;

public class Search {
	//Connection and ResultSet objects declared
	static Connection currentConn = null;
	static ResultSet rs = null;

	//Retrieves searched listings from db and returns and ArrayList of Listing objects.
	// User input (keywords) which defines what is searched and retrieved is passed as argument.
	public static ArrayList <Listing> searchFor(String searchItem) throws ParseException{
	
	// for storing recent Listing objects that will be retrieved from the db
	ArrayList <Listing> searchedListings = new ArrayList <Listing> ();
	
	// create database connection object
	currentConn = ConnectionManager.getConnection();
	
	//declare Statement object
	Statement stmt = null;
	
	// the received argument 'searchItem' is used in SQL statement to define what is being searched for
	String query = "SELECT listing_ID, offerer_username, listing_description, activity, start_time FROM listing WHERE listing_keywords LIKE '%"+searchItem+"%'";
	
	
	try {
		stmt = currentConn.createStatement();
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	try {
		rs = stmt.executeQuery(query);
	} catch (SQLException e1) {
		e1.printStackTrace();
	}

	
	// iterate through ResultSet object's rows
	try {
		while (rs.next()){
			
		//each iteration a new Listing object is created which will be added to the ArrayList
		// the required data is retrieved from the Resultset object and then set to the various attributes of the Listing object	
			
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
		searchedListings.add(listing);
		
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// ArrayList 'searchedListings' is returned to the SearchController.java
	return searchedListings;
	}
}
	


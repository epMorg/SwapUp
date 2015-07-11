package ct869.project.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class BiddingDAO {

	// static objects declared
	static Connection currentConn = null;
	static ResultSet rs = null;
	static boolean success;

	// Method returns a boolean value to indicate whether a bid was successfully store in db or not.
	// A bid object is passed an argument. Data is extracted from object and stored in db.
	public static boolean addBidToDatabase(Bid bid) throws SQLException{


		currentConn = (Connection) ConnectionManager.getConnection();
		PreparedStatement pstmt = null;	

		// extract data from bid object
		int associatedListingID = bid.getAssociatedListingID();
		String bidText = bid.getBidText();
		String biddingUser = bid.getUserName();

		//create INSERT SQL statement. Pass data extracted from bid object as values
		// to be inserted.
		String sql = "INSERT INTO bid (bid_Text,userName, listing_ID)"+
				"VALUES (?,?,?)";

		pstmt = (PreparedStatement) currentConn.prepareStatement(sql);
		pstmt.setString(1, bidText);
		pstmt.setString(2, biddingUser);
		pstmt.setInt(3, associatedListingID);

		pstmt.executeUpdate();

		//indicating that bid has been successfully recorded in db
		return true;
	}

	// Method returns an ArrayList which stores bid objects which will be retrieved from the db.
	// What is being sought here are bid objects that pertain to a particular listing.
	// A bid object is passed as an argument.Within this bid object, only the attribute indicating the associated 
	//listing ID has been initialised. This is used to retrieve the remaining attribute values of all bid objects
	// that hold the same associated listingID value.
	public static ArrayList<Bid> retrieveBidsFromDatabase(Bid bid) throws SQLException{

		// for storing bid objects for a particular listing
		ArrayList<Bid> bidList = new ArrayList <Bid> ();

		// retrieve associated listingID
		int intListingID = bid.getAssociatedListingID();

		PreparedStatement pstmt = null;	

		//connect to db
		currentConn = (Connection) ConnectionManager.getConnection();

		//associated listingID is used in SQL statement to target and retrieve the relevant bid objects
		String sql = "SELECT * FROM bid WHERE listing_ID = " + intListingID;

		// PreparedStatement object is initialised and the SQL query is passed to it
		pstmt = (PreparedStatement) currentConn.prepareStatement(sql);

		// results stored in ResultSet object
		rs = pstmt.executeQuery(sql);

		// iterate through ResultSet object's rows
		while (rs.next()){

			//each iteration a new Bid object is created which will be added to the ArrayList.
			// the required data is retrieved from the Resultset object and then set to the 
			//various attributes of the Bid object.

			Bid newBid = new Bid ();

			int listingID = rs.getInt("listing_ID");
			newBid.setAssociatedListingID(listingID);

			String userName = rs.getString("userName");
			newBid.setUserName(userName);

			String bidText = rs.getString("bid_Text");
			newBid.setBidText(bidText);

			String bidDate = rs.getString("date");
			newBid.setDateOfBid(bidDate);

			// add Listing object to Listing object ArrayList
			bidList.add(newBid);

		}
		// ArrayList 'bidList' is returned to the RecentListings.java Controller
		return bidList;
	}

	public static ArrayList<Bid> retrieveBidsToMeFromDatabase(ArrayList <Integer> associatedListingIDs) throws SQLException{

		// for storing bid objects relating to a particular user's listings
		ArrayList<Bid> bidsToMeList = new ArrayList <Bid> ();

		Statement stmt = null;	

		//connect to db
		currentConn = (Connection) ConnectionManager.getConnection();
		
		stmt = (Statement) currentConn.createStatement();

		//associated listingID is used in SQL statement to target and retrieve the relevant bid objects
		
		for (int i = 0; i < associatedListingIDs.size(); i++){
			ResultSet rs = stmt.executeQuery("SELECT * FROM bid WHERE listing_ID = '" + associatedListingIDs.get(i)+"'");
			
		    while(rs.next()){
		    	
		    	Bid bidToMe = new Bid();
		    	
		    	int bidID = rs.getInt("bid_ID");
		    	bidToMe.setAssociatedListingID(bidID);
		    	
		    	int listingID = rs.getInt("listing_ID");
				bidToMe.setAssociatedListingID(listingID);

				String userName = rs.getString("userName");
				bidToMe.setUserName(userName);

				String bidText = rs.getString("bid_Text");
				bidToMe.setBidText(bidText);

				String bidDate = rs.getString("date");
				bidToMe.setDateOfBid(bidDate);
		    	
		    	bidsToMeList.add(bidToMe);
		    }
		    rs.close();   
		}
		 return bidsToMeList;
	}
	
	public static boolean removeAcceptedBid(int bidAssociatedListingIDToBeRemoved) throws SQLException{
		
		currentConn = (Connection) ConnectionManager.getConnection();
		
		Statement stmt = null;
		
		String sql = "DELETE FROM bid WHERE listing_ID = "+bidAssociatedListingIDToBeRemoved;
		
		stmt = (Statement) currentConn.createStatement();
		
		
		int rowsUpdated = stmt.executeUpdate(sql);
		
		if (rowsUpdated == 0) {
		    success = false;
			System.out.println("Bid not deleted.");
		} else {
		    success = true;
			System.out.println("Bid deleted");
		}
		return success;
		
	}
}
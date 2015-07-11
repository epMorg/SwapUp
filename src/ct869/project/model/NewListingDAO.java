package ct869.project.model;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class NewListingDAO {


	public static boolean insertNewListing(Listing newListing) throws SQLException{

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement pstmt = null;


		String sql = "INSERT INTO listing (listing_description, offerer_username, listing_keywords)"+
				"VALUES (?,?,?)";

		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, newListing.getListingDescription());
		pstmt.setString(2, newListing.getOffererUsername());
		pstmt.setString(3, newListing.getListingKeywords());
		
		pstmt.executeUpdate();
	
		return true;
	}

}

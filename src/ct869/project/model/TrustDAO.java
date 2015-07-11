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

public class TrustDAO {
	
	private static boolean controller;
	private static Connection currentConn = null;
	private static ResultSet rs = null;
	
	
	public static boolean checkWebOfTrust(String username) {
		System.out.println("This is the checkWebOfTrust method.");
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT node FROM arc WHERE node=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			boolean more = rs.next();
		
		
			if(!more) {
			System.out.println("No such data found.");
			controller = false;
			}
			//if user exists set the isValid variable to true
			else if(more) {
					controller = true;
				}
			
			}
		catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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

	return controller;
}
	public static List<User> getBarteres(String username) {
		ArrayList<User> users = new ArrayList<User>();
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT DISTINCT LEAST(offerer_username, bidder_username) " +
					"AS barterer_column1, " +
					"GREATEST(bidder_username, offerer_username) AS barterer_column2 " +
					"FROM listingPractice WHERE offerer_username=? OR bidder_username=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			List<String> barterers = new ArrayList<String>();
			while(rs.next()) {
				System.out.println("This is the while loop!");
				//create new instance with each loop iteration
				String offererUname = rs.getString("barterer_column1");
				String bidderUname = rs.getString("barterer_column2");
				
				if (!offererUname.equals(username)) {
					barterers.add(offererUname);
				} else if (!bidderUname.equals(username)) {
					barterers.add(bidderUname);
				}
				
			}
				
				for (String barterer : barterers) {
					System.out.println("These are the barterers: " + barterer);
				}
				List<String> listOfTrustees = new ArrayList<String>();
				listOfTrustees = getTrustedUsers(username);
				for (String trustee : listOfTrustees) {
					System.out.println("These are the retrieved trustees: " + trustee);
				}
				barterers.removeAll(listOfTrustees);
				System.out.println("Barterers after the operation" + barterers);
				
				
				for (String barterer : barterers) {
					System.out.println(barterer);
					User user = new User();
					user.setUserName(barterer);
					users.add(user);
				}
			
	}catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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
	return users;
	}
	public static boolean checkActivity(String username) {
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT offerer_username, bidder_username FROM listing WHERE offerer_username=? OR bidder_username=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			boolean more = rs.next();
		
		
			if(!more) {
			System.out.println("The user hasn't yet participated in any listings.");
			controller = false;
			}
	
			else {
					controller = true;
				}
	}catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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

	return controller;

}
	public static User getBartererInfo(String username) {
		User user = new User();
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT user_ID, username, date_of_join, gender, " +
					"approval_points, profile_blurb, social_role, positive_AP, negative_AP, neutral_AP " +
					"FROM user WHERE username=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			
			while(rs.next()) {
				System.out.println("This is the while loop!");
				
				Timestamp timestamp = rs.getTimestamp("date_of_join");
				if(timestamp != null) {
					Date date = new Date(timestamp.getTime());
					user.setDate(date);
				}
				user.setUserStudentID(rs.getInt("user_ID"));
				user.setUserName(rs.getString("username"));
				user.setUserGender(rs.getString("gender"));
				user.setApprovalPoints(rs.getInt("approval_points"));
				user.setProfileBlurb(rs.getString("profile_blurb"));
				user.setSocialRole(rs.getString("social_role"));
				user.setPositiveAP(rs.getInt("positive_AP"));
				user.setNegativeAP(rs.getInt("negative_AP"));
				user.setNeutralAP(rs.getInt("neutral_AP"));
			}
			
	}catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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
	
		return user;
	}
	public static boolean insertVerticesAndEdges(Node vertex, Node connectTo, Node edge) {
		
		String vrtx = vertex.getVertex();
		String pointAt = connectTo.getConnectTo();
		String edges = edge.getEdges();
		
		PreparedStatement pstmt = null;

		System.out.println("This is the insertVerticesAndEdges method reporting.");
		
		Calendar calendar = Calendar.getInstance();
		java.sql.Timestamp timestampObject = new java.sql.Timestamp(calendar.getTime().getTime());
		
		try {
			String sql = "INSERT INTO arc(node, pointed_at, arc, date) VALUES(?, ?, ?, ?)";
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, vrtx);
			pstmt.setString(2, pointAt);
			pstmt.setString(3, edges);
			pstmt.setTimestamp(4, timestampObject);
			System.out.println(pstmt.toString());
			System.out.println("Connecting to the DB.");
			
			pstmt.executeUpdate();
			System.out.println("Executing update.");	
			controller = true;
		}catch (Exception e) {
			System.out.println("Login failed: An exception has occured" + e);
			controller = false;
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
		return controller;
}
public static List<String> getTrustedUsers(String uname) {
		List<String> trustees = new ArrayList<String>();
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT pointed_at FROM arc WHERE node=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, uname);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			
			while(rs.next()) {
				System.out.println("This is the while loop!");
				
				String trustee = rs.getString("pointed_at");
				trustees.add(trustee);
			}
		}catch (Exception e) {
			System.out.println("Login failed: An exception has occured" + e);

		}//exception handling routines

		return trustees;
	}
	public static List<Node> getTrustees(String username) {
		List<Node> endNodes = new ArrayList<Node>();
		PreparedStatement pstmt = null;
		
		try {
			String sql = "SELECT pointed_at FROM arc WHERE node=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			
			while(rs.next()) {
				System.out.println("This is the while loop!");
				//create new instance with each loop iteration
				Node node = new Node();
				node.setConnectTo(rs.getString("pointed_at"));
				endNodes.add(node);
				}	
	}catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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
		return endNodes;
	}
	public static boolean severBondOfTrust(Node vertex, Node connectTo, Node edge) {
		
		String vrtx = vertex.getVertex();
		String pointAt = connectTo.getConnectTo();
		String edges = edge.getEdges();
		
		PreparedStatement pstmt = null;

		System.out.println("This is the severBondOfTrust method reporting.");
		
		try {
			String sql = "DELETE FROM arc WHERE node=? AND pointed_at=? AND arc=?";
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, vrtx);
			pstmt.setString(2, pointAt);
			pstmt.setString(3, edges);
			System.out.println(pstmt.toString());
			System.out.println("Connecting to the DB.");
			
			int rowsUpdated = pstmt.executeUpdate();
			
			if (rowsUpdated == 0) {
			    //controller = false;
				System.out.println("Bond of Trust not deleted.");
			} else {
			    //controller = true;
				System.out.println("Bond of Trust deleted");
			}
			String sql2 = "SELECT arc_ID FROM arc WHERE node=? AND pointed_at=?";
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql2);
			
			pstmt.setString(1, vrtx);
			pstmt.setString(2, pointAt);
			System.out.println(pstmt.toString());
			System.out.println("Connecting to the DB.");
			rs = pstmt.executeQuery();
			int result=0;
			while(rs.next()) {
				result = rs.getInt("arc_ID");
				if (result == 0) {
					//controller = true;
					System.out.println("Bond of Trust deleted");
				} else {
					//controller = false;
					System.out.println("Bond of Trust not deleted.");
				}
			}
			
		}catch (Exception e) {
			controller = false;
			System.out.println("Bond of Trust not deleted.");
			System.out.println("Login failed: An exception has occured" + e);

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
		return controller;
	}
	public static List<String> getEdgeList() {
		List<String> edges = new ArrayList<String>();
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT arc FROM arc";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			//boolean more = rs.next();
			
			//if user does not exist set the isValid variable to false
			if(!rs.isBeforeFirst()) {
				System.out.println("No edeges found.");
				} else {
					while(rs.next()) {
						System.out.println("This is the while loop!");
						String edge = new String();
						edge = rs.getString("arc");
						edges.add(edge);
			}
		}
	}catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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
		return edges;
	}
	public static boolean checkIfTrusted (String username) {
		System.out.println("This is the checkIfTrusted method.");
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT node, pointed_at FROM arc WHERE node=? OR pointed_at=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			boolean more = rs.next();
		
		
			if(!more) {
			System.out.println("No such data found.");
			controller = false;
			}
			//if user exists set the isValid variable to true
			else if(more) {
					controller = true;
				}
			
			}
		catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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
		return controller;	
	}
	public static List<Node>getVertices(String username) {
		
		List<Node> vertices = new ArrayList<Node>();
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT node FROM arc WHERE pointed_at=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			
			while(rs.next()) {
				System.out.println("This is the while loop!");
				
				Node node = new Node();
				node.setVertex(rs.getString("node"));
				vertices.add(node);
			}
			
	}catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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
	
		return vertices;
}
public static List<Node>getConnectedTos(String username) {
		
		List<Node> connectedTos = new ArrayList<Node>();
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "SELECT pointed_at FROM arc WHERE node=?";
			
			currentConn = ConnectionManager.getConnection();
			pstmt = (PreparedStatement)currentConn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			System.out.println("Connecting to the DB.");
			
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			System.out.println("Executing query.");
			
			while(rs.next()) {
				System.out.println("This is the while loop!");
				
				Node node = new Node();
				node.setConnectTo(rs.getString("pointed_at"));
				connectedTos.add(node);
			}
			
	}catch (Exception e) {
		System.out.println("Login failed: An exception has occured" + e);

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
	
		return connectedTos;
}
}
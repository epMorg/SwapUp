package ct869.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ct869.project.model.Listing;
import ct869.project.model.RecentListingDAO;

@WebServlet("/RecentListings")
public class RecentListings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RecentListings() {
        super();
       
    }
    // Get Method processes a request from the user to view x amount of recent listings.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Capture input from User. Input is integer value which determines how many recent listings to display
		String noOfListings = request.getParameter("numberOfListings");
		int numberOfListings = Integer.parseInt(noOfListings);
		System.out.println("Number of listings: " + numberOfListings);
		//Data structure for storing Listing object(s) after they have been retrieved from the db
		ArrayList <Listing> recentListings = new ArrayList <Listing> ();
		
		//call to RecentListingDAO.java -> getRecentListings(). User input is passed as argument.
		// An ArrayList of Listing objects is returned and stored in 'recentListings'.
			try {
				recentListings = RecentListingDAO.retrieveRecentListings(numberOfListings);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		// Set 'recentListings' ArrayList as attribute of request object.
		//Forward request object to latestListings.jsp
		RequestDispatcher rd = null;
		request.setAttribute("recentListings", recentListings);
		rd = request.getRequestDispatcher("/latestListings.jsp");
		rd.forward(request, response); 
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}
}

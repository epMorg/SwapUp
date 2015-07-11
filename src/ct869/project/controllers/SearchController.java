package ct869.project.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ct869.project.model.Listing;
import ct869.project.model.Search;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchController() {
		super();
	}
	
	//Get method receives user input. User inputs keyword to search for a specific listing
	// All listings which match this keyword will be returned
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// for storing the Listing objects will be retrieved from the db
		ArrayList <Listing> searchedListings = new ArrayList <Listing> ();

		// capture the keywords inputed by the user
		String searchItem = request.getParameter("searchItem");

		// a call is made to Search.java -> searchFor() method. The user's keyword is passed as argument.
		// searchFor() returns and ArrayList of relevant Listing objects
		try {
			searchedListings = Search.searchFor(searchItem);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if ( searchedListings.isEmpty()){
			
			String noMessage = "Nothing here today fitting that description. Come and try again later!";
			
			request.setAttribute("noMessage", noMessage);
		}
		
		else if ( !searchedListings.isEmpty()){
			
			String yesMessage = "How do you likem them apples?";
			
			request.setAttribute("yesMessage", yesMessage);
		}
		
		// the ArrayList is set as an attribute of the request object and is forwarded to searchResultDisplay.jsp
		RequestDispatcher rd = null;
		request.setAttribute("searchedListings", searchedListings);
		rd = request.getRequestDispatcher("/searchResultDisplay.jsp");
		rd.forward(request, response); 
	}
}

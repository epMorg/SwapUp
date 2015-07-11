package ct869.project.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ct869.project.model.Bid;
import ct869.project.model.BiddingDAO;
import ct869.project.model.IndividualListingDAO;
import ct869.project.model.Listing;


@WebServlet("/IndividualListingController")
public class IndividualListingController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public IndividualListingController() {
		super();

	}
	
	// In order to generate an individual Listing page the ListingID is passed via link (when user clicks on it) from either
	// the searchResultDisplay.jsp or the latestListing.jsp
	//The ID is used to retrieve all the details of the Listing from the db and also any Bid objects
	//which are associated (via db foreign key) with the Listing object in question.
	//The details of an individual listings and details of any related bids will be displayed on listing.jsp
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// for storing Bid objects associated with Listings
		ArrayList <Bid>  bidList = new ArrayList<Bid> ();

		RequestDispatcher rd = null;

		// retrieve listing ID number from request object
		String idNo = request.getParameter("id");
		int idNumber = Integer.parseInt(idNo);

		// create a new Listing object which will store the Listing object returned from the 
		//getIndividualListing() method called from IndividualListingDAO.java
		// The id number is passed as an argument to aforementioned method.
		try {
			Listing listing = new Listing();
			listing = IndividualListingDAO.getIndividualListing(idNumber); 

			//create a new Bid object and set its ListingID attribute to the id number
			// this will allows us to query the db and retrieve 
			//all the info regarding bids for the Listing in question
			Bid bid = new Bid();
			bid.setAssociatedListingID(idNumber);

			//all relevant bid objects are stored in ArrayList 'bidList'
			bidList = BiddingDAO.retrieveBidsFromDatabase(bid);
			
			//Listing object and ArrayList of Bid objects are set as attributes of the request object
			// request object is forwarded to listing.jsp where Listing and Bid data are displayed together
			request.setAttribute("listing", listing);
			request.setAttribute("bidList", bidList);
			rd = request.getRequestDispatcher("/listing.jsp");

		} catch (Exception e) {
			System.out.println("There was an exception thrown:" + e);
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

}

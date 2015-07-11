package ct869.project.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ct869.project.model.IndividualListingDAO;
import ct869.project.model.Listing;

/**
 * Servlet implementation class ListingServlet
 */
@WebServlet(description = "Takes the user to the individual listing page", urlPatterns = { "/ListingServlet" })
public class ListingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String listingIDString = request.getParameter("id");
		System.out.println("This is the ListingServlet speaking");
		System.out.println("The action is: " + action + " and the listing ID is:" + listingIDString);
		int listingID = Integer.parseInt(listingIDString);
		RequestDispatcher rd = null;
		
		try {
			Listing listing = new Listing();
			listing = IndividualListingDAO.getListingData(listingID); 
		
			request.setAttribute("listing", listing);
			rd = request.getRequestDispatcher("/closedlisting.jsp");
		
	} catch (Exception e) {
			System.out.println("There was an exception thrown:" + e);
			rd = request.getRequestDispatcher("/error.jsp");
	}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

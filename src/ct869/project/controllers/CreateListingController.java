package ct869.project.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ct869.project.model.Listing;
import ct869.project.model.NewListingDAO;

@WebServlet("/CreateListingController")
public class CreateListingController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateListingController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean success = false;

		HttpSession session = request.getSession();

		String newListingDescription = request.getParameter("listingDescription");
		String newListingKeywords = request.getParameter("keywords");

		Listing newListing = new Listing();

		String newListingUsername = (String) session.getAttribute("username");

		newListing.setListingDescription(newListingDescription);
		newListing.setOffererUsername(newListingUsername);
		newListing.setListingKeywords(newListingKeywords);

		try {
			success = NewListingDAO.insertNewListing(newListing);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		if (success){
			
			response.sendRedirect("listingSuccessfullyCreated.jsp");
		}
	}
}

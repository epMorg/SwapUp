package ct869.project.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ct869.project.model.BiddingDAO;
import ct869.project.model.IndividualListingDAO;

@WebServlet("/AcceptBidController")
public class AcceptBidController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
    public AcceptBidController() {
        super();
      
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acceptedListingID = request.getParameter("acceptBid");
		System.out.println("The ID of the listing is: " + acceptedListingID);
		int listingToBeRemovedID = Integer.parseInt(acceptedListingID);
		System.out.println("The listing ID as an integer: " + listingToBeRemovedID);
		boolean success = false;
		
		try {
			if (IndividualListingDAO.changeListingStatus(listingToBeRemovedID)&& BiddingDAO.removeAcceptedBid(listingToBeRemovedID)){
				success = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (success){
			
			response.sendRedirect("bidAccepted.jsp");
		}
		
		else response.sendRedirect("error.html");
		}

}

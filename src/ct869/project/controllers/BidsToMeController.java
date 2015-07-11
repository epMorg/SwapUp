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
import javax.servlet.http.HttpSession;

import ct869.project.model.Bid;
import ct869.project.model.BiddingDAO;
import ct869.project.model.Listing;


@WebServlet("/BidsToMeController")
public class BidsToMeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BidsToMeController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		ArrayList <Integer> associatedListingIDs = new ArrayList <Integer>();		
		
		ArrayList <Listing> listingList = (ArrayList<Listing>) session.getAttribute("myListings");
		
		System.out.println("This is BidsToMeController with list size :" +listingList.size());
		
		ArrayList <Bid> bidsToMeList = new ArrayList <Bid> ();
		
		for (Listing l : listingList){
			
			associatedListingIDs.add(l.getListingID());
		}	
		
		try {
			bidsToMeList = BiddingDAO.retrieveBidsToMeFromDatabase(associatedListingIDs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("bidsToMeList", bidsToMeList);
		
		RequestDispatcher rd = request.getRequestDispatcher("bidsForMyListings.jsp");
		rd.forward(request, response);	
	}
}

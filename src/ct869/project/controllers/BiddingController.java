package ct869.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ct869.project.model.Bid;
import ct869.project.model.BiddingDAO;


@WebServlet("/BiddingController")
public class BiddingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BiddingController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.sendRedirect("indexPage.jsp");
	}

	// Depending on the value of the action parameter, the Post method will either direct the user to
	// biddingPage.jsp in order to make a bid OR it will receive a Posted bid and place it in the db
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println("Session username retrieved is: " + username);
		RequestDispatcher rd = null;
		if(username==null){
			response.sendRedirect("login.html");
			return;
		} 
		// when being called by the 'Make a Bid' button on listing.jsp
		
		
		
		else if(action.equals("grabID")){
		
		
		// capture request parameter value
		String soughtListingID = request.getParameter("id");
		
		// set soughListingID as an attribute of the request object and forward it to biddingPage.jsp
		request.setAttribute("soughtListingID", soughtListingID);
		rd = request.getRequestDispatcher("biddingPage.jsp");
		rd.forward(request,response);
		}

		// when being called by the 'Post Bid' button on biddingPage.jsp
		else if (action.equals("dbInsert")){
			
			// capture the text entered by the user outlining their bid and id number of the 
			// listing that is being sought after. 
			String bidText = (String) request.getParameter("bid");
			String associatedListingID = request.getParameter("listingID");
			int parsedAssociatedListingID = Integer.parseInt(associatedListingID);
			
			
			// retrieve the userName of the bidder from the Session object
			session = request.getSession();
			String userName = (String) session.getAttribute("username");
			
			// create a Bid object and store the previously attained data
			Bid bid = new Bid();
			bid.setBidText(bidText);
			bid.setAssociatedListingID(parsedAssociatedListingID);
			bid.setUserName(userName);
			
			//set a boolean flag to signal whether the Bid was successfully made and recorded or not.
			boolean success = false;
			
			//pass the Bid object as an argument to the addBidToDatabase() method of BiddingDAO.java
			// boolean flag is assigned a value to indicate success or not.
			try {
			success = BiddingDAO.addBidToDatabase(bid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{ 
				if (success){
					
				//redirect user to notification of successful bid
				response.sendRedirect("successfulBid.jsp");
			}
				else{
					System.out.println("Oops. Please try again.");
				}
			}
		}
	}
}


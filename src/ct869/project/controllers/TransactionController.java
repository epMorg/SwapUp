package ct869.project.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ct869.project.model.*;
/**
 * Servlet implementation class TransactionController
 */
@WebServlet(description = "Transaction controller", urlPatterns = { "/TransactionController" })
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String transactionType = request.getParameter("chooseTransaction");
		System.out.println("This is the TransactionServlet speaking.");
		System.out.println("The transaction type chosen: " + transactionType);
		HttpSession session = request.getSession(false);
		
		if (transactionType.equals("active")) {
			String username = (String)session.getAttribute("username");
			System.out.println("Current user's username is: " + username);
			
			List<Listing> activeListings = new ArrayList<Listing>();
			activeListings = TransactionDataRetriever.getActiveTransactions(username);
			if (activeListings.isEmpty()) {
				String activeListingsMessage = "No " + transactionType + " transactions for " 
						+ username + " at the moment.";
				session.setAttribute("activeListingsMessage", activeListingsMessage);
				System.out.println(activeListingsMessage);
			} else {
				session.setAttribute("activeListings", activeListings);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/activeListingsList.jsp");
			rd.forward(request, response);
		}
		else if(transactionType.equals("closed")) {
			String username = (String)session.getAttribute("username");
			System.out.println("Current user's username is: " + username);
			
			List<Listing> closedListings = new ArrayList<Listing>();
			closedListings = TransactionDataRetriever.getClosedTransactions(username);
			if (closedListings.isEmpty()) {
				String closedListingsMessage = "No " + transactionType + " transactions for " 
						+ username + " at the moment.";
				session.setAttribute("closedListingsMessage", closedListingsMessage);
				System.out.println(closedListingsMessage);
			} else {
				session.setAttribute("closedListings", closedListings);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/closedListingsList.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}

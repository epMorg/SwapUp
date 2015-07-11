package ct869.project.controllers;

import java.io.IOException;
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
 * Servlet implementation class FeedbackServlet
 */
@WebServlet(description = "Handles feedback requests", urlPatterns = { "/FeedbackServlet" })
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		String username = (String)session.getAttribute("username");
		System.out.println("Current users' username is: " + username);
		String transactionIDString = request.getParameter("listingId");
		String offererUsername = request.getParameter("offererUname");
		String bidderUname = request.getParameter("bidderUname");
		String feedbackValue = request.getParameter("feedback");
		String feedbackComment = request.getParameter("feedbackComment");
		String action = request.getParameter("action");
		System.out.println("The action that's been called is named: " + action);
		
		System.out.println("The parameters are: " + transactionIDString + " " + offererUsername + " " 
				+ bidderUname + " " + feedbackValue + " " + feedbackComment);
		String transactionPartner = "";
		if (username.equals(offererUsername)) {
			transactionPartner = bidderUname;
			System.out.println("The name of your transaction partner is: " + transactionPartner);
		} else {
			transactionPartner = offererUsername;
			System.out.println("The name of your transaction partner is: " + transactionPartner);
		}
		if (action.equals("leaveFeedback")) {
			try {
				
				session.setAttribute("transactionPartner", transactionPartner);
				session.setAttribute("listingID", transactionIDString);
				session.setAttribute("offererUsername", offererUsername);
				session.setAttribute("bidderUsername", bidderUname);
				RequestDispatcher rd = request.getRequestDispatcher("/feedbackForm.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				System.out.println("An exception was thrown: " + e);
			}
		}
		else if (action.equals("giveFeedback")) {
				System.out.println("This is the else if statement");
		
			try {
				String feedbackType = "";
				if (username.equals(offererUsername)) {
					feedbackType = "OfToBdFeedback";
					System.out.println("The type of feedback is:" + feedbackType);
				} else {
					System.out.println("We're dealing with a bidder to offerer feedback operation.");
					feedbackType = "BdToOfFeedback";
					System.out.println("The type of feedback is:" + feedbackType);
				}
				if (feedbackComment == null) {
					feedbackComment = "";
					System.out.println("The feedback comment is now: " + feedbackComment);
				}
				
				int listingID = Integer.parseInt(transactionIDString);
				System.out.println("The listing ID as an integer: " + listingID);
				if (FeedbackDAO.updateFeedbackTable(listingID, feedbackType, feedbackValue, 
						feedbackComment, username)) {
					System.out.println("Data inserted into the FEEDBACK TABLE succesfully.");
					}
				
				ApprovalPoints approvalPoints = FeedbackDAO.getAPData(feedbackValue, transactionPartner);
				int positiveAP = 0;
				int neutralAP = 0;
				int negativeAP = 0;
				int appPts = approvalPoints.getApprovalPoints();
				System.out.println("All AP before the method call: " + appPts);
				
				if (feedbackValue.equals("positive")) {
					positiveAP = approvalPoints.getPositiveFeedbackCount();
					System.out.println("Positive AP before: " + positiveAP);
					appPts = approvalPoints.incrementApprovalPoints();
					approvalPoints.setApprovalPoints(appPts);
					System.out.println("All AP after the method call: " + appPts);
					int positiveAPaftr = approvalPoints.getPositiveFeedbackCount();
					System.out.println("Positive AP after the method call: " + positiveAPaftr);
					approvalPoints.setPositiveFeedbackCount(positiveAPaftr);
					}
				else if (feedbackValue.equals("neutral")) {
					neutralAP = approvalPoints.getNeutralFeedbackCount();
					System.out.println("Neutral AP before: " + neutralAP);
					appPts = approvalPoints.giveNeutralFeedback();
					approvalPoints.setApprovalPoints(appPts);
					System.out.println("All AP after method call: " + appPts);
					int neutralAPaftr = approvalPoints.getNeutralFeedbackCount();
					System.out.println("Neutral AP after the method call: " + neutralAPaftr);
					approvalPoints.setNeutralFeedbackCount(neutralAPaftr);
				}
				else {
					negativeAP = approvalPoints.getNegativeFeedbackCount();
					System.out.println("Negative AP before: " + negativeAP);
					appPts = approvalPoints.decrementApprovalPoints();
					approvalPoints.setApprovalPoints(appPts);
					System.out.println("All AP after method call: " + appPts);
					int negativeAPaftr = approvalPoints.getNegativeFeedbackCount();
					System.out.println("Neutral AP after the method call: " + negativeAPaftr);	
					approvalPoints.setNegativeFeedbackCount(negativeAPaftr);
					}
				
				
				if (FeedbackDAO.updateUsersAP(approvalPoints, transactionPartner, feedbackValue)) {
					System.out.println("User's APs updated succesfully.");
					RequestDispatcher rd = request.getRequestDispatcher("/thankyou.html");
					rd.forward(request, response);
				}
			} catch (Exception e) {
				System.out.println("An exception has occured: " + e);
				RequestDispatcher rd = request.getRequestDispatcher("/error.html");
				rd.forward(request, response);
			}
		}
		else if (action.equals("seeFeedback")) {
			System.out.println("This is the seeFeedback if block!");
			session.setAttribute("username", username);
			session.setAttribute("listingID", transactionIDString);
			session.setAttribute("offererUsername", offererUsername);
			session.setAttribute("bidderUsername", bidderUname);
			
			try {
				int listingID = Integer.parseInt(transactionIDString);
				
				List<Feedback> feedbacks = FeedbackDAO.getFeedbackData(listingID);
				
				for (Feedback f : feedbacks) {
					System.out.println("These are the memory locations of the Listing objects: " + f);
					}

				session.setAttribute("feedbacks", feedbacks);
			
				RequestDispatcher rd = request.getRequestDispatcher("/seeFeedback.jsp");
				rd.forward(request, response);
				
			}catch(Exception e) {
				System.out.println("An exception was thrown");
				RequestDispatcher rd = request.getRequestDispatcher("/error.html");
				rd.forward(request, response);
		}
		/*try {
			
			if (feedbackValue == null) {
				request.setAttribute("listingID", transactionIDString);
				request.setAttribute("offererUsername", offererUsername);
				request.setAttribute("bidderUsername", bidderUname);
				
				RequestDispatcher rd = request.getRequestDispatcher("/feedbackForm.jsp");
				rd.forward(request, response);	
			} else {
				String feedbackType = "";
				if (username.equals(offererUsername)) {
					System.out.println("We're dealing with a offerer to bidder feedback operation.");
					feedbackType = "OfToBdFeedback";
				} else {
					System.out.println("We're dealing with a bidder to offerer feedback operation.");
					feedbackType = "BdToOfFeedback";
				}
				if (feedbackComment == null) {
					feedbackComment = "";
					System.out.println("The feedback comment is now: " + feedbackComment);
				}
				
				int listingID = Integer.parseInt(transactionIDString);
				System.out.println("The listing ID as an integer: " + listingID);
				if (FeedbackDAO.updateFeedbackTable(listingID, feedbackType, feedbackValue, feedbackComment)) {
					System.out.println("Data inserted into table succesfully.");
				}
			}
			
		}catch (Exception e) {
			System.out.println("An exception has occured: " + e);
			RequestDispatcher rd = request.getRequestDispatcher("/error.html");
			rd.forward(request, response);
		}*/
		
	}
	}
}





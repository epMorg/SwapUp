package ct869.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import model class
import ct869.project.model.*;
/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "Login Controller", urlPatterns = { "/LoginController" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//extract data from the login form
		String username = request.getParameter("userName");
		System.out.println("This is your LoginServlet speaking.");
		System.out.println("The entered username is: " + username);
		String password = request.getParameter("userPassword");
		System.out.println("The entered password is: " + password);
		//RequestDispatcher rd = null;
		
		
		try {
			User user = new User();
			user.setUserName(username);
			user.setUserPassword(password);
			//user object below has assigned values for all attributes, retrieved from db
			user = DatabaseConnector.userLogin(user);
			
			
			if (user.isValid()) {
				System.out.println("This is the LoginServlet again.");
				
				//session.setAttribute("currentSessionUser", user);
				//User currentUser = (User)session.getAttribute("currentSessionUser");
				//request.setAttribute("currentSessionUser", currentUser);
				
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				System.out.println(session.getAttribute("currentSessionUser"));
				
				session.setAttribute("username", username);
				//request.setAttribute("username", username);
				System.out.println("Session username: " + session.getAttribute("username"));
				System.out.println("Username: " + username);
				
				int studentID = user.getUserStudentID();
				session.setAttribute("studentID", studentID);
				//request.setAttribute("studentID", studentID);
				System.out.println("Session studentID: " + session.getAttribute("studentID")); 
				System.out.println("Student ID: " + studentID);
				
				String email = user.getUserEmail();
				session.setAttribute("email", email);
				//request.setAttribute("email", email);
				System.out.println("Session email: " + session.getAttribute("email"));
				System.out.println("Email: " + email);
				
				String gender = user.getUserGender();
				session.setAttribute("gender", gender);
				//request.setAttribute("gender", gender);
				System.out.println("Session gender: " + session.getAttribute("gender"));
				System.out.println("Gender: " + gender);
				
				String profileBlurb = user.getProfileBlurb();
				session.setAttribute("profileBlurb", profileBlurb);
				//request.setAttribute("profileBlurb", profileBlurb);
				System.out.println("Session profile blurb: " + session.getAttribute("profileBlurb"));
				System.out.println("Profile Blurb: " + profileBlurb);
				
				int approvalPoints = user.getApprovalPoints();
				session.setAttribute("approvalPoints", approvalPoints);
				//request.setAttribute("approvalPoints", approvalPoints);
				System.out.println("Session approval points: " + session.getAttribute("approvalPoints"));
				System.out.println("Approval Points: " + approvalPoints);
				
				int positiveAP = user.getPositiveAP();
				session.setAttribute("positiveAP", positiveAP);
				int negativeAP = user.getNegativeAP();
				session.setAttribute("negativeAP", negativeAP);
				int neutralAP = user.getNeutralAP();
				session.setAttribute("neutralAP", neutralAP);
				
				String socialRole = user.getSocialRole();
				session.setAttribute("socialRole", socialRole);
				//request.setAttribute("socialRole", socialRole);
				System.out.println("Session social role: " + session.getAttribute("socialRole"));
				System.out.println("Social Role: " + socialRole);
				
				ArrayList <Listing> myListings = new ArrayList<Listing>();

				Listing listing = new Listing();
				listing.setOffererUsername(username);
				System.out.println("The username that has just logged in is: " + username);
				try {
					myListings = SessionListingsDAO.getListings(listing);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				session.setAttribute("myListings",myListings);
				
				session.setMaxInactiveInterval(30*60);
				response.sendRedirect("profilePage.jsp");
			}
			
			else {
				response.sendRedirect("loginFailure.html");
			}
		}
			catch (Throwable e) {
				System.out.println(e);
			}
		try {
			HttpSession session = request.getSession(false);
			username = (String) session.getAttribute("username");
				if (username == null) {
					request.getRequestDispatcher("sessionExpired.html").forward(request, response);
			} else {
				System.out.println("Session not expired");
				
			}
			
		}
			catch (Throwable e) {
				System.out.println(e);
			}
		
		}
			
	}	



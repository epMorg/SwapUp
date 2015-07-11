package ct869.project.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ct869.project.model.UserProfileConnector;

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet(description = "Controls users' edits of their profiles", urlPatterns = { "/EditProfileController" })
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileController() {
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
		
		String profileBlurb = request.getParameter("aboutMe");
		System.out.println(profileBlurb);
		
		RequestDispatcher rd = null;
		HttpSession session = request.getSession(true);
		//User user = (User)(session.getAttribute("currentSessionUser"));
		//session.setAttribute("currentSessionUser", user);
		String username = (String)session.getAttribute("username");
		
		
		//user.setProfileBlurb(profileBlurb);
		
		System.out.println("Extracting user " + username + "\n profile blurb: " + profileBlurb);
		
		try {
			if (UserProfileConnector.editProfile(username, profileBlurb)) {
				session.getAttribute("profileBlurb");
				session.setAttribute("profileBlurb", profileBlurb);
				System.out.println("This is the profile blurb: " + profileBlurb);
				rd = request.getRequestDispatcher("/profilePage.jsp");
			}else {
				System.out.println("Could not connect to database");
				rd = request.getRequestDispatcher("/error.html");
				}
		}
		catch (Exception e) {
			System.out.println("An exception occured: " + e);
			rd = request.getRequestDispatcher("/error.html");
		}
		rd.forward(request, response);
	}
	
		
	}



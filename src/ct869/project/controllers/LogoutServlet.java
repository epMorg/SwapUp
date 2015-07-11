package ct869.project.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ct869.project.model.*;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet(description = "Logout Servlet", urlPatterns = { "/LogoutServlet" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//session.setAttribute("currentSessionUser", null);
		try {
			
			HttpSession session = request.getSession(true);
			User user = (User)(session.getAttribute("currentSessionUser"));
			session.setAttribute("currentSessionUser", user);
			System.out.println("Current user object: " + session.getAttribute("currentSessionUser"));
			System.out.println("Username of current session user object: " + session.getAttribute("username"));
			System.out.println("This is the LogOutServlet");
			System.out.println("Session object before the execution of if statement:" + session);
			System.out.println("currentSessionUser");
			
			if (session != null) {
				
				session.invalidate();
				System.out.println("Session object aftr execution of if statement: ");
				
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Cache-Control", "no-store");
				response.setDateHeader("Expires", 0);
				response.setHeader("Pragma", "no-cache");
				response.sendRedirect("login.html");
			}
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					cookie.setValue("-");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		catch (Exception e) {
			System.out.println("An exception occured " + e);
			response.sendRedirect("error.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package ct869.project.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ct869.project.model.Listing;
import ct869.project.model.SessionListingsDAO;

/**
 * Servlet implementation class SessionListingsController
 */
@WebServlet("/SessionListingsController")
public class SessionListingsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionListingsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		

			ArrayList <Listing> myListings = new ArrayList<Listing>();

			Listing listing = new Listing();
			String username = (String) session.getAttribute("username");
			listing.setOffererUsername(username);

			try {
				myListings = SessionListingsDAO.getListings(listing);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("myListings",myListings);
			
			
		}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

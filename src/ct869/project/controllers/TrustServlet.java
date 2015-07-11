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

import ct869.project.model.Listing;
import ct869.project.model.Node;
import ct869.project.model.TransactionDataRetriever;
import ct869.project.model.TrustDAO;
import ct869.project.model.User;

/**
 * Servlet implementation class TrustServlet
 */
@WebServlet(description = "Deals with trust-related actions", urlPatterns = { "/TrustServlet" })
public class TrustServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrustServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("The requested action is: " + action);
		String bartererUname = request.getParameter("barterername");
		System.out.println("The username of the member whose profile page will be viewed: " + bartererUname);
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
		String message = "";
		String noTransactions = "";
		
		
		if (action.equals("Enter")) {
			System.out.println("The user wants to see their Web of Trust");
			session.setAttribute("bartererUname", bartererUname);
			
			if (!TrustDAO.checkWebOfTrust(username)) {
				System.out.println("User " + username + " has not yet established any trust bonds with other users.");
				message = "You have not yet established any bonds of trust with other users.";
				session.setAttribute("message", message);
				
			} else {
				System.out.println("User " + username + " has established trust bonds with other users.");
				message = "You've established a Bond of Trust with the following users: ";
				session.setAttribute("message", message);
				List<Node> nodes = TrustDAO.getTrustees(username);
				session.setAttribute("nodes", nodes);
				for (Node node : nodes) {
					System.out.println("These are the nodes retrieved: " + node.getConnectTo());
				}
			}
			if (!TrustDAO.checkActivity(username)) {
				noTransactions = "You have not yet participated in any transactions.";
				session.setAttribute("noTransactions", noTransactions);
			} else {
				List<User> users = TrustDAO.getBarteres(username);
				session.setAttribute("users", users);
				for (User user : users) {
					System.out.println("The usernames retrieved: " + user.getUserName());
				}
			}
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/webOfTrust.jsp");
			rd.forward(request, response);
			
		}
		else if (action.equals("seeBartererPage")) {
			System.out.println("This is the nextAction method");
			
			
			User user = new User();
			user = TrustDAO.getBartererInfo(bartererUname);
			List<Node> vertices = new ArrayList<Node>();
			vertices = TrustDAO.getVertices(bartererUname);
			List<Node> connectedTos = new ArrayList<Node>();
			connectedTos = TrustDAO.getConnectedTos(bartererUname);
			
			request.setAttribute("user", user);
			request.setAttribute("vertices", vertices);
			request.setAttribute("connectedTos", connectedTos);
			session.setAttribute("bartererUname", bartererUname);
			
			RequestDispatcher rd = request.getRequestDispatcher("/bartererInfo.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("The requested action is: " + action);
		String bartererUname = request.getParameter("bartererName");
		System.out.println("The username of the mamber whose profile page will be viewed: " + bartererUname);
		String username = request.getParameter("username");
		System.out.println("The name of the user who wants to establish the bond of trust: " + username);
		
		if (action.equals("establishABond")) {
			System.out.println("The user wants to establish a bond of trust with another user!");
			
			Node vertex = new Node();
			vertex.setVertex(username);
			String vrtx = vertex.getVertex();
			System.out.println("Vertex node is: " + vrtx);
			
			Node connectTo = new Node();
			connectTo.setConnectTo(bartererUname);
			
			String createEdge = username + " " + bartererUname;			
			Node edge = new Node();
			edge.setEdges(createEdge);
			String edg = edge.getEdges();
			System.out.println("The edge string looks like this: " + edg);
			
			if (TrustDAO.insertVerticesAndEdges(vertex, connectTo, edge)) {
				request.setAttribute("connectTo", connectTo);
				RequestDispatcher rd = request.getRequestDispatcher("/bondOfTrustSuccess.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/error.html");
				rd.forward(request, response);
			}
		} else if (action.equals("severABond")) {
			System.out.println("This is the bond-severing action.");
			
			Node vertex = new Node();
			vertex.setVertex(username);
			String vrtx = vertex.getVertex();
			System.out.println("Vertex node is: " + vrtx);
			
			Node connectTo = new Node();
			connectTo.setConnectTo(bartererUname);
			
			String createEdge = username + " " + bartererUname;			
			Node edge = new Node();
			edge.setEdges(createEdge);
			String edg = edge.getEdges();
			System.out.println("The edge string looks like this: " + edg);
			
			if (TrustDAO.severBondOfTrust(vertex, connectTo, edge)) {
				System.out.println("Bond of Trust severed succesfully.");
				request.setAttribute("connectTo", connectTo);
				RequestDispatcher rd = request.getRequestDispatcher("/severingOfTrustSuccess.jsp");
				rd.forward(request, response);
				
			} else {
				System.out.println("Bond of Trust could not be severed.");
				RequestDispatcher rd = request.getRequestDispatcher("/severingOfTrustUnsuccesful.html");
				rd.forward(request, response);
			}
		}
	}

}

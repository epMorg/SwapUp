package ct869.project.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ct869.project.model.PhotoInserter;

/**
 * Servlet implementation class PhotoServlet
 */
@WebServlet(description = "Forwards the photos retrieved from the database to the jsp pages", urlPatterns = { "/PhotoServlet" })
@MultipartConfig(fileSizeThreshold=1024*1024*10,  //10MB
maxFileSize=1024*1024*50,		  //50 MB
maxRequestSize=1024*1024*100)	  //100 MB

public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 205242440643911308L;
	private static final int BUFFER_SIZE = 4096;  
	private String photoHolder;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.getParameter("getPhoto");
		System.out.println("This is the PhotoServlet");
		String action = request.getParameter("action");
		String name = request.getParameter("bartererName");
		System.out.println("Action gotten by the Photo servlet is: " + action);
		System.out.println("Username gotten by the Photo servlet is: " + name);
		if (action.equals("profilePage")) {
			HttpSession session = request.getSession();
			photoHolder = (String)session.getAttribute("username");
			System.out.println("The name of the user is: " + photoHolder);
		} else if (action.equals("bartererInfo")) {
			photoHolder = name;
			System.out.println("The name of the barterer is: " + photoHolder);
		}
		if (PhotoInserter.checkPhoto(photoHolder)) {
			try {
				InputStream photoStream = PhotoInserter.getPhoto(photoHolder);
				//prepare streams
				BufferedInputStream input = null;
				BufferedOutputStream output = null;
					try {
						//open streams
						input = new BufferedInputStream(photoStream, BUFFER_SIZE);
						response.setContentType("image/jpeg");
						output = new BufferedOutputStream(response.getOutputStream(), BUFFER_SIZE);
						//write file contents to response
						byte[] buffer = new byte[BUFFER_SIZE];
						int length;
						while ((length = input.read(buffer)) > 0) {
							output.write(buffer, 0, length);
					}
				} finally {
					//output.close();
					//input.close();
			}
			//return;
			//redirect to profile page
			//RequestDispatcher rd = request.getRequestDispatcher("/profilePage.jsp");
			//rd.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}
		} else {
			System.out.println("The user has not yet uploaded a photo.");
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		/*request.getParameter("getPhoto");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		
		try {
			InputStream photoStream = PhotoInserter.getPhoto(username);
			//prepare streams
			BufferedInputStream input = null;
			BufferedOutputStream output = null;
				try {
				//open streams
				input = new BufferedInputStream(photoStream, BUFFER_SIZE);
				response.setContentType("image/jpeg");
				output = new BufferedOutputStream(response.getOutputStream(), BUFFER_SIZE);
				//write file contents to response
				byte[] buffer = new byte[BUFFER_SIZE];
				int length;
				while ((length = input.read(buffer)) > 0) {
					output.write(buffer, 0, length);
					}
				} finally {
					output.close();
					input.close();
			}
			//redirect to profile page
			RequestDispatcher rd = request.getRequestDispatcher("/profilePage.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}
}



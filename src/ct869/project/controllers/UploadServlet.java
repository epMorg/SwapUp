package ct869.project.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import ct869.project.model.PhotoInserter;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(description = "Handles photo uploads", urlPatterns = { "/UploadServlet" })
@MultipartConfig(fileSizeThreshold=1024*1024*10,  //10MB
				 maxFileSize=1024*1024*50,		  //50 MB
				 maxRequestSize=1024*1024*100)	  //100 MB
		
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 205242440643911308L;
    
	public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = null;
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
     
		InputStream inputStream = null; // input stream of the upload file
     
    // obtains the upload file part in this multipart request
		Part filePart = request.getPart("profilePhoto");
			if (filePart != null) {
        // prints out some information for debugging
				System.out.println("The name of the file:" + filePart.getName());
				System.out.println("The size of the file (in Bytes): " + filePart.getSize());
				System.out.println("The type of file:" + filePart.getContentType());
         
        // obtains input stream of the upload file
				inputStream = filePart.getInputStream();
				System.out.println("File uploaded succesfully!");
			}
			if (PhotoInserter.insertPhoto(username, inputStream)) {
				System.out.println("This is the servlet speaking - The image has been inserted into the database succesfully");
				session.setAttribute("profilePhoto", inputStream);
				rd = request.getRequestDispatcher("/profilePage.jsp");
				rd.forward(request, response);
				/*String message = "Your photo has been uploaded succesfully!";
				request.setAttribute("message", message);
				rd = request.getRequestDispatcher("/uploadSuccess.jsp");
				rd.forward(request, response);*/
				}
			
		}
		
    /**
	 * Method used to get uploaded file name.
	 * This will parse following string and get filename 
	 * Content-Disposition: form-data; name="content"; filename="a.txt"
	 **/
	public String getUploadedFileName(Part filePart) {
		String file = "", header = "Content-Disposition";
		String[] strArray = filePart.getHeader(header).split(";");
		for(String split : strArray) {
			if(split.trim().startsWith("filename")) {
				file = split.substring(split.indexOf('=') + 1);
				file = file.trim().replace("\"", "");
				System.out.println("File name : "+ file);
			}
		}
		return file;
	}
	
	
	
	/*Directory where uploaded files will be saved, relative to the web application directory
	private static final String UPLOAD_DIR = "uploads";
    
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//gets the absolute path of the web application
		String applicationPath = request.getServletContext().getRealPath("");
		//constructs the path of the directory where the uploaded file is saved
		String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
		
		//creates the save directory if it does not exist
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		System.out.println("Upload File Directory = " + fileSaveDir.getAbsolutePath());
		
		String fileName = null;
		//Get all the parts from request and write it to the file on server
		for (Part part: request.getParts()) {
			fileName = getFileName(part);
			part.write(uploadFilePath + File.separator + fileName);
		}
		
		request.setAttribute("message", fileName + "File uploaded succesfully.");
		getServletContext().getRequestDispatcher("/uploadSuccess.jsp").forward(request, response);
	}
		//an utility method that gets the file name from the HTTP header's content-disposition
		private String getFileName(Part part) {
			String contentDisp = part.getHeader("content-disposition");
			System.out.println("content-disposition header= " + contentDisp);
			String[] tokens = contentDisp.split(";");
			for (String token: tokens) {
				if (token.trim().startsWith("profilePhoto")) {
					return token.substring(token.indexOf("=") + 2, token.length()-1);
				}
			}
			return "";
		}*/
	}




package ct869.project.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ct869.project.model.*;

/**
 * Servlet implementation class DuplicateUsernamesChecker
 */
@WebServlet(description = "Cheks for duplicate usernames", urlPatterns = { "/checkduplicate" })
public class DuplicateUsernamesChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DuplicateUsernamesChecker() {
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
		RequestDispatcher rd = null;
		try {
			RegistrationForm regForm = (RegistrationForm)request.getAttribute("regForm");
			String username = regForm.getUserName();
			
			boolean usernameTaken = DatabaseConnector.usernameTaken(username);
			if (usernameTaken == false) {
				System.out.println("Username not taken!");
				
				String userName = regForm.getUserName();
				request.setAttribute("userName", userName);
				String password = regForm.getFirstPassword();
				String userEmail = regForm.getUserEmail();
				request.setAttribute("userEmail", userEmail);
				String userGender = regForm.getUserGender();
				request.setAttribute("userGender", userGender);
				String userStudentIDString = regForm.getStudentID();
				int userStudentID = Integer.parseInt(userStudentIDString);
				request.setAttribute("userStudentID", userStudentID);
				
				
				//create an user object
				User user = new User();
				//set input values
				user.setUserName(userName);
				user.setUserEmail(userEmail);
				user.setUserPassword(password);
				user.setUserStudentID(userStudentID);
				user.setUserGender(userGender);
				//user.setDefaultSocialRole(gender);
				//store user
				request.setAttribute("user", user);
				//call the userRegistration method on the user object and assign 
				//the (boolean) result to a variable
				boolean isRegSuccesful = DatabaseConnector.userRegistration(user);
				//see if the registration was a success - this is a nested if statement
				if (isRegSuccesful) {
					System.out.println("Servlet calling userRegistration method. Registration was a success!");
					//dispatch request to the 'success.jsp' page
					
					rd = request.getRequestDispatcher("/loginSuccess.jsp");
				} else {
					System.out.println("Registration failed.");
					//else dispatch request to the generic error page
					rd = request.getRequestDispatcher("/error.html");
				}
			} if (usernameTaken == true) {
				regForm.setErrors("userName", "Duplicate user. Please select another username");
				String userName = regForm.getUserName();
				request.setAttribute("userName", userName);
				String userNameErrMsg = regForm.getErrorMsg("userName");
				request.setAttribute("userNameErrMsg", userNameErrMsg);
				//console check
				System.out.println(userNameErrMsg);
				
				//get value input into 'email' field by the user and save it
				String userEmail = regForm.getUserEmail();
				request.setAttribute("userEmail", userEmail);
				//get error message associated with 'email' field and save it
				String userEmailErrMsg = regForm.getErrorMsg("userEmail");
				request.setAttribute("userEmailErrMsg", userEmailErrMsg);
				//console check
				System.out.println(userEmailErrMsg);
				
				//get value input into the 'password1' field by user and save it
				String userPassword1 = regForm.getFirstPassword();
				request.setAttribute("userPassword1", userPassword1);
				//get error message associated with this field and save it
				String userPassword1ErrMsg = regForm.getErrorMsg("userPassword1");
				request.setAttribute("userPassword1ErrMsg", userPassword1ErrMsg);
				//console check
				System.out.println(userPassword1ErrMsg);
				
				//'password2' field
				String userPassword2 = regForm.getSecondPassword();
				request.setAttribute("userPassword2", userPassword2);
				String userPassword2ErrMsg = regForm.getErrorMsg("userPassword2");
				request.setAttribute("userPassword2ErrMsg", userPassword2ErrMsg);
				//console check
				System.out.println(userPassword2ErrMsg);
				
				//'studentID' field
				String userStudentIDString = regForm.getStudentID();
				int userStudentID = Integer.parseInt(userStudentIDString);
				request.setAttribute("userStudentID",userStudentID);
				String studentIDErrMsg = regForm.getErrorMsg("userStudentID");
				request.setAttribute("studentIDErrMsg", studentIDErrMsg);
				//console check
				System.out.print(studentIDErrMsg);
				
				//'gender' radio button
				String userGender = regForm.getUserGender();
				request.setAttribute("userGender", userGender);
				String userGenderErrMsg = regForm.getErrorMsg("userGender");
				request.setAttribute("userGenderErrMsg", userGenderErrMsg);
				
				rd = request.getRequestDispatcher("/retry.jsp");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.print(e);
		}
			}
			

	}



package ct869.project.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import model beanz
import ct869.project.model.RegistrationForm;
//import ct869.project.model.User;
//import ct869.project.model.DatabaseConnector;

/**
 * Servlet implementation class RegistrationController
 */
@WebServlet(description = "Registration Controller", urlPatterns = { "/RegistrationController" })
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationController() {
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
		//get variable values from the HTML form
		String name = request.getParameter("userName");
		String email = request.getParameter("userEmail");
		String password1 = request.getParameter("userPassword1");
		String password2 = request.getParameter("userPassword2");
		String gender = request.getParameter("chooseGender");
		String studentID = request.getParameter("userStudentID");
		//print to test if servlet call was a success
		String space = " ";
		System.out.println(name + space + email + space + password1 + space + password2 + space + gender + space + studentID);
		System.out.println("all variables extracted from form");
		
		RequestDispatcher rd = null;
		
		RegistrationForm regForm = new RegistrationForm();
		regForm.setUserName(name);
		regForm.setUserEmail(email);
		regForm.setFirstPassword(password1);
		regForm.setSecondPassword(password2);
		regForm.setUserGender(gender);
		regForm.setStudentID(studentID);
		//store the registration form object for further use
		//in the DuplicateUsernameChecker servlet
		request.setAttribute("regForm", regForm);
		//regForm.setChooseGender(gender);
		System.out.println("Variable values have been set");
		
		try {
		//call to the isRegFormValid method	
		regForm.isRegFormValid();
		if (regForm.isRegFormValid()) {
			System.out.println("Form is valid!");
			rd = request.getRequestDispatcher("/checkduplicate");
			rd.forward(request, response);
			//get valid data with the use of the RegistrationForm object
			//and store the attributes of this particular request
			/*String userName = regForm.getUserName();
			request.setAttribute("userName", userName);
			String userEmail = regForm.getUserEmail();
			request.setAttribute("userEmail", userEmail);
			String userGender = regForm.getUserGender();
			request.setAttribute("userGender", userGender);
			String userStudentID = regForm.getStudentID();
			request.setAttribute("userStudentID", userStudentID);
			
			
			//create an user object
			User user = new User();
			//set input values
			user.setUserName(name);
			user.setUserEmail(email);
			user.setUserPassword(password1);
			user.setUserStudentID(studentID);
			user.setUserGender(gender);
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
				rd = request.getRequestDispatcher("/success.jsp");
			} else {
				System.out.println("Registration failed.");
				//else dispatch request to the generic error page
				rd = request.getRequestDispatcher("/error.html");
			}*/
		}else {
			System.out.println("Form not valid!");
			//get value input into 'username' field by the user and save it
			String userName = regForm.getUserName();
			request.setAttribute("userName", userName);
			//get error message associated with 'username' field
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
			String userStudentID = regForm.getStudentID();
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
			
			//which radio button was checked
			//String chooseGender = regForm.getChooseGender();
			//request.setAttribute("chooseGender", chooseGender);
			//String isRadioSelected = regForm.isRadioSelected(regForm.getChooseGender());
			//request.setAttribute("isRadioSelected", isRadioSelected);
			//System.out.print(isRadioSelected);
			
			//dispatch client request to 'retry.jsp'
			rd = request.getRequestDispatcher("/retry.jsp");
		}
		rd.forward(request, response);
	} catch (Exception e) {
		System.out.print(e);
	}
	}
}


package com.eVoting.signup;

import java.io.IOException;



import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String email = request.getParameter("email");
		String e_otp = request.getParameter("e_otp");
		
		User user = new User();
		user.setUserid(uname);
		
		String mailcode = user.getOtp();
		
		/*Check authentication details - OTP*/
		if (!mailcode.equals(e_otp))
		{
			response.sendRedirect("login.jsp?msg=Incorrect OTP value entered. Login and authenticate again");
		}
		else
		{
			/*Signed up successfully - Store the data in the database*/
			//User user = new User();
			user.setEmail(email);
			user.addUserEmail();

			response.sendRedirect("successfulsignup.jsp");
		}
	}

}

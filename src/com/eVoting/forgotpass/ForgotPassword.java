package com.eVoting.forgotpass;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eVoting.signup.User;


@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String conpass = request.getParameter("conpass");
		
		User user = new User();
		user.setUserid(uname);
		
		if (!pass.equals(conpass)) {
				response.sendRedirect("login.jsp?msg=Password does not match.");
		}
		else {
			user.changePassword(pass);
			response.sendRedirect("changepass.jsp");
		}
	}
}

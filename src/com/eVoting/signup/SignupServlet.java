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
		String pass = request.getParameter("pass");
		String conpass = request.getParameter("conpass");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String city = request.getParameter("City");
		String country = request.getParameter("country");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String e_otp = request.getParameter("e_otp");
		String m_otp = request.getParameter("m_otp");
		
		PrintWriter out = response.getWriter();
		
		/*Check username is unique*/
		
		/*Check pass and conpass are same*/
		if (!pass.equals(conpass))
		{
			out.println("The passwords do not match");
		}
		
		out.println("Uname : "+uname);
		out.println("Password : "+pass);
		out.println("Name : "+fname+lname);
		out.println("DOB : "+dob);
		out.println("City : "+city);
		out.println("Country : "+country);
		out.println("Email : "+email);
		out.println("Mobile No : "+phone);
		out.println("Email OTP : "+e_otp);
		out.println("Mobile OTP : "+m_otp);
		
		/*Check authentication details - OTP*/
		
		/*Signed up successfully - Store the data in the database*/
	}

}

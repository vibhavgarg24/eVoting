package com.eVoting.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		String nextPage = "";
		
//		if(uname.equals("root") && pass.equals("pass")) {
		
		Login login = new Login(); 
		boolean loginCheck = false;
		boolean status=false;
		
		try {
			
			if (uname == null || pass == null || uname.equals("") || pass.equals("")) {
				nextPage = "login.jsp?msg=Username or Password cannot be Empty";
			} else {	
				loginCheck = login.checkCreds_userid(uname, pass);
				
				if ( loginCheck ) {
					
					status = login.checkAuthStatus(uname);
					if (status){
						HttpSession session = request.getSession();
						session.setAttribute("uname", uname);	
						nextPage = "homepage.jsp";
					}
					else {
						nextPage="login.jsp";
					}
					
				} else {
					
					loginCheck = login.checkCreds_email(uname, pass);
					if ( loginCheck ) {
						HttpSession session = request.getSession();
						session.setAttribute("uname", uname);
						
						nextPage = "homepage.jsp";
						
					}
					else {
						nextPage = "login.jsp?msg=Invalid Credentials";
					}
				}
			}
			
		} catch (Exception e) {
			nextPage = "login.jsp?msg=Login Error";
			e.printStackTrace();
		} 
		
		response.sendRedirect(nextPage);
		
		
	}

}
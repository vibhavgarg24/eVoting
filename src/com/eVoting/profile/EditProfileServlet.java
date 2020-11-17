package com.eVoting.profile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eVoting.profile.UserDetails.userinfo;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String uname = session.getAttribute("uname").toString();
		
		if (session.getAttribute("uname") == null) {
			response.sendRedirect("login.jsp");
		}
		else {
			UserDetails ud = new UserDetails();
			userinfo profile = ud.new userinfo();
			profile.setUname(uname);
			profile.save_from_database();
			request.setAttribute("profile", profile);
			
			request.getRequestDispatcher("editprofile.jsp").forward(request, response);
		}
		
	}

}

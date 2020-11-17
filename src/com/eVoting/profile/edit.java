package com.eVoting.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class edit extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String mobile_no = request.getParameter("mobile_no");
		String email = request.getParameter("email");
		
		String url = "jdbc:mysql://localhost:3306/users";
		String username = "root";
		String password = "LoveEveryone";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "update user_details set First_name=? ,Last_name=? "+
					", Mobile_no=? ,Date_of_Birth=? ,City=? ,Country=?";
			
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, fname);
			st.setString(2, lname);
			st.setString(3, mobile_no);
			st.setString(4, dob);
			st.setString(5, city);
			st.setString(6, country);
			int count = st.executeUpdate();
			
			st.close();
			con.close();
			
			response.sendRedirect("UserDetails");
			}
		catch (Exception ex) {
			System.out.println(ex);
		}
	}

}

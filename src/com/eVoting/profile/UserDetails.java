package com.eVoting.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/UserDetails")
public class UserDetails extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("uname") == null) {
			response.sendRedirect("login.jsp");
		}
		else {
			String uname = session.getAttribute("uname").toString();
			userinfo profile = new userinfo();
			profile.setUname(uname);
			profile.save_from_database();
			request.setAttribute("profile", profile);
			
			request.getRequestDispatcher("myprofile.jsp").forward(request, response);
		}
		
	}

	public class userinfo {
		String fname;
		String lname;
		String mobile_no;
		String dob;
		String city;
		String country;
		String uname;
		String email;
		
		public String getFname() {
			return fname;
		}
		public void setFname(String fname) {
			this.fname = fname;
		}
		public String getLname() {
			return lname;
		}
		public void setLname(String lname) {
			this.lname = lname;
		}
		public String getMobile_no() {
			return mobile_no;
		}
		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getUname() {
			return uname;
		}
		public void setUname(String uname) {
			this.uname = uname;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public void save_from_database() {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,username,password);
				
				String query = "select * from user_details where userid=\""+this.uname+"\";";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				if (rs.next()) {
					this.fname = rs.getString(2);
					this.lname = rs.getString(3);
					this.mobile_no = rs.getString(4);
					this.dob = rs.getString(5);
					this.city = rs.getString(6);
					this.country = rs.getString(7);
				}
				else {
					this.fname = "";
					this.lname = "";
					this.mobile_no = "";
					this.dob = "";
					this.city = "";
					this.country = "";
				}
				db_email();
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
			
		}
		
		public void db_email() {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,username,password);
				
				String query = "select * from login where userid=\""+this.uname+"\"";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				
				if (rs.next()) {
					this.email = rs.getString(3);
				}
				else {
					this.email = "";
				}
			}
			catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}
}

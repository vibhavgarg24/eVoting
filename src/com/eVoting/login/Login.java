package com.eVoting.login;

import java.sql.*;

public class Login {
	
//	public static void main (String[] args) throws Exception {
//		System.out.println(checkCreds ("roots", "passwo") );
//	}
	
	public boolean checkCreds_userid (String uname, String pass) throws Exception  {
		
//		if (uname == null || pass == null || uname.equals("") || pass.equals("")) {
//			return false;
//		} 
		
		String url = "jdbc:mysql://localhost:3306/users";
//		String query = "select * from login where uname = '" + uname + "' and password = '" + pass + "'";
		String query = "select * from login where userid = ? and password = ?";

//		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,"root","LoveEveryone");

		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, uname);
		st.setString(2, pass);
		
//		System.out.println(st.toString());
//		return true;
		
		ResultSet rs = st.executeQuery();
//		int count = st.executeUpdate();

		if (rs.next()) {
			return true;
		}
		
//		rs.next();
//		String username = rs.getString(1);
//		String password = rs.getString(2);
//		
//		rs.next();
//		if (username.equals(uname) && password.equals(pass)) {
//			return true;
//		}
		
		st.close();
		con.close();
				
		return false;

	}

	public boolean checkCreds_email (String email, String pass) throws Exception  {
		
		String url = "jdbc:mysql://localhost:3306/users";
		
		String query = "select * from login where email = ? and password = ?";

//		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,"root","LoveEveryone");
		
		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, email);
		st.setString(2, pass);
		
		ResultSet rs = st.executeQuery();
		
		if (rs.next()) {
			return true;
		}
		
		return false;
	}
	
	public boolean checkAuthStatus(String uname) {
		try {
			boolean test;
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select auth_status from login where userid=\""+uname+"\";";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				int status = rs.getInt("auth_status");
				
				if (status==0)
					test=false;
				else
					test=true;
			}
			else {
				test = false;
			}
			
			st.close();
			con.close();
			
			return test;
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		return false;
	}
}

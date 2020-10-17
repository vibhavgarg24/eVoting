package com.eVoting.login;

import java.sql.*;

public class Login {
	
//	public static void main (String[] args) throws Exception {
//		System.out.println(checkCreds ("roots", "passwo") );
//	}
	
	public boolean checkCreds (String uname, String pass) throws Exception  {
		
//		if (uname == null || pass == null || uname.equals("") || pass.equals("")) {
//			return false;
//		} 
		
		String url = "jdbc:mysql://localhost:3306/demo";
//		String query = "select * from login where uname = '" + uname + "' and password = '" + pass + "'";
		String query = "select * from login where uname = ? and password = ?";

//		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,"root","SQLpass");

		PreparedStatement st = con.prepareStatement(query);
		st.setString(1, uname);
		st.setString(2, pass);
		
//		System.out.println(st.toString());
//		return true;
		
		ResultSet rs = st.executeQuery();

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

}

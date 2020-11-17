package com.eVoting.signup;

import java.sql.*;

public class User {
	
	String userid;
	String password;
	String fname;
	String lname;
	String email;
	String mobile;
	String dob;
	String city;
	String country;
	String e_code;
	int auth_status; /*1 for authentication done and 0 for not done*/

	public User()
	{}
	
	public User(String userid, String password, String fname, String lname, 
			String email, String mobile, String dob, String city, String country,int auth_status)
	{
		this.userid = userid;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
		this.dob = dob;
		this.city = city;
		this.country = country;
		this.auth_status=auth_status;
		//this.e_code = e_code;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getE_code() {
		return e_code;
	}

	public void setE_code(String e_code) {
		this.e_code = e_code;
	}
	
	public int getAuth_status() {
		return auth_status;
	}

	public void setAuth_status(int auth_status) {
		this.auth_status = auth_status;
	}
	
	public boolean alreadyExists(){
		try {
			boolean test=false;
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select userid from login where userid=\""+this.userid+"\";";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
				test=true;
			else
				test=false;
			
			st.close();
			con.close();
			
			return test;
			
		}
		catch (Exception ex){
			System.out.println(ex);
		}
		return false;
	}
	
	public boolean uniqueEmail() {
		try {
			boolean test;
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select Email from login where Email=\""+this.email+"\";";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
				test=false;
			else
				test=true;
			
			st.close();
			con.close();
			
			return test;	
		} 
		catch (Exception ex){
			System.out.println(ex);
		}
		return false;
	}
	

	public void adduser() {
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query1 = "insert into login (userid, password, Email,auth_status) values (?,?,?,?);";
			
			PreparedStatement st1 = con.prepareStatement(query1);
			st1.setString(1,this.userid);
			st1.setString(2,this.password);
			st1.setString(3,this.email);
			st1.setInt(4,this.auth_status);
			
			int count = st1.executeUpdate();
			
			String query2 = "insert into user_details (userid, First_name, Last_name, "
					+ "Mobile_no, Date_of_Birth, City, Country) "
					+ "values (?,?,?,?,?,?,?);";
			
			PreparedStatement st2 = con.prepareStatement(query2);
			st2.setString(1,this.userid); 
			st2.setString(2,this.fname);
			st2.setString(3,this.lname);
			st2.setString(4,this.mobile);
			st2.setString(5,this.dob);
			st2.setString(6,this.city);
			st2.setString(7,this.country);
			
			count = st2.executeUpdate();
					
			st1.close();
			st2.close();
			con.close();
		} 
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void addUserEmail(){
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "update login set Email=\""+this.email+"\",auth_status=1 where userid=\""+this.userid+"\";";
			PreparedStatement st = con.prepareStatement(query);
			int count = st.executeUpdate();
			
			st.close();
			con.close();		
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public void deleteUser() {
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query1 = "delete from login where userid=\""+this.userid+"\";";
			PreparedStatement st1 = con.prepareStatement(query1);
			int count = st1.executeUpdate();
			
			String query2 = "delete from user_details where userid=\""+this.userid+"\";";
			PreparedStatement st2 = con.prepareStatement(query2);
			count = st2.executeUpdate();
			
			st1.close();
			st2.close();
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public boolean checkAuthStatus(){
		try {
			boolean test;
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select auth_status from login where userid=\""+this.userid+"\";";
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
	
	public void addOtp() {
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "insert into authentication (userid,Otp) values (?,?);";
			
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1,this.userid);
			st.setString(2,this.e_code);
			int count = st.executeUpdate();
			
			st.close();
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public String getOtp() {
		String otp="";
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select Otp from authentication where userid=\""+this.userid+"\";";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				otp = rs.getString("Otp");
			}

			st.close();
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		return otp;
	}
	
	
	public void changePassword(String newPass) {
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "update login set password=\""+newPass+"\" where userid=\""+this.userid+"\";";
			PreparedStatement st = con.prepareStatement(query);
			
			int count = st.executeUpdate();
			
			st.close();
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public String getTrueEmail() {
		String temp=""; 
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select Email from login where userid=\""+this.userid+"\";";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				temp = rs.getString("Email");
			}
			else {
				temp = "Null";
			}
			
			st.close();
			con.close();
			
			return temp;
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		return temp;
	}
	
	public String forgpass_getOtp() {
		String otp="";
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select Otp from forgpass_verification where userid=\""+this.userid+"\";";
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				otp = rs.getString("Otp");
			}
			else {
				otp="";
			}

			st.close();
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
		return otp;
	}
	
	public void forgpass_addOtp() {
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "insert into forgpass_verification (userid,Otp) values (?,?);";
			
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1,this.userid);
			st.setString(2,this.e_code);
			int count = st.executeUpdate();
			
			st.close();
			con.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
}

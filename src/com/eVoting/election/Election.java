package com.eVoting.election;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
 

public class Election {

	String code;
	String title = "";
	String desc = "";
	String unameConducted = "";
	int noOfCandidates;
	String start_date="";
	String end_date="";
	
	ArrayList<String> candidates;
	
	public void addCandidate(String candidate) {
		if (candidates == null) {
			candidates = new ArrayList<String>();
		}
		candidates.add(candidate);
	}
	
	public ArrayList<String> getCandidates() {
		return candidates;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getUnameConducted() {
		return unameConducted;
	}

	public void setUnameConducted(String unameConducted) {
		this.unameConducted = unameConducted;
	}
	
	public int getNoOfCandidates() {
		return noOfCandidates;
	}
	
	public void setNoOfCandidates(int noOfCandidates) {
		this.noOfCandidates = noOfCandidates;
	}
	
	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public void save () throws Exception {
		String url = "jdbc:mysql://localhost:3306/users";
		String queryForElec = "insert into elections values ( ?, ?, ?, ?, ?, ?, ? )";

//		Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,"root","LoveEveryone");

		PreparedStatement psForElec = con.prepareStatement(queryForElec);
		psForElec.setString(1, this.code);
		psForElec.setString(2, this.title);
		psForElec.setString(3, this.desc);
		psForElec.setString(4, this.unameConducted);
		psForElec.setInt(5, this.noOfCandidates);
		psForElec.setString(6, this.start_date);
		psForElec.setString(7, this.end_date);
		
		psForElec.executeUpdate();
		psForElec.close();
		
		
		String queryForCans = "insert into candidates(candidateName, electionCode) values ( ?, ? )";
		PreparedStatement psForCans = con.prepareStatement(queryForCans);
		psForCans.setString(2, this.code);
		for (int i=0; i < this.noOfCandidates; i++) {
			String candidate = this.candidates.get(i);
			psForCans.setString(1, candidate);
			psForCans.executeUpdate();
		}
		
		psForCans.close();
		psForElec.close();
		con.close();

	}
	
//	public String toString() {
//		return "";
//	}
	
	public String getdbcode (String uname) {
		String dbcode="none";
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select code from elections where (title=\""+this.title+"\"and description=\""+this.desc
					+"\"and noOfCandidates=\""+this.noOfCandidates+"\"and unameConducted=\""+uname+"\""+
					"and start_date=\""+this.start_date
					+"\"and end_date=\""+this.end_date+"\")";
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				dbcode = rs.getString("code");
			}
			else {
				dbcode = "none";
			}
			
			st.close();
			con.close();
		} 
		catch (Exception ex) {
			System.out.println(ex);
		}
		return dbcode;
	}
	
	
	public String getdbStartDate () {
		String date="none";
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select start_date from elections where code=\""+this.code+"\"";
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				date = rs.getString(1);
			}
			else {
				date = "none";
			}
			
			st.close();
			con.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return date;
	}
	
	public String getdbEndDate () {
		String date="none";
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select end_date from elections where code=\""+this.code+"\"";
			
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				date = rs.getString(1);
			}
			else {
				date = "none";
			}
			
			st.close();
			con.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return date;
	}
	
	public boolean alreadyVoted (String uname) {
		boolean test = false;
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select * from votes where uname=\""+uname+"\"and electionCode=\""+this.code+"\"";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				test = true;
			}
			else {
				test = false;
			}
			
			st.close();
			con.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return test;
	}
}

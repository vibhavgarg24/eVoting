package com.eVoting.election;

import java.io.IOException;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.eVoting.election.Election; 
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ElectionsVotedServlet")
public class ElectionsVotedServlet extends HttpServlet {
		
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String uname = session.getAttribute("uname").toString();
		
		int noOfVotes = 0;
		
		String url = "jdbc:mysql://localhost:3306/users";
		String queryForVotes = "select votes.electionCode, votes.candidateID, elections.title, elections.unameConducted, candidates.candidateName from votes inner join elections on votes.electionCode = elections.code inner join candidates on votes.candidateID = candidates.candidateID where votes.uname=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root","LoveEveryone");
			
			PreparedStatement psForVotes = con.prepareStatement(queryForVotes);
			psForVotes.setString(1, uname);
			
			ResultSet rsForVotes = psForVotes.executeQuery();
			
			while (rsForVotes.next()) {
				
				String electionCode = rsForVotes.getString(1);
				int candidateID = rsForVotes.getInt(2);
				String electionTitle = rsForVotes.getString(3);
				String unameConducted = rsForVotes.getString(4);
				String candidateName = rsForVotes.getString(5);
			
				int maxVotes = getMaxVotes(electionCode);
				ArrayList <String> winners = new ArrayList <String> ( getwinners (maxVotes,electionCode) );
				
				Election election = new Election();
				election.setCode(electionCode);
				LocalDateTime now = LocalDateTime.now();
				String date = now.toString();
				date = date.substring(0,16);
				String end_date = election.getdbEndDate();
				
				VoteDetails vd = new VoteDetails();
				vd.electionCode = electionCode;
				vd.candidateID = candidateID;
				vd.electionTitle = electionTitle;
				vd.unameConducted = unameConducted;
				vd.candidateName = candidateName;
				if (date.compareTo(end_date) < 0) {
					vd.winners.add("Election not ended yet");
				}
				else {
					vd.winners = winners;
				}
				
				noOfVotes++;
				String attributeName = "vd" + noOfVotes;
				request.setAttribute(attributeName, vd);
			}
			
			psForVotes.close();
			con.close();
			
		} catch (Exception e) {
			response.sendRedirect("homepage.jsp?msg=Error");
		}
		
		request.setAttribute("noOfVotes", noOfVotes);
		request.getRequestDispatcher("electionsVoted.jsp").forward(request, response);
		
	}
	
	public class VoteDetails {
		String electionCode;
		int candidateID;
		String electionTitle;
		String unameConducted;
		String candidateName;
		ArrayList <String> winners;
		
		VoteDetails () {
			winners = new ArrayList <String> ();
		}
		
		public String getElectionCode() {
			return electionCode;
		}
		public int getCandidateID() {
			return candidateID;
		}
		public String getElectionTitle() {
			return electionTitle;
		}
		public String getUnameConducted() {
			return unameConducted;
		}
		public String getCandidateName() {
			return candidateName;
		}
		public ArrayList<String> getWinners() {
			return winners;
		}
		
	}

	public int getMaxVotes (String code) {
		int votes=0;
		String url = "jdbc:mysql://localhost:3306/users";
		String username = "root";
		String password = "LoveEveryone";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select max(counts) from"
					+ "(select count(uname) as counts from votes where electionCode=\""+code+"\" group by candidateID) as votes";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				votes = rs.getInt(1);
			}
			
			st.close();
			con.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return votes;
	}
	
	public ArrayList<String> getwinners (int maxVotes,String code) {
		ArrayList <String> winners = new ArrayList <String> ();
		String url = "jdbc:mysql://localhost:3306/users";
		String username = "root";
		String password = "LoveEveryone";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select votes.candidateID,candidateName from (select candidateID,count(uname) as counts "
					+ "from votes where electionCode=\""+code+"\" group by candidateID) as votes join candidates "
					+ "on candidates.candidateID=votes.candidateID where counts="+maxVotes;
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				winners.add(rs.getString(2));
			}
			
			st.close();
			con.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return winners;
	}
}

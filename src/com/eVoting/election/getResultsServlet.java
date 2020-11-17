package com.eVoting.election;

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

@WebServlet("/getResultsServlet")
public class getResultsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String Code = request.getParameter("Code");
		int noOfCandidates = Integer.parseInt( request.getParameter("noOfCandidates") ); 
		
		//int noOfVotes[] = new int[noOfCandidates];
		
		String url = "jdbc:mysql://localhost:3306/users";
		String username = "root";
		String password = "LoveEveryone";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			//String query = "select candidateID,count(uname) as counts from votes where electionCode=\""+Code+"\" group by candidateID";
			
			String query = 
					"select candidates.candidateID,counts from \r\n"
					+ "(select candidateID from candidates where electionCode=\""+Code+"\" ) as candidates left join\r\n"
					+ "(select candidateID,count(uname) as counts from votes where electionCode=\""+Code+"\" group by candidateID ) as votes\r\n"
					+ "on candidates.candidateID=votes.candidateID";
			
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			int i=1;
			int maxVotes = 0;
			
			while (rs.next()) {
				int CandidateId = rs.getInt(1);
				int noOfVotes = rs.getInt(2);
				
				if (noOfVotes>=0 || noOfVotes<0) { 
				}
				else {
					noOfVotes = 0;
				}
				
				CandidateVoteDetails cvd = new CandidateVoteDetails();
				cvd.CandidateId = CandidateId;
				cvd.noOfVotes = noOfVotes;
				cvd.CandidateName = getCandName(CandidateId);
				
				String attributeName = "cvd"+i;
				request.setAttribute(attributeName, cvd);
				
				if (noOfVotes>maxVotes) {
					maxVotes = noOfVotes;
				}
				
				i++;
			}
			
			request.setAttribute("maxVotes",maxVotes);
			st.close();
			con.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		
		request.setAttribute("Code", Code);
		request.setAttribute("noOfCandidates", noOfCandidates);
		request.getRequestDispatcher("getResults.jsp").forward(request, response);
	}

	public class CandidateVoteDetails {
		int CandidateId;
		int noOfVotes;
		String CandidateName;
		
		public int getCandidateId() {
			return CandidateId;
		}
		public void setCandidateId(int candidateId) {
			CandidateId = candidateId;
		}
		public int getNoOfVotes() {
			return noOfVotes;
		}
		public void setNoOfVotes(int noOfVotes) {
			this.noOfVotes = noOfVotes;
		}
		public String getCandidateName() {
			return CandidateName;
		}
		public void setCandidateName(String candidateName) {
			CandidateName = candidateName;
		}
		
	}
	
	public String getCandName(int id) {
		String name = "";
		String url = "jdbc:mysql://localhost:3306/users";
		String username = "root";
		String password = "LoveEveryone";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select candidateName from candidates where candidateID="+id;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				name = rs.getString(1);
			}
			else {
				name = "";
			}
			
			st.close();
			con.close();
		}
		catch (Exception ex) {
			System.out.println(ex);
		}
		return name;
	}
}

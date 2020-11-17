package com.eVoting.election;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eVoting.election.ElectionsVotedServlet.VoteDetails;


@WebServlet("/ElectionsConductedServlet")
public class ElectionsConductedServlet extends HttpServlet {

	protected void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String uname = session.getAttribute("uname").toString();
		
		int noOfElecConducted = 0;
		
		String url = "jdbc:mysql://localhost:3306/users";
		String queryForElecConducted = "select * from elections where unameConducted = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root","LoveEveryone");
			
			PreparedStatement psForElecConducted = con.prepareStatement(queryForElecConducted);
			psForElecConducted.setString(1, uname);
			
			ResultSet rsForElecConducted = psForElecConducted.executeQuery();
			
			while (rsForElecConducted.next()) {
				
				String electionCode = rsForElecConducted.getString(1);
				String electionTitle = rsForElecConducted.getString(2);
				String electionDesc = rsForElecConducted.getString(3);
				int noOfCandidates = rsForElecConducted.getInt(5);
				
				ElecConductedDetails ecd = new ElecConductedDetails();
				ecd.electionCode = electionCode;
				ecd.electionTitle = electionTitle;
				ecd.electionDesc = electionDesc;
				ecd.noOfCandidates = noOfCandidates;
				
				noOfElecConducted++;
				String attributeName = "ecd" + noOfElecConducted;
				request.setAttribute(attributeName, ecd);
			}
			
			psForElecConducted.close();
			con.close();
			
		} catch (Exception e) {
			response.sendRedirect("homepage.jsp?msg=Error");
		}
		
		request.setAttribute("noOfElecConducted", noOfElecConducted );
		request.getRequestDispatcher("electionsConducted.jsp").forward(request, response);
		
	}
	
	public class ElecConductedDetails {
		String electionCode;
		int noOfCandidates;
		String electionTitle;
		String electionDesc;
		
		
		public String getElectionCode() {
			return electionCode;
		}
		public int getNoOfCandidates() {
			return noOfCandidates;
		}
		public String getElectionTitle() {
			return electionTitle;
		}
		public String getElectionDesc() {
			return electionDesc;
		}
	}

}


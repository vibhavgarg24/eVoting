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

@WebServlet("/CodeCheckServlet")
public class CodeCheckServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String nextPage = "";
		String codeEntered = request.getParameter("codeEntered");
		
		String url = "jdbc:mysql://localhost:3306/users";
		String queryForCode = "select * from elections where code = ?";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root","LoveEveryone");
	
			PreparedStatement psForCode = con.prepareStatement(queryForCode);
			psForCode.setString(1, codeEntered);
							
			ResultSet rsForCode = psForCode.executeQuery();
	
			if (rsForCode.next()) {
				
				Election election = new Election();
				election.setCode(codeEntered);
				election.setTitle(rsForCode.getString(2));
				election.setDesc(rsForCode.getString(3));
				election.setUnameConducted(rsForCode.getString(4));
				election.setNoOfCandidates(rsForCode.getInt(5));
				
				String queryForCans = "select candidateID, candidateName from candidates where electionCode = ?";
				
				PreparedStatement psForCans = con.prepareStatement(queryForCans);
				psForCans.setString(1, codeEntered);
				
				ResultSet rsForCans = psForCans.executeQuery();
				
				int i=1;
				while (rsForCans.next()) {
					String attrCanID = "candidateID" + i;
					request.setAttribute(attrCanID, rsForCans.getInt(1));
					String attrCanName = "candidateName" + i;
					request.setAttribute(attrCanName, rsForCans.getString(2));
					i++;
				}
				
				psForCans.close();
				
				request.setAttribute("election", election);
				
				request.getRequestDispatcher("vote.jsp").forward(request, response);
				
//				nextPage = "enterCode.jsp?electionCode="+codeEntered;
//				response.sendRedirect(nextPage);
			
			} else {
				nextPage = "enterCode.jsp?msg=Wrong Code";
				response.sendRedirect(nextPage);
			}
	
			psForCode.close();
			con.close();
		} 
		catch (Exception e) {
			nextPage = "enterCode.jsp?msg=Error";
			response.sendRedirect(nextPage);
		}
	}

}

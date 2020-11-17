package com.eVoting.election;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/VoteServlet")
public class VoteServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String uname = session.getAttribute("uname").toString();
		request.setAttribute("uname", uname);
		
		String electionCode = request.getParameter("electionCode");
		
		int votedCanID = Integer.parseInt( request.getParameter("candidatesRadio") );
		String votedCanName = "";
		
		if (votedCanID == 0) {
			votedCanName = "nota";
			request.setAttribute("votedCanID", votedCanID);
			request.setAttribute("votedCanName", votedCanName);
		} else {
			
			request.setAttribute("votedCanID", votedCanID);
			
			String url = "jdbc:mysql://localhost:3306/users";
			String queryForCanName = "select candidateName from candidates where candidateID = ?";
	
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,"root","LoveEveryone");
		
				PreparedStatement psForCanName = con.prepareStatement(queryForCanName);
				psForCanName.setInt(1, votedCanID);
								
				ResultSet rsForCanName = psForCanName.executeQuery();
				
				if (rsForCanName.next()) {
					votedCanName = rsForCanName.getString(1);
					request.setAttribute("votedCanName", votedCanName);
					
//					request.getRequestDispatcher("voted.jsp").forward(request, response);
				}
				
				psForCanName.close();
				con.close();
	
			} catch (Exception e){
				response.sendRedirect("vote.jsp?msg=Error");
			}
		}
		
		if (!stored(electionCode,uname)) {
				String url = "jdbc:mysql://localhost:3306/users";
				String queryForVote = "insert into votes values( ?, ?, ?)";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url,"root","LoveEveryone");
					
					PreparedStatement psForVote = con.prepareStatement(queryForVote);
					psForVote.setString(1, electionCode);
					psForVote.setString(2, uname);
					psForVote.setInt(3, votedCanID);
					
					psForVote.executeUpdate();
					
					psForVote.close();
					con.close();
					
				} catch (Exception e) {
					response.sendRedirect("vote.jsp?msg=Error");
				}
		}
				
		request.getRequestDispatcher("voted.jsp").forward(request, response);		

	}
	
	public boolean stored (String electionCode,String uname) {
		boolean test = false;
		try {
			String url = "jdbc:mysql://localhost:3306/users";
			String username = "root";
			String password = "LoveEveryone";
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,username,password);
			
			String query = "select * from votes where electionCode=\""+electionCode+"\" and uname=\""+uname+"\"";
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

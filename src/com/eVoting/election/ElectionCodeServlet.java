package com.eVoting.election;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ElectionCodeServlet")
public class ElectionCodeServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String electionCode = request.getParameter("electionCode");
		
		response.sendRedirect("vote.jsp?electionCode=" + electionCode);
	}

}

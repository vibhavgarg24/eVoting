package com.eVoting.election;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.getopt.util.hash.FNV1a32;

@WebServlet("/ElectionConfigServlet")
public class ElectionConfigServlet extends HttpServlet {


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uname = request.getParameter("uname").toString();
		String title = request.getParameter("title").toString();
		String desc = request.getParameter("desc").toString();
		int noOfCandidates = Integer.parseInt( request.getParameter("noOfCandidates").toString() );
		
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		
		long unix = System.currentTimeMillis() / 10L;
		FNV1a32 fn = new FNV1a32();
		fn.init(uname + unix);
		String code = Long.toHexString(fn.getHash());
				
		Election election = new Election();
		election.setTitle(title);
		election.setDesc(desc);
		election.setUnameConducted(uname);
		election.setNoOfCandidates(noOfCandidates);
		election.setStart_date(start_date);
		election.setEnd_date(end_date);
		
		String dbcode = election.getdbcode(uname);
		
		if (!dbcode.equals("none")) {
			code = dbcode;
			election.setCode(code);
		}
		else {
			election.setCode(code);
			
			for (int i=1; i<=noOfCandidates; i++) {
				String candidate = request.getParameter("candidateName" + i);
				election.addCandidate(candidate);	
			}
			
			try {
				election.save();
			} catch (Exception e) {
				System.out.println(e);
				election.code = "error";
			}
			
		}
			
		request.setAttribute("election", election);
		
		request.getRequestDispatcher("electionCode.jsp").forward(request, response);
			
		//response.sendRedirect("electionCandidates.jsp?election="+election);
	}

}

package com.eVoting.election;

import java.util.ArrayList;

public class Election {

	String title = "";
	String desc = "";
	int noOfCandidates;
	String code;
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
	
	
	public int getNoOfCandidates() {
		return noOfCandidates;
	}
	public void setNoOfCandidates(int noOfCandidates) {
		this.noOfCandidates = noOfCandidates;
	}
	
//	public String toString() {
//		return "";
//	}
}

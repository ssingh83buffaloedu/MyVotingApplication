package com.my.voting.application.MyVotingApplication;

public class VotingQuestionResult {
	private String questionText;
    private long totalVotes;
    private double yesPercentage;
    private double noPercentage;
    
    VotingQuestionResult() {}
    
    VotingQuestionResult(String questionText, int totalVotes, double yesPercentage, double noPercentage)
    {
    	this.questionText = questionText;  	
    	this.totalVotes = totalVotes;
    	this.yesPercentage = yesPercentage;
    	this.noPercentage = noPercentage;
    }
    
	public String getQuestionText() {
		return questionText;
	}
	
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public long getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(long totalVotes) {
		this.totalVotes = totalVotes;
	}

	public double getYesPercentage() {
		return yesPercentage;
	}

	public void setYesPercentage(double yesPercentage) {
		this.yesPercentage = yesPercentage;
	}

	public double getNoPercentage() {
		return noPercentage;
	}

	public void setNoPercentage(double noPercentage) {
		this.noPercentage = noPercentage;
	}

}

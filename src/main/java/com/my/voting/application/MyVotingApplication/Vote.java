package com.my.voting.application.MyVotingApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="vote")
public class Vote {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="vote_generator", sequenceName="vote_seq", allocationSize=20)
	private Long id;
	
	@ManyToOne
	private VotingQuestions votingQuestions;
	
	private String votedByUser;
	private boolean voteValue;
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getVotedByUser() {
		return votedByUser;
	}
	
	public void setVotedByUser(String votedByUser) {
		this.votedByUser = votedByUser;
	}
	
	public boolean isVoteValue() {
		return voteValue;
	}
	
	public void setVoteValue(boolean voteValue) {
		this.voteValue = voteValue;
	}
	
	public VotingQuestions getVotingQuestions()
	{
		return votingQuestions;
	}
	
	public void setVotingQuestions(VotingQuestions votingQuestions)
	{
		this.votingQuestions = votingQuestions;
	}
}

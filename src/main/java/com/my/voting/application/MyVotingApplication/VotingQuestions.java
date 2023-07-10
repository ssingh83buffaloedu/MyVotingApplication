package com.my.voting.application.MyVotingApplication;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="VotingQuestions")
public class VotingQuestions {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="voting_question_generator", sequenceName="voting_question_seq", allocationSize=20)
	private Long id;
	
	@Column(length = 200, nullable = false, unique=true)
	private String questionText;
	
	private String createdByUser;
	private LocalDateTime timestamp;
	
	
	VotingQuestions() {}
	
	VotingQuestions(String questionText, String createdByUser, LocalDateTime timestamp, boolean voted)
	{
		this.questionText = questionText;
		this.setUsername(createdByUser);
		this.setTimestamp(timestamp);
	}
	
	public Long getId()
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public String getQuestionText()
	{
		return questionText;
	}
	
	public void setQuestionText(String questionText)
	{
		this.questionText = questionText;
	}

	public String getUsername() {
		return createdByUser;
	}

	public void setUsername(String username) {
		this.createdByUser = username;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (!(o instanceof VotingQuestions))
	      return false;
	    VotingQuestions votingQuestions = (VotingQuestions) o;
	    return Objects.equals(this.id, votingQuestions.id) 
	    	&& Objects.equals(this.questionText, votingQuestions.questionText)
	    	&& Objects.equals(this.createdByUser, votingQuestions.createdByUser)
	        && Objects.equals(this.timestamp, votingQuestions.timestamp);
    }
	
	@Override
	public int hashCode() {
	    return Objects.hash(this.id, this.questionText, this.createdByUser, this.timestamp);
	}
	
	@Override
	public String toString() {
	    return "VotingQuestions{" + "id=" + this.id + ", questionText='" + this.questionText +'\'' +
	    		", userName='" + this.createdByUser + '\'' + ", timestamp='" + this.timestamp + '\'' + '}';
	}
}

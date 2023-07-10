package com.my.voting.application.MyVotingApplication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IVoteRrepository extends JpaRepository<Vote, Long> {
	Optional<Vote> findByVotedByUser(String votedByUser);
	
	long countByVotingQuestions_Id(Long votingQuestionId);
	
	long countByVotingQuestions_IdAndVoteValue(Long votingQuestionId, boolean voteValue);
}
	

package com.my.voting.application.MyVotingApplication;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVotingQuestionsRepository extends JpaRepository<VotingQuestions, Long> {
	Optional<VotingQuestions> findByQuestionText(String questionText);

	List<VotingQuestions> findByOrderByTimestampDesc();
}

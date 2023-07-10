package com.my.voting.application.MyVotingApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingQuestionsService {
	private final IVotingQuestionsRepository votingQuestionsRepository;
	private final IVoteRrepository voteRepository;
	
	public VotingQuestionsService(IVotingQuestionsRepository votingQuestionsRepository,
			IVoteRrepository voteRepository) {
        this.votingQuestionsRepository = votingQuestionsRepository;
        this.voteRepository = voteRepository;
    }
	
	@Transactional
	public ResponseEntity<?> saveQuestion(String questionText, String username, 
			LocalDateTime timestamp) {
        if (questionText.isEmpty()) {
            return ResponseEntity.badRequest().body("Question text cannot be empty");
        }

        Optional<VotingQuestions> existingQuestion = votingQuestionsRepository.findByQuestionText(questionText);
        if (existingQuestion.isPresent() && existingQuestion.get().getQuestionText().equals(questionText)) {
            return ResponseEntity.unprocessableEntity().body("Duplicate question"); 
        }
        
        VotingQuestions votingQuestion = new VotingQuestions();
        votingQuestion.setQuestionText(questionText);
        votingQuestion.setUsername(username);
        votingQuestion.setTimestamp(timestamp);
        votingQuestionsRepository.save(votingQuestion);

        return ResponseEntity.ok().body("Voting question saved successfully");
	}
	
	@Transactional
	public List<VotingQuestions> getQuestions(String votedByUser)
	{
		List<VotingQuestions> questionsList = votingQuestionsRepository.findByOrderByTimestampDesc();
		Optional<Vote> voteList = voteRepository.findByVotedByUser(votedByUser);
		if(voteList.isEmpty())
			return questionsList;
		
		List<Long> votedQuestionIds = voteList.stream().map(vote->vote.getVotingQuestions().getId())
				.collect(Collectors.toList());
		
		List<VotingQuestions> remainingQuestionsList = questionsList.stream()
				.filter(question-> !votedQuestionIds.contains(question.getId()))
				.collect(Collectors.toList());
		
		return remainingQuestionsList;
	}
	
	@Transactional
	public ResponseEntity<?> saveVote(Long questionId, boolean voteValue, String votedByUser)
	{
		Optional<VotingQuestions> question = votingQuestionsRepository.findById(questionId);
		Vote vote = new Vote();
		vote.setVotingQuestions(question.get());
		vote.setVotedByUser(votedByUser);
		vote.setVoteValue(voteValue);
		voteRepository.save(vote);
		
		return ResponseEntity.ok().body("Voting question saved successfully");
	}
	
	@Transactional
	public List<VotingQuestionResult> getResults()
	{
		List<VotingQuestions> questionsList = votingQuestionsRepository.findByOrderByTimestampDesc();
		List<VotingQuestionResult> resultList = new ArrayList<>();
		long totalVotes, yesVoteCount, noVoteCount;
		double yesPercentage = 0.0;
		double noPercentage = 0.0;
		for(VotingQuestions question : questionsList)
		{
			yesPercentage =0.0;
			noPercentage =0.0;
			totalVotes = voteRepository.countByVotingQuestions_Id(question.getId());
			yesVoteCount = voteRepository.countByVotingQuestions_IdAndVoteValue(question.getId(), true);
			noVoteCount = voteRepository.countByVotingQuestions_IdAndVoteValue(question.getId(), false);
			if(totalVotes > 0)
			{
				yesPercentage = ((double)yesVoteCount / totalVotes) * 100;
				noPercentage = ((double)noVoteCount / totalVotes) * 100;
			}
			
			VotingQuestionResult result = new VotingQuestionResult();
			result.setQuestionText(question.getQuestionText());
			result.setTotalVotes(totalVotes);
			result.setYesPercentage(yesPercentage);
			result.setNoPercentage(noPercentage);
			
			resultList.add(result);
		}
		
		return resultList;
	}	
}

package com.my.voting.application.MyVotingApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MyVotingApplicationController {
	
	@Autowired
	private MyVotingUserService votingUserService;
	
	@Autowired
	private VotingQuestionsService votingQuestionsService;
	
	@GetMapping("/login")
	public String login()	
	{
		return "login";
	}
	
	@GetMapping("/api/questions")
	@ResponseBody
    public List<VotingQuestions> getQuestions(@RequestParam("votedByUser") String votedByUser) {
		List<VotingQuestions> questionsList = votingQuestionsService.getQuestions(votedByUser);

        return questionsList;
    }
	
	@PostMapping("/api/username")
	public ResponseEntity<String> saveUserName(@RequestBody MyVotingUser votingUser,
			Model model)
	{
		if(votingUser.getUserName().length() < 5 || votingUser.getUserName().length() > 20
				|| !StringUtils.isAlphanumeric(votingUser.getUserName()))
		{
				ResponseEntity.badRequest().body("Invalid username");
		}
				
		ResponseEntity<?> response = votingUserService.createUser(votingUser.getUserName(),votingUser.getPassword());
		if (response.getStatusCode().is2xxSuccessful())
		{
			return ResponseEntity.ok("Username saved successfully");
		}
		else {
			return ResponseEntity.badRequest().body("Failed to save user");
		}
	}
	
	@PostMapping("api/question")
	public ResponseEntity<String> saveQuestion(@RequestBody String questionText, 
			@RequestHeader("username") String username)
	{
		LocalDateTime timestamp = LocalDateTime.now();
		if(!(questionText.trim().length() > 0 && questionText.length() <= 200))
		{
			return ResponseEntity.badRequest().body("Invalid question text");
		}
		ResponseEntity<?> response = votingQuestionsService.
				saveQuestion(questionText, username, timestamp);
		if(response.getStatusCode().is2xxSuccessful())
		{
			return ResponseEntity.ok("Voting question saved successfully");
		}
		else if(response.getStatusCode().is4xxClientError())
		{
			return ResponseEntity.unprocessableEntity().body("Duplicate question");
		}
		else
		{
			return ResponseEntity.internalServerError().body("Failed to save");
		}
	}
	
	@PostMapping("api/vote")
	public ResponseEntity<String> voteOnQuestion(@RequestHeader Long questionId, @RequestBody String voteValue, 
			@RequestHeader("username") String username) throws JsonMappingException, JsonProcessingException 
	{
		ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode jsonNode = objectMapper.readTree(voteValue);
	    boolean vote = jsonNode.get("voteValue").asBoolean();
		ResponseEntity<?> response  = votingQuestionsService.saveVote(questionId, vote, username);
		
		if(response.getStatusCode().is2xxSuccessful())
		{
			return ResponseEntity.ok("Voting question saved successfully");
		}
		else
		{
			return ResponseEntity.internalServerError().body("Failed to save");
		}
	}
	
	@GetMapping("/api/results")
	@ResponseBody
    public List<VotingQuestionResult> getResults() {
		List<VotingQuestionResult> resultsList = votingQuestionsService.getResults();

        return resultsList;
    }
}

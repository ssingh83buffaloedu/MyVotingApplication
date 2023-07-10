package com.my.voting.application.MyVotingApplication;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyVotingUserService {
	private final IVotingUserRepository userRepository;
	
	public MyVotingUserService(IVotingUserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Transactional
	public ResponseEntity<?> createUser(String userName, String password) {
        if (userName.isEmpty()) {
            return ResponseEntity.badRequest().body("Username cannot be empty");
        }

        Optional<MyVotingUser> existingUser = userRepository.findByUserNameIgnoreCase(userName);
        if (existingUser.isPresent() && existingUser.get().getUserName().equals(userName)) {
            return ResponseEntity.ok().body("Welcome, " + userName + "!"); 
        }
        
        MyVotingUser user = new MyVotingUser();
        user.setUserName(userName);
        user.setPassword(password);
        userRepository.save(user);
        return ResponseEntity.ok().body("User created successfully");
	}
}

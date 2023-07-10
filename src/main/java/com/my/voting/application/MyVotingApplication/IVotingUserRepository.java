package com.my.voting.application.MyVotingApplication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotingUserRepository extends JpaRepository<MyVotingUser, Long> {
	Optional<MyVotingUser> findByUserNameIgnoreCase(String userName);
}

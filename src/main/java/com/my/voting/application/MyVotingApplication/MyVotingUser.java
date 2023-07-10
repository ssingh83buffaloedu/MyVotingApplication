package com.my.voting.application.MyVotingApplication;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="VotingUsers")
public class MyVotingUser {
	
	  @Id 
	  @GeneratedValue(strategy=GenerationType.SEQUENCE)
	  @SequenceGenerator(name="user_generator", sequenceName="user_seq", allocationSize=20)
	  private Long id;
	  @Column(nullable=false, unique =true)
	  private String userName;
	  @Column(nullable=false)
	  private String password;
	  
	  MyVotingUser() {}
	  
	  MyVotingUser(String userName, String password)
	  {
		  this.userName = userName;
		  this.password = password;
	  }
	  
	  public Long getId()
	  {
		  return this.id;
	  }
	  
	  public String getUserName()
	  {
		  return this.userName;
	  }
	  
	  public String getPassword()
	  {
		  return this.password;
	  }
	  
	  public void setId(Long id)
	  {
		  this.id = id;
	  }
	  
	  public void setUserName(String userName)
	  {
		  this.userName = userName;
	  }
	  
	  public void setPassword(String password)
	  {
		  this.password = password;
	  }
	  
	  @Override
	  public boolean equals(Object o) {

	    if (this == o)
	      return true;
	    if (!(o instanceof MyVotingUser))
	      return false;
	    MyVotingUser votingUser = (MyVotingUser) o;
	    return Objects.equals(this.id, votingUser.id) && Objects.equals(this.userName, votingUser.userName)
	        && Objects.equals(this.password, votingUser.password);
	  }
	  
	  @Override
	  public int hashCode() {
	    return Objects.hash(this.id, this.userName, this.password);
	  }
	  
	  @Override
	  public String toString() {
	    return "VotingUser{" + "id=" + this.id + ", userName='" + this.userName + '\'' + ", password='" + this.password + '\'' + '}';
	  }
}

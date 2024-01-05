package com.adt.assignment1.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.adt.assignment1.model.User;

public interface UserRepository extends MongoRepository<User, String>{
	@Query("{username:'?0'}")
	User findByUsername(String username);
	

}

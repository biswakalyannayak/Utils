package com.example.jpadata.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.jpadata.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	

}

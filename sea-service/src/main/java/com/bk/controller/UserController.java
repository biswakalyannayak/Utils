package com.bk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bk.api.bean.User;
import com.bk.entity.UserEntity;
import com.bk.mapper.UserMapper;
import com.bk.repository.UserRepository;

@RestController("/user")
public class UserController {
	
	@Autowired UserRepository userRepository;
	
	@RequestMapping(value = "/userId/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findUserByUserId(@PathVariable("id") Integer userId) throws Exception {
		 UserEntity userFound = userRepository.findOne(userId);
		 User toReturn = UserMapper.INSTANCE.entityToBean(userFound);
		if (toReturn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
		UserEntity toSave = UserMapper.INSTANCE.beantoEntity(user);
		UserEntity userEntitySaved = userRepository.save(toSave);
		User toReturn = UserMapper.INSTANCE.entityToBean(userEntitySaved);
		if (toReturn == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(toReturn, HttpStatus.OK);
	}

}

package com.example.jpadata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpadata.entity.User;
import com.example.jpadata.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired UserRepository userRepository;
	
	@GetMapping("/")
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }
	
	@GetMapping("/find/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userRepository.findOne(id) , HttpStatus.OK);
    }
	@PostMapping
    public ResponseEntity<User> message(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user) , HttpStatus.OK);
    }

}

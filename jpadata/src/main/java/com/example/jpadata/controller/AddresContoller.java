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

import com.example.jpadata.entity.Address;
import com.example.jpadata.repository.AddressRepository;

@RestController
@RequestMapping("/adress")
public class AddresContoller {
	
	@Autowired AddressRepository addressRepository;
	
	@GetMapping("/find/{id}")
    public ResponseEntity<Address> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(addressRepository.findOne(id) , HttpStatus.OK);
    }
	@PostMapping
    public ResponseEntity<Address> message(@RequestBody Address address) {
		 return new ResponseEntity<>(addressRepository.save(address) , HttpStatus.OK);
    }
	

}


package com.example.jpadata.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpadata.bean.SearchCriteria;
import com.example.jpadata.bean.SearchResponse;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@PostMapping
    public ResponseEntity<Set<SearchResponse>> message(@RequestBody SearchCriteria criteria) {
		Set<SearchResponse> responses = new HashSet<>();
		SearchResponse resposne =  new SearchResponse(); 
		resposne.setEmail(criteria.getEmail());
		
		responses.add(resposne);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}

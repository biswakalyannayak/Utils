package com.example.jpadata.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpadata.bean.SearchCriteria;
import com.example.jpadata.bean.SearchResponse;
import com.example.jpadata.repository.CustomSerachRepository;

@RestController
@RequestMapping("/search")
public class SearchController {
	
	@Autowired CustomSerachRepository customSerachRepository;
	
	@PostMapping
    public ResponseEntity<List<SearchResponse>> message(@RequestBody SearchCriteria criteria) {
		List<SearchResponse> responses = new ArrayList<>();
		responses = customSerachRepository.search(criteria);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

}

package com.example.jpadata.repository;

import java.util.List;

import com.example.jpadata.bean.SearchCriteria;
import com.example.jpadata.bean.SearchResponse;

public interface CustomSerachRepository {
	
	public List<SearchResponse> search(SearchCriteria criteria);
	
}

package com.example.jpadata.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.example.jpadata.bean.SearchCriteria;
import com.example.jpadata.bean.SearchResponse;

@Component
public class CustomSerachRepositoryImpl implements CustomSerachRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SearchResponse> search(SearchCriteria criteria) {
		StringBuilder queryStrBuld = new StringBuilder("SELECT NEW com.example.jpadata.bean.SearchResponse(u.id,u.userName, u.email,a.flat,a.state)"+
						"FROM User AS u , Address As a WHERE u.id = a.user.id");
		String queryStr = enrichWhereCondition(queryStrBuld,criteria);
		/*Object ref =  this.entityManager.
				//createQuery("select e from Employee e where e.employeeName like '"+name+"'").
				createQuery(queryStr).
					getResultList();*/
		
		TypedQuery<SearchResponse> query =
			  this.entityManager.createQuery(queryStr, SearchResponse.class);
		List<SearchResponse> results = query.getResultList();
		System.out.println(results);
		return results;
	}

	private String enrichWhereCondition(StringBuilder queryStrBuld, SearchCriteria criteria) {
		if(criteria.getEmail() != null){
			queryStrBuld.append(" AND u.email = '").append(criteria.getEmail()).append("'");
		}
		if(criteria.getUserName() != null){
			queryStrBuld.append( " AND u.userName = '").append(criteria.getUserName()).append("'");
		}
		if(criteria.getFlat() != null){
			queryStrBuld.append( " AND a.flat = '").append(criteria.getFlat()).append("'");
		}
		if(criteria.getState() != null){
			queryStrBuld.append( " AND a.state = '").append(criteria.getState()).append("'");
		}
		
		return queryStrBuld.toString();
	}

}

package com.partha.resultservice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partha.resultservice.models.ResultDetails;
import com.partha.resultservice.repositories.ResultDetailsRepository;

@Repository
public class ResultDetailsDao {
	
	@Autowired
	private ResultDetailsRepository resultDetailsRepository;
	
	public ResultDetails getResultDetailsByRollNo(String rollno)
	{
		System.out.println("In dao class "+ rollno);
		return resultDetailsRepository.getResultByRollNo(rollno);
	}

}

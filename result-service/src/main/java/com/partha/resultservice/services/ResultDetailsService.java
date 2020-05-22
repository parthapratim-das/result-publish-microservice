package com.partha.resultservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partha.resultservice.dao.ResultDetailsDao;
import com.partha.resultservice.models.ResultDetails;

@Service
public class ResultDetailsService {
	
	@Autowired
	private ResultDetailsDao resultDetailsDao;
	
	public ResultDetails getResultDetailsByRollNo(String rollno)
	{
		System.out.println("In service class "+ rollno);
		return resultDetailsDao.getResultDetailsByRollNo(rollno);
	}

}

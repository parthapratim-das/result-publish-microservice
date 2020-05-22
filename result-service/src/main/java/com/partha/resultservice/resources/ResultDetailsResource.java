package com.partha.resultservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.partha.resultservice.models.ResultDetails;
import com.partha.resultservice.services.ResultDetailsService;

@RestController
@RequestMapping("/mongo/result")
public class ResultDetailsResource {
	
	@Autowired
	private ResultDetailsService resultDetailsService;
	
	@RequestMapping("/getresult/{rollno}")
	public ResultDetails getResultDetailsByRollNo(@PathVariable String rollno)
	{
		System.out.println(rollno);
		return resultDetailsService.getResultDetailsByRollNo(rollno);
	}

}

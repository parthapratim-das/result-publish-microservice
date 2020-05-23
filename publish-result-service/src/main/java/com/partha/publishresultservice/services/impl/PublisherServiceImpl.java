package com.partha.publishresultservice.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.partha.publishresultservice.models.FinalResult;
import com.partha.publishresultservice.models.ResultDetails;
import com.partha.publishresultservice.models.Student;
import com.partha.publishresultservice.services.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "getFallBackResultByParams")
	public FinalResult getHttpReqResponseByParams(String regno, int year) {
		
		FinalResult finalResult = new FinalResult();
		
		ResponseEntity<List<Student>> studentRecordResponse =
		        restTemplate.exchange("http://localhost:8080/sql/student/get/"+regno+"/"+year,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
		            });
		List<Student> studentRecords = studentRecordResponse.getBody();
		/*List<Student> studentRecords = new ArrayList<>();
		studentRecords.add(new Student(202,"S2212","Poulami",2013,"s1011R18"));
		System.out.println(studentRecords);*/
		finalResult.setRegistration(studentRecords.get(0).getRegistration());
		finalResult.setStudentname(studentRecords.get(0).getStudentname());
		
		List<ResultDetails> results = new ArrayList<ResultDetails>();
		for(Student record : studentRecords)
		{
			ResultDetails resultDetails = 
					(ResultDetails)restTemplate.getForObject("http://localhost:8083/mongo/result/getresult/"+record.getRollno(), ResultDetails.class);
			results.add(resultDetails);
		}
		finalResult.setAllResults(results);
		
		return finalResult;
	}

	@Override
	@HystrixCommand(fallbackMethod = "getFallBackResultByParam")
	public FinalResult getHttpReqResponseByParams(String regno) {
		FinalResult finalResult = new FinalResult();
		
		ResponseEntity<List<Student>> studentRecordResponse =
		        restTemplate.exchange("http://student-details-service/sql/student/get/"+regno,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
		            });
		List<Student> studentRecords = studentRecordResponse.getBody();
		finalResult.setRegistration(studentRecords.get(0).getRegistration());
		finalResult.setStudentname(studentRecords.get(0).getStudentname());
		
		List<ResultDetails> results = new ArrayList<ResultDetails>();
		for(Student record : studentRecords)
		{
			ResultDetails resultDetails = 
					(ResultDetails)restTemplate.getForObject("http://result-details-service/mongo/result/getresult/"+record.getRollno(), ResultDetails.class);
			results.add(resultDetails);
		}
		finalResult.setAllResults(results);
		
		return finalResult;
	}
	
	public FinalResult getFallBackResultByParams(String regno, int year)
	{
		List<ResultDetails> fallBackResult = new ArrayList<>();
		ResultDetails result = new ResultDetails();
		result.setRollno("xxx999xxx");
		result.setS1(-999);
		result.setS2(-999);
		result.setS3(-999);
		result.setS4(-999);
		result.setS5(-999);
		result.setS6(-999);
		result.setS7(-999);
		result.setS8(-999);
		fallBackResult.add(result);
		return new FinalResult("Not Available","-XX99XX99",fallBackResult);
	}
	
	public FinalResult getFallBackResultByParam(String regno)
	{
		List<ResultDetails> fallBackResult = new ArrayList<>();
		ResultDetails result = new ResultDetails();
		result.setRollno("xxx999xxx");
		result.setS1(-999);
		result.setS2(-999);
		result.setS3(-999);
		result.setS4(-999);
		result.setS5(-999);
		result.setS6(-999);
		result.setS7(-999);
		result.setS8(-999);
		fallBackResult.add(result);
		return new FinalResult("Not Available","-XX99XX99",fallBackResult);
	}
	

}

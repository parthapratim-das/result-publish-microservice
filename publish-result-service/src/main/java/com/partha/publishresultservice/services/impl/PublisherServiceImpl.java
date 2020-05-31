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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.partha.publishresultservice.models.FinalResult;
import com.partha.publishresultservice.models.ResultDetails;
import com.partha.publishresultservice.models.Student;
import com.partha.publishresultservice.services.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "getFallBackStudentByParams",
					commandProperties = {
							@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
							@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
							@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
							@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
					})
	public FinalResult getHttpReqResponseByParams(String regno, int year) {
		
		FinalResult finalResult = new FinalResult();
		
		ResponseEntity<List<Student>> studentRecordResponse =
		        restTemplate.exchange("http://student-details-service/sql/student/get/"+regno+"/"+year,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
		            });
		List<Student> studentRecords = studentRecordResponse.getBody();
		finalResult = this.getResultFromMongo(studentRecords);
		return finalResult;
	}

	@Override
	@HystrixCommand(fallbackMethod = "getFallBackStudentByParam",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			})
	public FinalResult getHttpReqResponseByParams(String regno) {
		
		FinalResult finalResult = new FinalResult();
		ResponseEntity<List<Student>> studentRecordResponse =
		        restTemplate.exchange("http://student-details-service/sql/student/get/"+regno,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
		            });
		List<Student> studentRecords = studentRecordResponse.getBody();
		finalResult = this.getResultFromMongo(studentRecords);
		return finalResult;
		
	}
	
	@HystrixCommand(fallbackMethod = "getFallBackResultFromMongo",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
			})
	public FinalResult getResultFromMongo(List<Student> studentRecords)
	{
		FinalResult finalResult = new FinalResult();
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
	
	public FinalResult getFallBackStudentByParams(String regno, int year)
	{
		List<Student> student  = Arrays.asList(new Student(0000,"No Data", "No Data", 9999, "xxxx"));
		FinalResult finalResult = this.getResultFromMongo(student);
		return finalResult;
	}
	
	public FinalResult getFallBackStudentByParam(String regno)
	{
		List<Student> student  = Arrays.asList(new Student(0000,"No Data", "No Data", 9999, "xxxx"));
		FinalResult finalResult = this.getResultFromMongo(student);
		return finalResult;
	}
	
	public ResultDetails getFallBackResultFromMongo(List<Student> studentRecords)
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
		return result;
	}
	

}

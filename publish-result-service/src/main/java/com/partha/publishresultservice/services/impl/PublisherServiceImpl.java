package com.partha.publishresultservice.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.partha.publishresultservice.models.FinalResult;
import com.partha.publishresultservice.models.ResultDetails;
import com.partha.publishresultservice.models.Student;
import com.partha.publishresultservice.services.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService{
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public FinalResult getHttpReqResponseByParams(String regno, int year) {
		
		FinalResult finalResult = new FinalResult();
		
		ResponseEntity<List<Student>> studentRecordResponse =
		        restTemplate.exchange("http://localhost:8085/sql/student/get/"+regno+"/"+year,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
		            });
		List<Student> studentRecords = studentRecordResponse.getBody();
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
	public FinalResult getHttpReqResponseByParams(String regno) {
FinalResult finalResult = new FinalResult();
		
		ResponseEntity<List<Student>> studentRecordResponse =
		        restTemplate.exchange("http://movie-data-service-new/ratings/"+regno,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
		            });
		List<Student> studentRecords = studentRecordResponse.getBody();
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

}

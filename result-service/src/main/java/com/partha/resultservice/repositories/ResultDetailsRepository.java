package com.partha.resultservice.repositories;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.partha.resultservice.models.ResultDetails;

public interface ResultDetailsRepository extends MongoRepository<ResultDetails,BigInteger>{
	
	Logger logger = LoggerFactory.getLogger(ResultDetailsRepository.class);
	
	@Query("{'rollno' : ?0}")
	ResultDetails getResultByRollNo(String rollno);
	
	//@Query(value = "{$where: 'this.rollno == ?0'}")
	//ResultDetails findResultDetailsByRollno(String rollno);

	
	//public ResultDetails findByRollno(String rollno);

}

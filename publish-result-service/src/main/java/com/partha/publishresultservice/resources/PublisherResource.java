package com.partha.publishresultservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.publishresultservice.models.FinalResult;
import com.partha.publishresultservice.services.PublisherService;

@RestController
@RequestMapping("/result")
public class PublisherResource {
	
	@Autowired
	private PublisherService publisherService;
	
	Logger logger = LoggerFactory.getLogger(PublisherResource.class); 
	
	@RequestMapping("/{regno}/{year}")
	public FinalResult getFinalResultByParams(@PathVariable String regno, @PathVariable Integer year){
		logger.info("Inside PublisherResource -> getFinalResultByParams" + regno +" "+year);
		return publisherService.getHttpReqResponseByParams(regno, year);
		
	}
	
	@RequestMapping("/{regno}")
	public FinalResult getFinalResultByRegNo(@PathVariable String regno){
		logger.info("Inside PublisherResource -> getFinalResultByRegNo" + regno);
		return publisherService.getHttpReqResponseByParams(regno);
		
	}

}

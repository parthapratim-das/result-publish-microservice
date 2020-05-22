package com.partha.publishresultservice.services;

import java.util.List;


import com.partha.publishresultservice.models.FinalResult;

public interface PublisherService {
	
	public FinalResult getHttpReqResponseByParams(String regno,int year);
	public FinalResult getHttpReqResponseByParams(String regno);

}

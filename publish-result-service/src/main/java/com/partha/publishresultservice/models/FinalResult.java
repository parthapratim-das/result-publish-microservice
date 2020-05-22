package com.partha.publishresultservice.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinalResult {
	
	private String studentname;
	private String registration;
	//private int year;
	//private String rollno;
	private List<ResultDetails> allResults;

}

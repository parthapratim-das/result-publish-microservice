package com.partha.publishresultservice.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	
	private int id;
	private String studentname;
	private String registration;
	private int year;
	private String rollno;

}

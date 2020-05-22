package com.partha.studentdetails.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.partha.studentdetails.models.Student;
import com.partha.studentdetails.repositories.StudentDetailsRepository;

@Repository
public class StudentDetailsDao {
	
	@Autowired
	private StudentDetailsRepository studentDetailsRepository;
	
	public List<Student> getStudentByParameters(String regno, int year)
	{
		List<Student> listStudents = studentDetailsRepository.findByParameters(regno, year);
		return listStudents;
	}
	
	public List<Student> getStudentByRegistrationNo(String regNo)
	{
		List<Student> listStudents = studentDetailsRepository.findByRegistrationNumber(regNo);
		return listStudents;
	}

}

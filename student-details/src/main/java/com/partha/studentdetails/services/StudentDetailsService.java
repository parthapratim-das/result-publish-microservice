package com.partha.studentdetails.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partha.studentdetails.dao.StudentDetailsDao;
import com.partha.studentdetails.models.Student;

@Service
public class StudentDetailsService {
	
	@Autowired
	private StudentDetailsDao studentDetailsDao;
	
	public List<Student> getStudentByParams(String regno, int year)
	{
		return studentDetailsDao.getStudentByParameters(regno, year);
	}
	
	public List<Student> getStudentByRegParams(String regno)
	{
		return studentDetailsDao.getStudentByRegistrationNo(regno);
	}

}

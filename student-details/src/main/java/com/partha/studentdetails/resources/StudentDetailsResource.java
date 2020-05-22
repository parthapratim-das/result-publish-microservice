package com.partha.studentdetails.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partha.studentdetails.models.Student;
import com.partha.studentdetails.services.StudentDetailsService;

@RestController
@RequestMapping("/sql/student")
public class StudentDetailsResource {
	
	@Autowired
	private StudentDetailsService StudentDetailsService;
	
	Logger logger = LoggerFactory.getLogger(StudentDetailsResource.class);
	
	@RequestMapping("/get/{regno}/{year}")
	public List<Student> getStudentByParam(@PathVariable String regno, @PathVariable Integer year)
	{
		logger.info("Inside Student resource : " + regno + " " + year);
		List<Student> listStudents = StudentDetailsService.getStudentByParams(regno, year);
		return listStudents;
	}
	
	@RequestMapping("/get/{regno}")
	public List<Student> getStudentByRegistrationParam(@PathVariable String regno)
	{
		logger.info("Inside Student resource : " + regno);
		List<Student> listStudents = StudentDetailsService.getStudentByRegParams(regno);
		return listStudents;
	}

}

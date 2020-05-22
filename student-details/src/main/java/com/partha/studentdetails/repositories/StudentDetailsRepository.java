package com.partha.studentdetails.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.partha.studentdetails.models.Student;

public interface StudentDetailsRepository extends JpaRepository<Student, Integer> {
	
	@Query(value = "SELECT * FROM STUDENT_DETAILS WHERE registration = ? and year = ?", nativeQuery = true)
	List<Student> findByParameters(String registration, int year);
	
	@Query(value = "SELECT * FROM STUDENT_DETAILS WHERE registration = ?", nativeQuery = true)
	List<Student> findByRegistrationNumber(String registration);

}

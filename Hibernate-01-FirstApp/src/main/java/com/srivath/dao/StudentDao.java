package com.srivath.dao;

import com.srivath.entity.Student;

public interface StudentDao {
	void saveStudent(Student student);

	Student loadStudent(int sid);

	Student updateStudent(int sid, int marks);

	void deleteStudent(int sid);
	
	void level1CacheTest();
}

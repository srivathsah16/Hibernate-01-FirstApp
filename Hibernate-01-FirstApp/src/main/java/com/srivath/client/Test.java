package com.srivath.client;

import com.srivath.dao.StudentDao;
import com.srivath.dao.impl.StudentDaoImpl;
import com.srivath.entity.Student;

public class Test {
	public static void main(String[] args) {
		StudentDao dao = new StudentDaoImpl();

//		Student student = new Student();
//		student.setSid(3);
//		student.setSname("Prakruti");
//		student.setGender("Female");
//		student.setMarks(100);
//		dao.saveStudent(student);
//
//		Student student2 = dao.loadStudent(1);
//		System.out.println(student2);

//		Student stu = dao.updateStudent(8, 600);
//		System.out.println(stu);

//		dao.deleteStudent(8);
		dao.level1CacheTest();
	}
}

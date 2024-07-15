package com.srivath.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import com.srivath.dao.StudentDao;
import com.srivath.entity.Student;

public class StudentDaoImpl implements StudentDao {

	SessionFactory factory;

	public StudentDaoImpl() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
		factory = metadata.getSessionFactoryBuilder().build();
	}

	@Override
	public void saveStudent(Student student) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(student);
			t.commit();
			System.out.println("Student object persisted in DB.");
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error in persisting student object");
			System.out.println(e);
		} finally {
			session.close();
			// factory.close();
		}
	}

	@Override
	public Student loadStudent(int sid) {
		/*
		 * load() -> lazy loading and get() -> early loading
		 */
		Session session = factory.openSession();
		Student student = session.load(Student.class, sid);
//		try {
//			Thread.sleep(10000);
//		} catch (Exception e) {
//		}
		// System.out.println("Name of student = " + student.getSname());
		session.close();
		return student;
	}

	@Override
	public Student updateStudent(int sid, int marks) {
		Session session = factory.openSession();
		Student student = session.get(Student.class, sid);
		Transaction t = session.beginTransaction();
		try {
			student.setMarks(marks);
			session.update(student);
			t.commit();
			System.out.println("Student obj with sid=" + sid + " is updated.");
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error while updating student obj with sid=" + sid);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return student;
	}

	@Override
	public void deleteStudent(int sid) {
		Session session = factory.openSession();
		Student student = session.get(Student.class, sid);
		Transaction t = session.beginTransaction();
		try {
			session.delete(student);
			t.commit();
			System.out.println("Student obj with sid=" + sid + " is deleted.");
		} catch (Exception e) {
			t.rollback();
			System.out.println("Error while deleting Student obj with sid=" + sid);
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void level1CacheTest() {
		Session session = factory.openSession();
		Session other_Session = factory.openSession();

		Student s1 = session.get(Student.class, 1);
		session.evict(s1);
//		Student s2 = session.get(Student.class, 2);
//		Student s3 = other_Session.get(Student.class, 1);
		Student s4 = session.get(Student.class, 1);
		
		session.close();
		other_Session.close();
	}

}

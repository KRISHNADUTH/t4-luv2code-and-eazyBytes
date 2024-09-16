package com.mapping.manytomany;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mapping.manytomany.model.Course;
import com.mapping.manytomany.model.Student;
import com.mapping.manytomany.repo.CourseRepo;
import com.mapping.manytomany.repo.StudentRepo;

@SpringBootApplication
public class ManyToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourseRepo courseRepo, StudentRepo studentRepo)
	{
		return (runner)->
		{
			// createCourseAndStudents(courseRepo);
			// findCourseAndStudentsByCourseId(courseRepo);
			// findStudentAndCoursesByStudentId(studentRepo);
			// addMoreCoursesToStudentWithId(studentRepo);
			// deleteCourseWithId(courseRepo);
			// deleteStudentWithId(studentRepo);
		};
	}



	private void createCourseAndStudents(CourseRepo courseRepo) {
		Course newCourse = new Course("Core Java.");
		Student newStudent1 = new Student("Krishnaduth", "A.M", "krishnaduth@gmail.com");
		Student newStudent2 = new Student("Appu", "K.M", "appu@gmail.com");
		newCourse.addStudent(newStudent1);
		newCourse.addStudent(newStudent2);
		courseRepo.save(newCourse);
		System.out.println("New student saved with id -  "+newCourse.getId());
	}
	
	private void findCourseAndStudentsByCourseId(CourseRepo courseRepo) {
		Integer id = 1;
		System.out.println("finding course for Id - " + id);
		Course course = courseRepo.findCourseAndStudentsByCourseId(id);
		System.out.println("Course   - " + course);
		System.out.println("Students enrolled for course - " + course.getStudents());
	}
	
	private void findStudentAndCoursesByStudentId(StudentRepo studentRepo) {
		Integer id = 2;

		Student student = studentRepo.findStudentAndCoursesByStudentId(id);
		System.out.println("Details of student with id - " + id + " " + student);
		System.out.println("Courses for the student - " + student.getCourses());
	}
	
	private void addMoreCoursesToStudentWithId(StudentRepo studentRepo) {
		Integer id = 48;
		Student student = studentRepo.findStudentAndCoursesByStudentId(id);
		// Creating new courses need to be added
		if (student != null)
		{
			System.out.println("Student is - " + student);
			System.out.println("Courses before adding extra - " + student.getCourses());
			Course course1 = new Course("React JS course");
			Course course2 = new Course("Data Structures A-Z");
			Course course3 = new Course("Data Ware housing");
			student.addCourse(course1);
			student.addCourse(course2);
			student.addCourse(course3);
			studentRepo.save(student);
			System.out.println(
					"Courses after updating student - "
							+ studentRepo.findStudentAndCoursesByStudentId(id).getCourses());
		}
		else
			System.out.println("student with id - "+id+" Couldn't found in DB");
	}
	
	private void deleteCourseWithId(CourseRepo courseRepo) {
		Integer id = 45;
		Course courseToBeDeleted = courseRepo.findCourseAndStudentsByCourseId(id);
		if (courseToBeDeleted != null) {
			System.out.println("Course to be deleted - " + courseToBeDeleted);
			courseRepo.deleteById(id);
			System.out.println("Deleted......");
		} else {
			System.out.println("Course with id - " + id + " cannot be found in db");
		}
	}
	
	
	private void deleteStudentWithId(StudentRepo studentRepo) {

		Integer id = 2;
		Student studentToBeDeleted = studentRepo.findStudentAndCoursesByStudentId(id);
		if (studentToBeDeleted != null)
		{
			System.out.println("Student to delete - " + studentToBeDeleted);
			studentRepo.deleteById(id);
			System.out.println("Deleted.........");
		}
		else {
			System.out.println("Student with given Id - " + id + " cannot found in DB...");
		}
	}
}

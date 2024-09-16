package com.mapping.ManyToOne;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mapping.ManyToOne.model.Course;
import com.mapping.ManyToOne.model.Instructor;
import com.mapping.ManyToOne.repo.CourseRepo;
import com.mapping.ManyToOne.repo.InstructorRepo;

@SpringBootApplication
public class ManyToOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManyToOneApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorRepo repo, CourseRepo repo1)
	{
		return (runner) -> {
			// createInstructorWithcourse(repo);

			// findInstructorWithcourse(repo, repo1);

			// findInstructorWithcourseJoinFetch(repo);

			// deleteInstructorById(repo, repo1);

			deleteCourseById(repo1);
		};
	}
	
	
	private void createInstructorWithcourse(InstructorRepo repo) {
		Instructor instructor = new Instructor("Jhon", "Seran");
		Course course1 = new Course("Biology");
		Course course2 = new Course("Zoology");
		Course course3 = new Course("Botoni");
		Course course4 = new Course("Psychology");
		instructor.addCourse(course1);
		instructor.addCourse(course2);
		instructor.addCourse(course3);
		instructor.addCourse(course4);
		System.out.println("Instructor  - " + instructor);
		System.out.println("Courses   - "+instructor.getCourses());
		repo.save(instructor);
		System.out.println("Saved..........");
	}	
	

	private void findInstructorWithcourse(InstructorRepo repo, CourseRepo repo1) {
		Integer id = 3;
		Instructor currentInstructor = repo.findById(id).get();
		List<Course> courses = repo1.findCoursesByInstructorId(id);
		System.out.println("Instructor id is - "+id);
		System.out.println("Current instructor - " + currentInstructor);
		currentInstructor.setCourses(courses);
		System.out.println("Associated courses - " + currentInstructor.getCourses());
		System.out.println("Done!");
	}

	private void findInstructorWithcourseJoinFetch(InstructorRepo repo) {
		Integer id = 6;
		Instructor currentInstructor = repo.findInstructorWithCoursesbyInstructorIdJoinFetch(id);
		System.out.println("Instructor id is - "+id);
		System.out.println("Current instructor using Join Fetch method - " + currentInstructor);
		System.out.println("Associated courses - " + currentInstructor.getCourses());
		System.out.println("Done!");
	}

	private void deleteInstructorById(InstructorRepo repo, CourseRepo repo1) {
		Integer id = 7;
		Instructor instructorToBeDeleted = repo.findInstructorWithCoursesbyInstructorIdJoinFetch(id);

		System.out.println("Instructor to be deleted - "+instructorToBeDeleted);
		List<Course> coursesOFInstructorTobeDeleted = instructorToBeDeleted.getCourses();
		
		// Detach courses from Instructor
		coursesOFInstructorTobeDeleted.forEach(c -> c.setInstructor(null));
		
		// Delete instructor from DB-
		repo.delete(instructorToBeDeleted);
		System.out.println("Instructor with id - "+id+" is deleted from DB.");
	}
	
	private void deleteCourseById(CourseRepo repo1) {
		Integer id = 13;
		Course course = repo1.findById(id).get();
		System.out.println("Course with id " + id + " is - " + course);
		repo1.delete(course);
		System.out.println("Deleted!!!!!!!");
	}
	/* 

	public void createInstructor(InstructorRepo repo)
	{
		List<Course> courses = new ArrayList<>();
		Instructor instructor = new Instructor("Jimmy", "Bro");
			courses.add(new Course("Baseball", instructor));
			courses.add(new Course("Voiline", instructor));
			courses.add(new Course("Guitar", instructor));
			courses.add(new Course("FootBall", instructor));

			instructor.setCourses(courses);
			System.out.println("Instructor is   ---   " + instructor);
			repo.save(instructor);
			System.out.println("Saved...........");
	}
*/

}

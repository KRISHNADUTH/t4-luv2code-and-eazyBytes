package com.mapping.OneToMany;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mapping.OneToMany.model.Course;
import com.mapping.OneToMany.model.Review;
import com.mapping.OneToMany.repo.CourseRepo;

@SpringBootApplication
public class OneToManyApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneToManyApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CourseRepo repo)
	{
		return (runner)->
		{
			// createCourseWithReviews(repo);
			// findCourseWithReviewJoinFetch(repo);
			// deleteCourseWithId(repo);
		};
	}

	private void createCourseWithReviews(CourseRepo repo) {
		Course newCourse = new Course("Java Scipt Mastery");
		newCourse.addReview(new Review("In depth"));
		newCourse.addReview(new Review("Understandable"));
		newCourse.addReview(new Review("Does the Job."));
		System.out.println("Course - " + newCourse);
		System.out.println("Reviews - "+newCourse.getReviews());
		repo.save(newCourse);
		System.out.println("Done!!!");
	}

	private void findCourseWithReviewJoinFetch(CourseRepo repo) {
		Integer id = 2;
		Course courseWithReviews = repo.findCourseWithReviewByCourseIdJoinFetch(id);
		System.out.println("Course is - " + courseWithReviews);
		System.out.println("Reviews of that Course - "+courseWithReviews.getReviews());
	}
	
	private void deleteCourseWithId(CourseRepo repo) {
		Integer id = 6;
		System.out.println("Deleting course having ID - "+id);
		repo.deleteById(id);
		System.out.println("Done!!!!!");
	}

}

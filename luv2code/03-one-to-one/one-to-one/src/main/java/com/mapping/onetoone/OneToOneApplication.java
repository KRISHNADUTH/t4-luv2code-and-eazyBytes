package com.mapping.onetoone;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mapping.onetoone.model.Instructor;
import com.mapping.onetoone.model.InstructorDetail;
import com.mapping.onetoone.repo.InstructorDetailsRepository;
import com.mapping.onetoone.repo.InstructorRepository;
@SuppressWarnings("unused")
@SpringBootApplication
public class OneToOneApplication {
	public static void main(String[] args) {
		SpringApplication.run(OneToOneApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(InstructorRepository repo, InstructorDetailsRepository repo1) // @Bean annotation will automatically find out object for InstructorRepository here. No need to use @Autowired.
	{
		return (runner) -> {
			// saveNewInstructor(repo);
			// System.out.println(findInstructorById(repo, 1));
			// deleteInstructorById(repo, 3);
			// findInstructorDetailsById(repo1, 3);
			// deleteInstructorDetailById(repo1, 9); - This function will not work since in InstructorDetail entity class for relation with Instructor we have set all caseCadeType types except remove, so instructor entity will not be removed even if we have removed assiciated instructorDetail entity, for doing so we need to set InstructorDetail Property of associated instructor entity as null for successfull deletion of Instructor detail entity.
			// deleteOnlyInstructorDetailById(repo, repo1, 12);
		};
	}

	public void saveNewInstructor(InstructorRepository repo) {
		InstructorDetail newInstructorDetails = new InstructorDetail("Switzerland", "Fighting");
		Instructor newInstructor = new Instructor("Britas", "Army", newInstructorDetails);
		repo.save(newInstructor);
		System.out.println("Saved successfully.......................&&&&&&&&&&&");
	}

	public Instructor findInstructorById(InstructorRepository repo, Integer id) {
		return repo.findById(id).get();
	}

	
	private void deleteInstructorById(InstructorRepository repo, int id) {
		repo.deleteById(id);
	}

	private void findInstructorDetailsById(InstructorDetailsRepository repo1, Integer id) {
		InstructorDetail currentInstructorDetails = repo1.findById(id).get();
		System.out.println("currentInstructorDetails  -  " + currentInstructorDetails);
		Instructor correspondingInstructor = currentInstructorDetails.getInstructor();
		System.out.println("Corresponding instructor  -  " + correspondingInstructor);
	}

	private void deleteInstructorDetailById(InstructorDetailsRepository repo1, Integer id) {

		repo1.deleteById(id);
		System.out.println("Successfully deleted...............");
	}

	private void deleteOnlyInstructorDetailById(InstructorRepository repo, InstructorDetailsRepository repo1,
			Integer id) {
/*
		Instructor instructor = repo.findById(id).get(); // This operation will not work as expected since now this entity is in managed state. In DB still that particular Instructor entity has link to entitydetails(and Instructor of the owner of this OneToOne relationship). So inorder to set InstructorDetail to null in DB we need to save that Instructor after assigning Instructor detail parameter to null
		instructor.setInstructorDetails(null);
		System.out.println("Instructor is - "+repo.findById(id).get());
		repo1.deleteById(id);
		System.out.println("Instructor after deletion - "+ repo.findById(id).get());
 */
		// Working code1 - This code is working because we are doing DB enquiry at one time before deleting instuctorDetail, and using same Instructor entity to find InstructorDetail to be deleted. 
		Instructor tempInstructor = repo.findById(id).get();
		InstructorDetail instructorDetailToBeDeleted = tempInstructor.getInstructorDetails();
		tempInstructor.setInstructorDetails(null);
		repo1.delete(instructorDetailToBeDeleted);
		System.out.println("Instructor after deletion of Instructor detail - " + repo.findById(id).get());
		System.out.println("Done!!!!!!!!!!!");

		// Working code2
	/*	Optional<Instructor> instructorOptional = repo.findById(id);
		if (instructorOptional.isPresent()) {
			Instructor instructor = instructorOptional.get();
		
			// Unlink the Instructor from the InstructorDetail
			instructor.setInstructorDetails(null);
		
			// Save the Instructor to persist the change
			repo.save(instructor);
		
			// Now delete the InstructorDetail by ID
			repo1.deleteById(id);
		} 	 */
	}
}
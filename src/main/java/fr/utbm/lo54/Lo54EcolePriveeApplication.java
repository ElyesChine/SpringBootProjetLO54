package fr.utbm.lo54;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import fr.utbm.lo54.core.Client;
import fr.utbm.lo54.core.Course;
import fr.utbm.lo54.core.CourseSession;
import fr.utbm.lo54.core.Location;

import fr.utbm.lo54.service.IClientService;
import fr.utbm.lo54.service.ICourseService;
import fr.utbm.lo54.service.ICourseSessionService;
import fr.utbm.lo54.service.ILocationService;

import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
@EnableAutoConfiguration

// the class implements the interface CommandLineRunner to allow writing in the console
public class Lo54EcolePriveeApplication implements CommandLineRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(Lo54EcolePriveeApplication.class, args);
	}

	@Autowired
	private ICourseSessionService iCourseSessionService;
	@Autowired
	private IClientService iClientService;
	@Autowired
	private ICourseService iCourseService;
	@Autowired
	private ILocationService iLocationService;

	@Override
	public void run(String... args) throws Exception
	{
		System.out.println("[Begin Execution]");

		/* INSERTING CLIENTS TO THE DATABASE */
		Client c1 = iClientService.create(new Client("Elyes", "Chine", "6 rue de Madrid, 90000 Belfort", "0658507131", "elyes@utbm.fr", "admin"));
		Client c2 = iClientService.create(new Client("Eya", "Labidi", "Belfort", "0644159623", "eya@utbm.fr", "admin"));
		Client c3 = iClientService.create(new Client("Fred", "Duda", "Lyon", "0644159621", "fred@utbm.fr", "admin"));
		System.out.println("Clients inserted successfully.");

		/* INSERTING COURSES TO THE DATABASE */
		Course course1 = iCourseService.create(new Course("BD40", "Systemes d'information"));
		Course course2 = iCourseService.create(new Course("BD50", "Conception des bases de donnees"));
		Course course3 = iCourseService.create(new Course("BD51", "Business intelligence & data warehouse"));
		Course course4 = iCourseService.create(new Course("LO41", "Systeme d'Exploitation : Principes et Communication"));
		Course course5 = iCourseService.create(new Course("LO43", "Bases fondamentales de la programmation orientee objet"));
		Course course6 = iCourseService.create(new Course("LO44", "Algorithmique et programmation"));
		Course course7 = iCourseService.create(new Course("LO54", "Java Enterprise Applications Architectures and Development Frameworks"));
		Course course8 = iCourseService.create(new Course("GL52", "Genie logiciel"));
		System.out.println("Courses inserted successfully.");


		/* INSERTING lOCATIONS TO THE DATABASE */
		Location location1 = iLocationService.create(new Location("Belfort"));
		Location location2 = iLocationService.create(new Location("Montbéliard"));
		Location location3 = iLocationService.create(new Location("Sevenans"));
		Location location4 = iLocationService.create(new Location("Montpellier"));
		Location location5 = iLocationService.create(new Location("Angers"));
		Location location6 = iLocationService.create(new Location("Toulouse"));

		/* INSERTING COURSE-SESSIONS TO THE DATABASE */
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate ld1 = LocalDate.parse("11/12/2019", f);
		LocalDate ld2 = LocalDate.parse("18/10/2019", f);
		LocalDate ld3 = LocalDate.parse("10/10/2019", f);
		LocalDate ld4 = LocalDate.parse("10/12/2019", f);
		LocalDate ld5 = LocalDate.parse("18/09/2019", f);
		LocalDate ld6 = LocalDate.parse("07/09/2019", f);
		LocalDate ld7 = LocalDate.parse("21/09/2019", f);
		LocalDate ld8 = LocalDate.parse("08/08/2019", f);

		LocalDate ld21 = LocalDate.parse("29/01/2020", f);
		LocalDate ld22 = LocalDate.parse("01/02/2020", f);
		LocalDate ld23 = LocalDate.parse("14/01/2020", f);
		LocalDate ld24 = LocalDate.parse("15/01/2020", f);
		LocalDate ld25 = LocalDate.parse("29/12/2019", f);
		LocalDate ld26 = LocalDate.parse("28/12/2019", f);
		LocalDate ld27 = LocalDate.parse("19/12/2019", f);
		
		CourseSession courseSession1 = iCourseSessionService.create(new CourseSession(ld1, ld21, 2, location1, course1));
		CourseSession courseSession2 = iCourseSessionService.create(new CourseSession(ld2, ld22, 3, location2, course2));
		CourseSession courseSession3 = iCourseSessionService.create(new CourseSession(ld3, ld23, 4, location3, course1));
		CourseSession courseSession4 = iCourseSessionService.create(new CourseSession(ld4, ld24, 5, location4, course4));
		CourseSession courseSession5 = iCourseSessionService.create(new CourseSession(ld5, ld25,  6, location5, course6));
		CourseSession courseSession6 = iCourseSessionService.create(new CourseSession(ld6, ld26, 6, location6, course7));
		CourseSession courseSession7 = iCourseSessionService.create(new CourseSession(ld7, ld27,  6, location1, course8));
		CourseSession courseSession8 = iCourseSessionService.create(new CourseSession(ld8, ld27,  6, location2, course8));
		System.out.println("Course-Session inserted successfully.");

		iClientService.saveEnroll(courseSession1, c2, 0);
		iClientService.saveEnroll(courseSession1, c2, 1);

		iClientService.saveEnroll(courseSession3, c1, 0);
		iClientService.saveEnroll(courseSession3, c2, 1);
		iClientService.saveEnroll(courseSession3, c3, 2);


		System.out.println("Enroll inserted successfully.");

		System.out.println("[End Execution]");
	}
}

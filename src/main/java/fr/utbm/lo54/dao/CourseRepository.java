package fr.utbm.lo54.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.utbm.lo54.core.Course;

public interface CourseRepository extends JpaRepository<Course, String>
{
	 @Query(value = "select c.Course_code,c.Title from Course c where c.Course_code like CONCAT('%',:keyWord,'%')", nativeQuery = true)
	    public ArrayList<Course> filterCourseByKeyWord(@Param("keyWord") String keyWord);

	 @Query(value = "select c.Course_code,c.Title from Course c inner join Course_Session cs "
	            + "on c.Course_Code = cs.Course_Code "
	            + "inner join Location l on cs.Location_Id = l.Location_Id "
	            + "where l.City like CONCAT('%',:locationWord,'%')", nativeQuery = true)
	    public ArrayList<Course> filterCourseByLocation(@Param("locationWord") String location);

	 @Query(value = "select c.Course_code,c.Title from Course c inner join Course_Session cs "
	            + "on c.Course_Code = cs.Course_Code "
	            + "where cs.Start_Date <= :availableSessionDate And "
	            + "cs.End_Date >= :availableSessionDate", nativeQuery = true)
	    public ArrayList<Course> filterCourseByAvailableSession(@Param("availableSessionDate") LocalDate availableSessionDate);
}

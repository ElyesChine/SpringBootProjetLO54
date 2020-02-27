package fr.utbm.lo54.dao;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.utbm.lo54.core.CourseSession;

public interface CourseSessionRepository extends JpaRepository<CourseSession, Integer>
{
	//Méthode 1: Utilisation des Named Queries pour alléger le code
	//utiliser le "_" pour indiquer que Location est référencé via une association
	public ArrayList<CourseSession> findByLocation_City(String location);
	//Méthode 2 : embedded sql
	//Permet plus de control sur la requete exécutée
	@Query(value = "select cs.Course_Session_Id,cs.COURSE_CODE" + ",cs.LOCATION_ID, cs.Start_Date "
			+ ",cs.End_Date,cs.Max " + ",c.Title,l.City "
			+ "from Course_Session cs inner join Course c " + "on cs.Course_Code = c.Course_Code "
			+ "inner join Location l " + "on cs.location_id = l.location_id "
			+ "where (cs.Course_Session_Id = :idCourseSession)", nativeQuery = true)
	public CourseSession findByIdCourseSession(int idCourseSession);

	@Query(value = "select cs.Course_Session_Id,cs.COURSE_CODE " + ",cs.LOCATION_ID, cs.Start_Date "
			+ ",cs.End_Date,cs.Max " + ",c.Title,l.City "
			+ "from Course_Session cs inner join Course c " + "on cs.Course_Code = c.Course_Code "
			+ "inner join Location l " + "on cs.location_id = l.location_id "
			+ "where c.title like CONCAT('%',:keyWord,'%') ", nativeQuery = true)
	public ArrayList<CourseSession> filterCourseSessionByKeyWord(@Param("keyWord") String keyWord);

	@Query(value = "select cs.Course_Session_Id,cs.COURSE_CODE " + ",cs.LOCATION_ID, cs.Start_Date "
			+ ",cs.End_Date,cs.Max " + ",c.Title,l.City "
			+ "from Course_Session cs inner join Course c " + "on cs.Course_Code = c.Course_Code "
			+ "inner join Location l " + "on cs.location_id = l.location_id "
			+ "where cs.start_date = :availableSessionDate ", nativeQuery = true) // >=
	public ArrayList<CourseSession> filterCourseSessionByAvailableSession(
			@Param("availableSessionDate") LocalDate availableSessionDate);

	@Query(value = "select cs.Course_Session_Id,cs.COURSE_CODE " + ",cs.LOCATION_ID, cs.Start_Date "
			+ ",cs.End_Date,cs.Max " + ",c.Title,l.City "
			+ "from Course_Session cs inner join Course c " + "on cs.Course_Code = c.Course_Code "
			+ "inner join Location l " + "on cs.location_id = l.location_id "
			+ "where (LOWER(c.title) like LOWER(CONCAT('%',:keyWord,'%')) OR c.title IS NULL) "
			+ "AND (LOWER(l.City) like LOWER(CONCAT('%',:locationWord,'%')) OR l.City IS NULL) "
			+ "AND (cs.start_date = :availableSessionDate)", nativeQuery = true)  // >=
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithStartDate(@Param("keyWord") String keyWord,
			@Param("locationWord") String location, @Param("availableSessionDate") LocalDate ldStartDate);

	@Query(value = "select cs.Course_Session_Id,cs.COURSE_CODE " + ",cs.LOCATION_ID, cs.Start_Date "
			+ ",cs.End_Date,cs.Max " + ",c.Title,l.City "
			+ "from Course_Session cs inner join Course c " + "on cs.Course_Code = c.Course_Code "
			+ "inner join Location l " + "on cs.location_id = l.location_id "
			+ "where (LOWER(c.title) like LOWER(CONCAT('%',:keyWord,'%')) OR c.title IS NULL) "
			+ "AND (LOWER(l.City) like LOWER(CONCAT('%',:locationWord,'%')) OR l.City IS NULL) "
			+ "AND (cs.end_date <= :availableSessionDate)", nativeQuery = true)
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithEndDate(@Param("keyWord") String keyWord,
			@Param("locationWord") String location, @Param("availableSessionDate") LocalDate ldEndDate);

	@Query(value = "select cs.Course_Session_Id,cs.COURSE_CODE " + ",cs.LOCATION_ID, cs.Start_Date "
			+ ",cs.End_Date,cs.Max " + ",c.Title,l.City "
			+ "from Course_Session cs inner join Course c " + "on cs.Course_Code = c.Course_Code "
			+ "inner join Location l " + "on cs.location_id = l.location_id "
			+ "where (LOWER(c.title) like LOWER(CONCAT('%',:keyWord,'%')) OR c.title IS NULL) "
			+ "AND (LOWER(l.City) like LOWER(CONCAT('%',:locationWord,'%')) OR l.City IS NULL) "
			+ "AND (cs.start_Date >= :startDateUser)" + "AND (cs.end_date <= :endDateUser) order by cs.Start_Date", nativeQuery = true)
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithDate(@Param("keyWord") String keyWord,
			@Param("locationWord") String location, @Param("startDateUser") LocalDate ldStartDate,
			@Param("endDateUser") LocalDate ldEndDate);

	@Query(value = "select cs.Course_Session_Id,cs.COURSE_CODE " + ",cs.LOCATION_ID, cs.Start_Date "
			+ ",cs.End_Date,cs.Max " + ",c.Title,l.City "
			+ "from Course_Session cs inner join Course c " + "on cs.Course_Code = c.Course_Code "
			+ "inner join Location l " + "on cs.location_id = l.location_id "
			+ "where (LOWER(c.title) like LOWER(CONCAT('%',:keyWord,'%')) OR c.title IS NULL) "
			+ "AND (LOWER(l.City) like LOWER(CONCAT('%',:locationWord,'%')) OR l.City IS NULL) ", nativeQuery = true)
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithoutDate(@Param("keyWord") String keyWord,
			@Param("locationWord") String location);

	@Query(value = "SELECT COUNT(Course_Session_Id) FROM Enroll WHERE Course_Session_Id = :idCourseSession "
			+ "GROUP BY Course_Session_Id", nativeQuery = true)
	public Integer countSizeCourseSession(@Param("idCourseSession") int idCourseSession);
}

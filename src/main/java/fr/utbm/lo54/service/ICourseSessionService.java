package fr.utbm.lo54.service;

import java.time.LocalDate;
import java.util.ArrayList;

import fr.utbm.lo54.core.CourseSession;

public interface ICourseSessionService
{
	public CourseSession create(CourseSession course);

	public CourseSession update(CourseSession course);

	public Boolean delete(CourseSession course);

	public Boolean deleteById(int idCourseSession);

	public ArrayList<CourseSession> findAll();

	public CourseSession findByIdCourseSession(int id);

	public ArrayList<CourseSession> filterCourseSessionByKeyWord(String keyWord);

	public ArrayList<CourseSession> filterCourseSessionByLocation(String location);

	public ArrayList<CourseSession> filterCourseSessionByAvailableSession(LocalDate availableSessionDate);

	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithStartDate(String keyWord, String location, LocalDate localDate);

	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithEndDate(String keyWord, String location, LocalDate ldEndDate);

	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithDate(String keyWord, String location, LocalDate ldStartDate, LocalDate ldEndDate);

	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithoutDate(String keyWord, String location);

	public Integer countSizeCourseSession(int idCourseSession);
}

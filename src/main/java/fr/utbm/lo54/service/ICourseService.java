package fr.utbm.lo54.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import fr.utbm.lo54.core.Course;

public interface ICourseService 
{
	public Course create(Course course);

	public Course update(Course course);

	public Boolean delete(Course course);

	public ArrayList<Course> findAll();

	public Optional<Course> findById(String codeCourse);

	public ArrayList<Course> filterCourseByKeyWord(String keyWord);

	public ArrayList<Course> filterCourseByLocation(String location);

	public ArrayList<Course> filterCourseByAvailableSession(LocalDate localDate);
}

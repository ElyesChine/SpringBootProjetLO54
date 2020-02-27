package fr.utbm.lo54.service;

import fr.utbm.lo54.core.Course;
import fr.utbm.lo54.dao.CourseRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//allowing spring to instanciate this class on the start of the program
@Service
//all methods are transactional
@Transactional
public class CourseServiceImpl implements ICourseService 
{
	// Pour faire l'injection des dépendances, on demande à Spring d'injecter une implémentation de cette interface
	// On a besoin de la couche DAO
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course create(Course course) 
	{
		Course myCourse = null;
		if (course != null) 
		{
			myCourse = courseRepository.save(course);
		}
		return myCourse;
	}

	@Override
	public Course update(Course course)
	{
		Course myCourse = null;
		if (course != null)
		{
			myCourse = courseRepository.save(course);
		}
		return myCourse;
	}

	@Override
	public Boolean delete(Course course) 
	{
		Boolean isDeleted = false;
		if (course != null) 
		{
			courseRepository.delete(course);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public ArrayList<Course> findAll() 
	{
		ArrayList<Course> listOfCourses = (ArrayList<Course>) courseRepository.findAll();
		if (listOfCourses == null)
		{
			throw new RuntimeException("Courses list not found!");
		}
		return listOfCourses;
	}

	@Override
	public Optional<Course> findById(String codeCourse) 
	{
		Optional<Course> myCourse = null;
		if (codeCourse != null)
		{
			myCourse = courseRepository.findById(codeCourse);
		}
		return myCourse;
	}

	@Override
	public ArrayList<Course> filterCourseByKeyWord(String keyWord) 
	{
		ArrayList<Course> listCourseServiceByKeyWord = (ArrayList<Course>) courseRepository.filterCourseByKeyWord("test");
		if (listCourseServiceByKeyWord == null) 
		{
			throw new RuntimeException("List of courses not found!");
		}
		return listCourseServiceByKeyWord;
	}

	@Override
	public ArrayList<Course> filterCourseByLocation(String location) 
	{
		ArrayList<Course> listCourseServiceByLocation = (ArrayList<Course>) courseRepository.filterCourseByLocation(location);
		if (listCourseServiceByLocation == null) 
		{
			throw new RuntimeException("List of courses by location not found!");
		}
		return listCourseServiceByLocation;
	}

	@Override
	public ArrayList<Course> filterCourseByAvailableSession(LocalDate localDate) 
	{
		ArrayList<Course> listCourseServiceByAvailableSession = (ArrayList<Course>) courseRepository.filterCourseByAvailableSession(localDate);
		if (listCourseServiceByAvailableSession == null)
		{
			throw new RuntimeException("List of courses by available session not found!");
		}
		return listCourseServiceByAvailableSession;
	}

}

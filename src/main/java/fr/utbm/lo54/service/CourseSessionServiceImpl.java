package fr.utbm.lo54.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.utbm.lo54.core.CourseSession;
import fr.utbm.lo54.dao.CourseSessionRepository;

@Service
@Transactional
public class CourseSessionServiceImpl implements ICourseSessionService
{
	@Autowired
	private CourseSessionRepository courseSessionRepository;

	@Override
	public CourseSession create(CourseSession courseSession)
	{
		CourseSession myCourseSession = null;
		if (courseSession != null)
		{
			myCourseSession = courseSessionRepository.save(courseSession);
		}
		return myCourseSession;
	}

	@Override
	public CourseSession update(CourseSession courseSession)
	{
		CourseSession myCourseSession = null;
		if (courseSession != null)
		{
			myCourseSession = courseSessionRepository.save(courseSession);
		}
		return myCourseSession;
	}

	@Override
	public Boolean delete(CourseSession courseSession)
	{
		Boolean isDeleted = false;
		if (courseSession != null)
		{
			courseSessionRepository.delete(courseSession);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public ArrayList<CourseSession> findAll()
	{
		ArrayList<CourseSession> listCourseSession = (ArrayList<CourseSession>) courseSessionRepository.findAll();
		if (listCourseSession == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSession;
	}

	@Override
	public CourseSession findByIdCourseSession(int id)
	{
		CourseSession myCourseSession = null;
		if (id > 0)
		{
			myCourseSession = courseSessionRepository.findByIdCourseSession(id);
		}
		return myCourseSession;
	}

	@Override
	public ArrayList<CourseSession> filterCourseSessionByKeyWord(String keyWord)
	{
		ArrayList<CourseSession> listCourseSessionByKeyWord = (ArrayList<CourseSession>) courseSessionRepository.filterCourseSessionByKeyWord(keyWord);
		if (listCourseSessionByKeyWord == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSessionByKeyWord;
	}

	@Override
	public ArrayList<CourseSession> filterCourseSessionByLocation(String location)
	{
		ArrayList<CourseSession> listCourseSessionByLocation = (ArrayList<CourseSession>) courseSessionRepository.findByLocation_City(location);
		if (listCourseSessionByLocation == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSessionByLocation;
	}

	@Override
	public ArrayList<CourseSession> filterCourseSessionByAvailableSession(LocalDate availableSessionDate)
	{
		ArrayList<CourseSession> listCourseSessionByAvailableSession = (ArrayList<CourseSession>) courseSessionRepository.filterCourseSessionByAvailableSession(availableSessionDate);
		if (listCourseSessionByAvailableSession == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSessionByAvailableSession;
	}

	@Override
	public Boolean deleteById(int idCourseSession)
	{
		Boolean isDeleted = false;
		if (idCourseSession > 0)
		{
			courseSessionRepository.deleteById(idCourseSession);
			isDeleted = true;
		}
		return isDeleted;

	}

	@Override
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithStartDate(String keyWord, String location,
			LocalDate availableSessionDate)
	{
		ArrayList<CourseSession> listCourseSessionMultiCriteria = (ArrayList<CourseSession>) courseSessionRepository
				.filterCourseSessionMultiCriteriaWithStartDate(keyWord, location, availableSessionDate);
		if (listCourseSessionMultiCriteria == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSessionMultiCriteria;
	}

	@Override
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithoutDate(String keyWord, String location)
	{
		ArrayList<CourseSession> listCourseSessionMultiCriteria = (ArrayList<CourseSession>) courseSessionRepository
				.filterCourseSessionMultiCriteriaWithoutDate(keyWord, location);
		if (listCourseSessionMultiCriteria == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSessionMultiCriteria;
	}

	@Override
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithEndDate(String keyWord, String location,
			LocalDate ldEndDate)
	{
		ArrayList<CourseSession> listCourseSessionMultiCriteria = (ArrayList<CourseSession>) courseSessionRepository
				.filterCourseSessionMultiCriteriaWithEndDate(keyWord, location, ldEndDate);
		if (listCourseSessionMultiCriteria == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSessionMultiCriteria;
	}

	@Override
	public ArrayList<CourseSession> filterCourseSessionMultiCriteriaWithDate(String keyWord, String location,
			LocalDate ldStartDate, LocalDate ldEndDate)
	{
		ArrayList<CourseSession> listCourseSessionMultiCriteria = (ArrayList<CourseSession>) courseSessionRepository
				.filterCourseSessionMultiCriteriaWithDate(keyWord, location, ldStartDate, ldEndDate);
		if (listCourseSessionMultiCriteria == null)
		{
			throw new RuntimeException("List of CourseSession not found!");
		}
		return listCourseSessionMultiCriteria;
	}

	@Override
	public Integer countSizeCourseSession(int idCourseSession)
	{
		Integer nbClientInSession = 0;
		if (idCourseSession > 0)
		{
			nbClientInSession = courseSessionRepository.countSizeCourseSession(idCourseSession);
			if (nbClientInSession == null)
			{
				nbClientInSession = 0;
			}
		}
		return nbClientInSession;
	}
}

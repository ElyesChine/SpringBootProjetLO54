package fr.utbm.lo54.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.utbm.lo54.core.Client;
import fr.utbm.lo54.core.CourseSession;
import fr.utbm.lo54.core.Location;
import fr.utbm.lo54.service.IClientService;
import fr.utbm.lo54.service.ICourseSessionService;
import fr.utbm.lo54.service.ILocationService;

@Controller
public class SearchController
{
	@Autowired
	private ICourseSessionService iCourseSessionService;
	@Autowired
	private IClientService iClientService;
	@Autowired
	private ILocationService iLocationService;

	@RequestMapping(value = "/ManageCourses", method = RequestMethod.GET)
	public String consultUv(ModelMap map, @RequestParam(required = false) String keyWord,
			@RequestParam(required = false) String location, @RequestParam(required = false) String dateDebut,
			@RequestParam(required = false) String dateFin, @RequestParam(required = false) Integer isEnrolled,
			@RequestParam(required = false) String title, @RequestParam(required = false) String firstName)
	{
		// Verify if the client is already in a course
		if ((isEnrolled != null) & (title != null))
		{
			map.addAttribute("isEnrolled", isEnrolled);
			map.addAttribute("title", title);
		}

		try
		{
			ArrayList<CourseSession> listCourseSession = iCourseSessionService.findAll();
			ArrayList<Location> listLocation = iLocationService.findAll();
			ArrayList<Client> listClient = iClientService.findAll();
			Integer tmpSize = 0;
			// Add Pourcentage
			for (CourseSession elt : listCourseSession)
			{
				tmpSize = iCourseSessionService.countSizeCourseSession(elt.getId());
				elt.setSize(tmpSize);
			}

			map.addAttribute("keyWord", keyWord);
			map.addAttribute("location", location);
			map.addAttribute("dateDebut", dateDebut);
			map.addAttribute("dateFin", dateFin);
			map.addAttribute("firstName", firstName);

			map.addAttribute("listCourseSession", listCourseSession);
			map.addAttribute("listLocation", listLocation);
			map.addAttribute("listClient", listClient);

			for (Client elt1 : listClient)
			{
				System.out.println("List of clients: " + elt1);
			}
		}
		catch (Exception e)
		{
			map.addAttribute("exception", e);
		}
		return "Search";
	}

	@RequestMapping(value = "/ManageCourses", method = RequestMethod.POST)
	public String consultAndFilterUv(ModelMap map, @RequestParam(required = false) String keyWord,
			@RequestParam(required = false) String location, @RequestParam(required = false) String dateDebut,
			@RequestParam(required = false) String dateFin)
	{
		ArrayList<CourseSession> listCourseSessionByKeyWord = null;
		ArrayList<Location> listLocation = iLocationService.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// IF I HAVE FOUND THE LIST, I SAVE IT IN THE MODEL
		try
		{
			// IF EXIST START DATE
			if (!dateDebut.equals(""))
			{
				// IF NO END DATE
				if (dateFin.equals(""))
				{
					LocalDate localStartDate = LocalDate.parse(dateDebut, formatter);
					listCourseSessionByKeyWord = (ArrayList<CourseSession>) iCourseSessionService
							.filterCourseSessionMultiCriteriaWithStartDate(keyWord, location, localStartDate);
				}
				else //IF EXISTS END DATE
				{
					LocalDate localStartDate = LocalDate.parse(dateDebut, formatter);
					LocalDate localEndDate = LocalDate.parse(dateFin, formatter);
					listCourseSessionByKeyWord = (ArrayList<CourseSession>) iCourseSessionService
							.filterCourseSessionMultiCriteriaWithDate(keyWord, location, localStartDate, localEndDate);
				}
			}
			// IF NO START DATE
			else if (dateDebut.equals(""))
			{
				// IF NO END DATE
				if (dateFin.equals(""))
				{
					listCourseSessionByKeyWord = (ArrayList<CourseSession>) iCourseSessionService
							.filterCourseSessionMultiCriteriaWithoutDate(keyWord, location);
				}
				else // IF EXISTS END DATE
				{
					LocalDate localEndDate = LocalDate.parse(dateFin, formatter);
					listCourseSessionByKeyWord = (ArrayList<CourseSession>) iCourseSessionService
							.filterCourseSessionMultiCriteriaWithEndDate(keyWord, location, localEndDate);
				}
			}

			ArrayList<Client> listClient = iClientService.findAll();

			map.addAttribute("keyWord", keyWord);
			map.addAttribute("location", location);
			map.addAttribute("dateDebut", dateDebut);
			map.addAttribute("dateFin", dateFin);
			map.addAttribute("listClient", listClient);

			int tmpSize = 0;
			// ADD PERCENTAGE
			for (CourseSession elt : listCourseSessionByKeyWord)
			{
				tmpSize = iCourseSessionService.countSizeCourseSession(elt.getId());
				elt.setSize(tmpSize);
			}
			map.addAttribute("listCourseSession", listCourseSessionByKeyWord);
			map.addAttribute("listLocation", listLocation);
		}
		catch (Exception e)
		{
			map.addAttribute("exception", e);
		}
		return "Search";
	}

	@RequestMapping(value = "/enrollTransaction", method = RequestMethod.GET)
	public String selectCourseSession(ModelMap modelMap, String typeTransaction, int idCourseSession,
									  int size, @RequestParam	 Integer idClient)
	{
		System.out.println("idClient : "+idClient);
		if(idClient == 0) return "SignupPage";
		Integer isEnrolled = 0;
		String title = "";
		String firstName = "";
		if (typeTransaction.equals("enroll"))
		{
			CourseSession courseSessionToFind = iCourseSessionService.findByIdCourseSession(idCourseSession);
			title = courseSessionToFind.getCourse().getTitle();
			Client clientToFind = iClientService.findByIdClient(idClient);
			isEnrolled = iClientService.saveEnroll(courseSessionToFind, clientToFind, size);
			firstName = clientToFind.getFirstName();
		}
		return "redirect:/ManageCourses?isEnrolled=" + isEnrolled + "&title=" + title + "&idClient=" + idClient	+ "&firstName=" + firstName;
	}
}

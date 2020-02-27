package fr.utbm.lo54.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.utbm.lo54.core.Client;
import fr.utbm.lo54.core.CourseSession;

import fr.utbm.lo54.service.IClientService;
import fr.utbm.lo54.service.ICourseSessionService;

@Controller
public class MySessionCoursesController 
{
	@Autowired
	private ICourseSessionService iCourseSessionService;
	@Autowired
	private IClientService iClientService;

	@RequestMapping(value = "/mysessioncourses", method = RequestMethod.GET)
	public String enrollCourseSession(ModelMap modelMap, @RequestParam(required = false) Integer isDeleted,
			@RequestParam(required = false) String title, @RequestParam(required = false) int idClient,
			@RequestParam(required = false) String firstName) 
	{

		if ((isDeleted != null) & (title != null)) 
		{
			modelMap.addAttribute("isDeleted", isDeleted);
			modelMap.addAttribute("title", title);
		}
		Client clientToFind = iClientService.findByIdClient(idClient);

		List<CourseSession> listOfEnrolledCourseSession = clientToFind.getListCourseSessions();

		modelMap.addAttribute("listOfEnrolledCourseSession", listOfEnrolledCourseSession);

		modelMap.addAttribute("idClient", idClient);
		modelMap.addAttribute("firstName", firstName);
		return "MySessionCourses";
	}

	@RequestMapping(value = "/deleteTransaction", method = RequestMethod.GET)
	public String deleteTransaction(ModelMap modelMap, String typeTransaction,
			@RequestParam(required = true) int idCourseSession, int idClient)
	{
		Integer isDeleted = 0;
		String title = "";
		String firstName = "";
		if (typeTransaction.equals("delete")) 
		{
			CourseSession courseSessionToDelete = iCourseSessionService.findByIdCourseSession(idCourseSession);
			Client clientToFind = iClientService.findByIdClient(idClient);
			firstName = clientToFind.getFirstName();
			title = courseSessionToDelete.getCourse().getTitle();
			isDeleted = iClientService.deleteEnroll(courseSessionToDelete, clientToFind);
		}
		modelMap.addAttribute("firstName", firstName);
		return "redirect:/mysessioncourses?isDeleted=" + isDeleted + "&title=" + title + "&idClient=" + idClient
				+ "&firstName=" + firstName;
	}

}

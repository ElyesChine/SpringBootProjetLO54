package fr.utbm.lo54.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

import fr.utbm.lo54.core.Client;
import fr.utbm.lo54.service.IClientService;

@Controller
public class ClientController
{
	@Autowired
	private IClientService iClientService;

	public Client setUpClientForm()
	{
		return new Client();
	}

	/*    http://localhost:9080/    */
	@RequestMapping("/")
	public String welcome(HttpSession s,ModelMap modelMap,@RequestParam(required=false) Integer disconnect)
	{
		if(s.getAttribute("clientId")!= null)
			return "redirect:/ManageCourses";
		if(disconnect != null){
			System.out.println("here");
			s.removeAttribute("clientId");
		}
		return "SignupPage";
	}

	/*    http://localhost:9080/disconnect    */
	@RequestMapping("/disconnect")
	public String welcome(HttpSession session,ModelMap modelMap)
	{
		System.out.println("here");
		session.removeAttribute("clientId");
		return "SignupPage";
	}


	/*    http://localhost:9080/connect    */
	@RequestMapping(value="/connect",method=RequestMethod.GET)
	public String connect()
	{
		return "SignupPage";
	}
	@RequestMapping(value="/connect",method=RequestMethod.POST)
	public String connect(HttpSession session,ModelMap modelMap,
	@RequestParam String email,@RequestParam String password)
	{
		Client client = iClientService.getClientByEmailAndPassword(email,password);
		System.out.println("email:"+email+",password:"+password+"client"+client);
		if(client==null){
			modelMap.addAttribute("AccountDoesNotExist","Pas de compte correspondant");
			return "SignupPage";
		}
		//ajouter le client à la session
		session.setAttribute("clientId",client.getId());
		return "redirect:/ManageCourses";
	}

	/*    http://localhost:9080/signup    */
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String register(@RequestParam(required = false) Integer AccountExists, ModelMap modelMap)
	{
		if (AccountExists != null)
		{
			modelMap.addAttribute("AccountExists", AccountExists);
		}
		return "SignupPage";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpSession session,@RequestParam String lastName, @RequestParam String firstName, @RequestParam String phone,
			@RequestParam String adresse, @RequestParam String email, @RequestParam String password)
	{
		Integer AccountExists = 0;
		Client client = iClientService.getClientByEmail(email);

		if (client != null) // if client déja exists
		{
			AccountExists = 1;
			return "redirect:/signup?AccountExists=" + AccountExists;
		}
		else // else new client
		{
			client = new Client(firstName, lastName, adresse, phone, email, password);
			client = iClientService.create(client);
			session.setAttribute("clientId",client.getId());
		}
		return "redirect:/ManageCourses";
	}
}

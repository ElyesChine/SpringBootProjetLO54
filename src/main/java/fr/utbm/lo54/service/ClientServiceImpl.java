package fr.utbm.lo54.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.utbm.lo54.core.Client;
import fr.utbm.lo54.core.CourseSession;
import fr.utbm.lo54.dao.ClientRepository;

@Service
@Transactional
public class ClientServiceImpl implements IClientService {

	@Autowired
	ClientRepository clientRepository;

	@Override
	public Client create(Client client)
	{
		Client myClient = null;
		if (client != null)
		{
			myClient = clientRepository.save(client);
		}
		return myClient;
	}

	@Override
	public Client update(Client client)
	{
		Client myClient = null;
		if (client != null)
		{
			myClient = clientRepository.save(client);
		}
		return myClient;
	}

	@Override
	public Boolean delete(Client client)
	{
		Boolean isDeleted = false;
		if (client != null) {
			clientRepository.delete(client);
			isDeleted = true;
		}
		return isDeleted;
	}

	public static Comparator<Client> ClientNameComparator = new Comparator<Client>()
	{
		public int compare(Client c1, Client c2)
		{
		   String ClientName1 = c1.getFirstName().toUpperCase();
		   String ClientName2 = c2.getFirstName().toUpperCase();

		   //ascending order
		   return ClientName1.compareTo(ClientName2);

		   //descending order
		   //return ClientName2.compareTo(ClientName1);
	    }
	};

	@Override
	public ArrayList<Client> findAll()
	{
		ArrayList<Client> listClient = (ArrayList<Client>) clientRepository.findAll();
		if (listClient == null)
		{
			throw new RuntimeException("Clients list not found!");
		}
		Collections.sort(listClient, ClientNameComparator);
		return listClient;
	}

	@Override
	public Client findByIdClient(int idClient)
	{
		Client myClient = null;
		if (idClient > 0)
		{
			myClient = clientRepository.findByIdClient(idClient);
		}
		return myClient;
	}

	public Client getClientByEmailAndPassword(String email,String password){
		return clientRepository.findByEmailAndPassword(email,password);
	}

	@Override
	public Integer saveEnroll(CourseSession courseSession, Client client, int size)
	{
		Integer isEnrolled = 0;

		if ((courseSession != null) & (client != null))
		{
			// Check if user is already enrolled
			if (!(client.getListCourseSessions().contains(courseSession)))
			{
				// if not enrolled, check if current size CourseSession is < then max Size
				if (size < courseSession.getMax())
				{
					client.getListCourseSessions().add(courseSession);
					courseSession.getListClients().add(client);
					isEnrolled = 1;
					System.out.println("************* ENROLLED *************");
				}
				else
				{
					System.out.println("You cannot enroll this session because it is full!");
					isEnrolled = 2;
				}

			}
			else
			{
				System.out.println("************* Already ENROLLED *************");
				isEnrolled = 3;
			}
			clientRepository.save(client);
		}
		return isEnrolled;
	}

	@Override
	public Integer deleteEnroll(CourseSession courseSession, Client client)
	{

		Integer isDeleted = 0;

		if ((courseSession != null) & (client != null))
		{
			if (client.getListCourseSessions().contains(courseSession))
			{
				client.getListCourseSessions().remove(courseSession);
				isDeleted = 1;
				System.out.println("*************DELETED SESSION FROM LIST SESSION OF CLIENT*************");
			}
			else
			{
				System.out.println("************* Something Wrong *************");
			}
			clientRepository.save(client);
		}
		return isDeleted;
	}

	@Override
	public Client getClientByEmail(String email)
	{
		Client client = clientRepository.getClientByEmail("%" + email + "%");
		if (client == null)
		{
			return null;
		}
		return client;
	}
}

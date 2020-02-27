package fr.utbm.lo54.service;

import java.util.ArrayList;
import fr.utbm.lo54.core.Client;
import fr.utbm.lo54.core.CourseSession;

public interface IClientService
{
	public Client create(Client client);

	public Client update(Client client);

	public Boolean delete(Client client);

	public Integer saveEnroll(CourseSession courseSession, Client client, int size);

	public Integer deleteEnroll(CourseSession courseSession, Client client);

	public Client getClientByEmailAndPassword(String email, String password);

	public Client findByIdClient(int idClient);

	public ArrayList<Client> findAll();

	public Client getClientByEmail(String email);
}

/*
 *
 * @authors "Hassan HAJJAR" and "Linda JEMNI"
 *
*/
package fr.utbm.lo54.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.utbm.lo54.core.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>
{
	@Query(value = "SELECT c.client_id, c.address, c.email, c.first_name, c.last_name, " + "c.password, c.phone "
			+ "FROM Client c WHERE c.client_id = :idClient ", nativeQuery = true)
	public Client findByIdClient(@Param("idClient") int idClient);

	@Query(value = "SELECT c.client_id, c.address, c.email, c.first_name, c.last_name, " + "c.password, c.phone "
			+ "FROM Client c WHERE LOWER(c.email) LIKE LOWER(:email)", nativeQuery = true)
	public Client getClientByEmail(@Param("email") String email);
	public Client findByEmailAndPassword(String email,String password);
}

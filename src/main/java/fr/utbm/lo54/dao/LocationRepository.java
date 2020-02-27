/*
 * 
 * @authors "Hassan HAJJAR" and "Linda JEMNI"
 * 
*/
package fr.utbm.lo54.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.utbm.lo54.core.Location;

public interface LocationRepository  extends JpaRepository<Location, Integer>{

}

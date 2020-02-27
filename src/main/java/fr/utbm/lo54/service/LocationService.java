package fr.utbm.lo54.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.utbm.lo54.core.Client;
import fr.utbm.lo54.core.Location;
import fr.utbm.lo54.dao.LocationRepository;

@Service
@Transactional
public class LocationService implements ILocationService 
{
	@Autowired
	LocationRepository locationRepository;

	@Override
	public Location create(Location location)
	{
		Location myLocation = null;
		if (location != null)
		{
			myLocation = locationRepository.save(location);
		}
		return myLocation;
	}

	@Override
	public Location update(Location location)
	{
		Location myLocation = null;
		if (location != null)
		{
			myLocation = locationRepository.save(location);
		}
		return myLocation;
	}

	@Override
	public Boolean delete(Location location) 
	{
		Boolean isDeleted = false;
		if (location != null)
		{
			locationRepository.delete(location);
			isDeleted = true;
		}
		return isDeleted;
	}

	public static Comparator<Location> LocationNameComparator = new Comparator<Location>() 
	{
		public int compare(Location l1, Location l2) 
		{
		   String Location1 = l1.getCity().toUpperCase();
		   String Location2 = l2.getCity().toUpperCase();

		   //ascending order
		   return Location1.compareTo(Location2);
	    }
	};
	
	@Override
	public ArrayList<Location> findAll() 
	{
		ArrayList<Location> listLocation = (ArrayList<Location>) locationRepository.findAll();
		if (listLocation == null)
		{
			throw new RuntimeException("Locations list not found!");
		}
		Collections.sort(listLocation, LocationNameComparator);
		return listLocation;
	}

	@Override
	public Optional<Location> findById(int id)
	{
		Optional<Location> myLocation = null;
		if (id > 0) 
		{
			myLocation = locationRepository.findById(id);
		}
		return myLocation;
	}
}
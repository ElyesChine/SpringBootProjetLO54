package fr.utbm.lo54.service;

import java.util.ArrayList;
import java.util.Optional;

import fr.utbm.lo54.core.Location;

public interface ILocationService 
{
	public Location create(Location location);

	public Location update(Location location);

	public Boolean delete(Location location);

	public ArrayList<Location> findAll();

	public Optional<Location> findById(int id);
}

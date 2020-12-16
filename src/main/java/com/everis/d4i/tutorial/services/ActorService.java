package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface ActorService {
	
	List<ActorRest> getActors() throws NetflixException;
	
	ActorRest getActorById(Long id) throws NetflixException;
	
	ActorRest createActor(ActorRest actorRest) throws NetflixException;
	
	ActorRest updateActor(ActorRest actorRest, Long id) throws NetflixException;

	ActorRest deleteActor(Long id) throws NetflixException;

}

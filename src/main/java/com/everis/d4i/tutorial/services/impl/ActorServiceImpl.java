package com.everis.d4i.tutorial.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ActorServiceImpl implements ActorService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActorServiceImpl.class);

	@Autowired
	private ActorRepository actorRepository;

	private ModelMapper modelMapper = new ModelMapper();

	public List<ActorRest> getActors() throws NetflixException {
		return actorRepository.findAll().stream()
				.map(actor -> modelMapper.map(actor, ActorRest.class))
				.collect(Collectors.toList());

	}
	
	@Override
	public ActorRest getActorById(Long id) throws NetflixException {

		try {
			return modelMapper.map(actorRepository.getOne(id), ActorRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}
	
	@Override
	public ActorRest createActor(final ActorRest actorRest) throws NetflixException {
		Actor actor = new Actor();
		actor.setName(actorRest.getName());
		actor.setId(actorRest.getId());
		try {
			actor = actorRepository.save(actor);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(actor, ActorRest.class);
	}
	
	@Override
	public ActorRest updateActor(final ActorRest actorRest, Long id) throws NetflixException {
		Actor actor = new Actor();
		actor.setName(actorRest.getName());
		actor.setId(actorRest.getId());
		try {
			actor = actorRepository.save(actor);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(actor, ActorRest.class);
	}

	@Override
	public ActorRest deleteActor(Long id) throws NetflixException {

		try {
			ActorRest actor = modelMapper.map(actorRepository.getOne(id), ActorRest.class);
			actorRepository.deleteById(id);
			return actor;
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}
	
	
	

}

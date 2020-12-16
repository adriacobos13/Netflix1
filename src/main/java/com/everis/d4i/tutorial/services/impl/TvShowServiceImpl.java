package com.everis.d4i.tutorial.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TvShowServiceImpl.class);

	@Override
    public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {

        return tvShowRepository.findByCategoriesId(categoryId).stream()
                .map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());

    }

	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {

		try {
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}
	
	@Override
	public TvShowRest createTvShow(final TvShowRest tvShowRest) throws NetflixException {
		TvShow tvShow = new TvShow();
		tvShow.setName(tvShowRest.getName());
		tvShow.setId(tvShowRest.getId());
		tvShow.setYear(tvShowRest.getYear());
		tvShow.setShortDescription(tvShowRest.getShortDescription());
		tvShow.setLongDescription(tvShowRest.getLongDescription());
		tvShow.setRecommendedAge(tvShowRest.getRecommendedAge());
		tvShow.setAdvertising(tvShowRest.getAdvertising());
		List<CategoryRest> restCategories =  tvShowRest.getCategories();
		List<Category> categories = new ArrayList<>();
		for (CategoryRest category:restCategories) {
			categories.add(modelMapper.map(category, Category.class));
		}
		tvShow.setCategories(categories);
		try {
			tvShow = tvShowRepository.save(tvShow);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(tvShow, TvShowRest.class);
	}
	
	@Override
	public TvShowRest updateTvShow(final TvShowRest tvShowRest, Long tvShowId) throws NetflixException {
		TvShow tvShow = new TvShow();
		tvShow.setName(tvShowRest.getName());
		tvShow.setId(tvShowRest.getId());
		tvShow.setYear(tvShowRest.getYear());
		tvShow.setShortDescription(tvShowRest.getShortDescription());
		tvShow.setLongDescription(tvShowRest.getLongDescription());
		tvShow.setRecommendedAge(tvShowRest.getRecommendedAge());
		tvShow.setAdvertising(tvShowRest.getAdvertising());
		List<CategoryRest> restCategories =  tvShowRest.getCategories();
		List<Category> categories = new ArrayList<>();
		for (CategoryRest category:restCategories) {
			categories.add(modelMapper.map(category, Category.class));
		}
		tvShow.setCategories(categories);
		try {
			tvShow = tvShowRepository.save(tvShow);
		} catch (final Exception e) {
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(tvShow, TvShowRest.class);
	}

	@Override
	public TvShowRest deleteTvShow(Long id) throws NetflixException {

		try {
			TvShowRest tvShow = modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
			tvShowRepository.deleteById(id);
			return tvShow;
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}

	}
}

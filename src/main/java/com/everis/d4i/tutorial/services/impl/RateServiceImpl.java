package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.Film;
import com.everis.d4i.tutorial.json.RateRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.repositories.SeasonRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.RateService;

@Service
public final class RateServiceImpl implements RateService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private TvShowRepository tvShowRepository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public RateRest getRateByTvShow(Long tvShowId) throws NetflixException {
		RateRest rate= new RateRest();
		rate.setId(tvShowId);
		String name=tvShowRepository.getOne(tvShowId).getName();
		String url=String.format("http://localhost:8181/film-info/film/%s", name);
		
		Object film= restTemplate.getForObject(url, Object.class);
		
		rate.setRate(modelMapper.map(film, Film.class).getRate());
		return rate;
		
	}


}

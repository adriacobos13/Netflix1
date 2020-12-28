package com.everis.d4i.tutorial.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.repositories.AwardRepository;
import com.everis.d4i.tutorial.repositories.SeasonRepository;
import com.everis.d4i.tutorial.services.AwardService;

@Service
public class AwardServiceImpl implements AwardService {
	@Autowired
	private AwardRepository awardRepository;

	private ModelMapper modelMapper = new ModelMapper();
	@Override
	public List<AwardRest> getAwardsByTvShow(Long tvShowId) throws NetflixException {
		return awardRepository.findByTvShowId(tvShowId).stream()
				.map(season -> modelMapper.map(season, AwardRest.class)).collect(Collectors.toList());
	}

}

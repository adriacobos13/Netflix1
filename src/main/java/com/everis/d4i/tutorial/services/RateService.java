package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.RateRest;
import com.everis.d4i.tutorial.json.SeasonRest;

public interface RateService{
	
	RateRest getRateByTvShow(Long tvShowId) throws NetflixException;

}

package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.RateRest;
import com.everis.d4i.tutorial.json.SeasonRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface RateController {
	
	NetflixResponse<RateRest> getRateByTvShow(Long tvShowId) throws NetflixException;

}

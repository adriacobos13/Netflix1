package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.json.SeasonRest;

public interface AwardService  {
	
	List<AwardRest> getAwardsByTvShow(Long tvShowId) throws NetflixException;
	

}

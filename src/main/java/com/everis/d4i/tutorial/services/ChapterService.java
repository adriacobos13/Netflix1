package com.everis.d4i.tutorial.services;

import java.util.List;

import javax.validation.Valid;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface ChapterService {

	List<ChapterRest> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber) throws NetflixException;

	ChapterRest getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException;
	
	public ChapterRest createChapter(ChapterRest chapterRest,Long tvShowId, short seasonNumber) throws NetflixException;

	public ChapterRest updateChapter(ChapterRest chapterRest, Long id) throws NetflixException;
	
	List<ChapterRest> getChaptersByActorId(Long ActorId) throws NetflixException;
	

}

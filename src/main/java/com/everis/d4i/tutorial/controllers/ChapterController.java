package com.everis.d4i.tutorial.controllers;

import java.util.List;

import javax.validation.Valid;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface ChapterController {

	NetflixResponse<List<ChapterRest>> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber)
			throws NetflixException;

	NetflixResponse<ChapterRest> getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException;
	
	NetflixResponse<ChapterRest> createChapter(Long tvShowId, short seasonNumber, ChapterRest chapterRest) throws NetflixException;

	NetflixResponse<ChapterRest> updateChapter(ChapterRest chapterRest, Long id) throws NetflixException;

	NetflixResponse<List<ChapterRest>> getChaptersByActorId(Long actorId) throws NetflixException;
}

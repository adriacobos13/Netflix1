package com.everis.d4i.tutorial.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.InternalServerErrorException;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import com.everis.d4i.tutorial.repositories.SeasonRepository;
import com.everis.d4i.tutorial.services.ChapterService;
import com.everis.d4i.tutorial.utils.constants.ExceptionConstants;

@Service
public class ChapterServiceImpl implements ChapterService {

	@Autowired
	private ChapterRepository chapterRepository;
	@Autowired
	private SeasonRepository seasonRepository;

	private ModelMapper modelMapper = new ModelMapper();
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ChapterServiceImpl.class);

	@Override
	public List<ChapterRest> getChaptersByTvShowIdAndSeasonNumber(Long tvShowId, short seasonNumber)
			throws NetflixException {
		return chapterRepository.findBySeasonTvShowIdAndSeasonNumber(tvShowId, seasonNumber).stream()
				.map(chapter -> modelMapper.map(chapter, ChapterRest.class)).collect(Collectors.toList());
	}

	@Override
	public ChapterRest getChapterByTvShowIdAndSeasonNumberAndChapterNumber(Long tvShowId, short seasonNumber,
			short chapterNumber) throws NetflixException {
		Chapter chapter = chapterRepository
				.findBySeasonTvShowIdAndSeasonNumberAndNumber(tvShowId, seasonNumber, chapterNumber)
				.orElseThrow(() -> new NotFoundException(ExceptionConstants.MESSAGE_INEXISTENT_CHAPTER));
		return modelMapper.map(chapter, ChapterRest.class);
	}
	
	@Override
	public ChapterRest createChapter(ChapterRest chapterRest,Long tvShowId, short seasonNumber ) throws NetflixException {
		Chapter chapter = new Chapter();
		chapter.setName(chapterRest.getName());
		chapter.setDuration(chapterRest.getDuration());
		chapter.setId(chapterRest.getId());
		chapter.setNumber(chapterRest.getNumber());
		Optional<Season> season= seasonRepository.findByTvShowIdAndNumber(tvShowId,seasonNumber);
		chapter.setSeason(season.get());
		List<ActorRest> actorsRest=chapterRest.getActors();
		List<Actor> actors=new ArrayList<>();
		for (ActorRest actorRest:actorsRest) {
			actors.add(modelMapper.map(actorRest, Actor.class));
		}
		chapter.setActors(actors);
		try {
			chapter = chapterRepository.save(chapter);
		} catch (final Exception e) { 
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(chapter, ChapterRest.class);
	}

	public ChapterRest updateChapter(ChapterRest chapterRest, Long id ) throws NetflixException {
		Optional<Chapter> savedChapter=chapterRepository.findById(id);
		Chapter chapter = new Chapter();
		chapter.setSeason(savedChapter.get().getSeason());
		chapter.setName(chapterRest.getName());
		chapter.setDuration(chapterRest.getDuration());
		chapter.setId(chapterRest.getId());
		chapter.setNumber(chapterRest.getNumber());	
		List<ActorRest> actorsRest=chapterRest.getActors();
		List<Actor> actors=new ArrayList<>();
		for (ActorRest actorRest:actorsRest) {
			actors.add(modelMapper.map(actorRest, Actor.class));
		}
		chapter.setActors(actors);
		try {
			chapter = chapterRepository.save(chapter);
		} catch (final Exception e) { 
			LOGGER.error(ExceptionConstants.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(ExceptionConstants.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(chapter, ChapterRest.class);
	}
	
	@Override
	public List<ChapterRest> getChaptersByActorId(Long actorId)
			throws NetflixException {
		return chapterRepository.findByActorsId(actorId).stream()
				.map(chapter -> modelMapper.map(chapter, ChapterRest.class)).collect(Collectors.toList());
	}
	
	
		
	
}

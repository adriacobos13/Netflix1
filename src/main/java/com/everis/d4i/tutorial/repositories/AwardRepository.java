package com.everis.d4i.tutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.entities.Season;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {
	List<Award> findByTvShowId(Long tvShowId);

}

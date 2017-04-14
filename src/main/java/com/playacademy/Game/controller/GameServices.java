package com.playacademy.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.playacademy.game.model.Game;
import com.playacademy.game.model.GameRepository;

@Service
public class GameServices implements IGameServices{
	
	@Autowired
	GameRepository repository;

	@Override
	public boolean addGame(Game game) {
		if(repository.save(game) != null)
			return true;
		else return false;
		
	}

	@Override
	public Game getGameByID(long id) {
		return repository.findByGameId(id);
	}

	@Override
	public List<Game> getAllGamesInCourse(long courseId) {
		return repository.findByCourseId(courseId);
	}
	
	

}

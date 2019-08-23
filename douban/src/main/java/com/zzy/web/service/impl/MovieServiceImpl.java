package com.zzy.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zzy.web.entity.Movie;
import com.zzy.web.entity.RecommendMovie;
import com.zzy.web.mapper.MovieDao;
import com.zzy.web.service.MovieService;
@Service("movieService")
public class MovieServiceImpl implements MovieService {

	@Autowired
	@Qualifier("movieDao")
	private MovieDao movieDao;
	
	@Override
	public List<Movie> findMovieByName(String name) {
		List<Movie> list = movieDao.findMovieByName(name);
		return list;
	}

	@Override
	public Movie findMovieById(String id) {
		return movieDao.findMovieById(id);
	}

	@Override
	public RecommendMovie findById(String id) {
		return movieDao.findById(id);
	}
}

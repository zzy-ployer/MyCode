package com.zzy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzy.web.entity.Movie;
import com.zzy.web.entity.RecommendMovie;
import com.zzy.web.service.MovieService;


@Controller
@RequestMapping("movie")
public class MovieController {

	@Autowired
	@Qualifier("movieService")
	private MovieService movieService;

	@RequestMapping("/findMovieByName")
	public String findMovieByName(String movieName, HttpServletRequest request) {

		List<Movie> list = movieService.findMovieByName(movieName);

		request.setAttribute("movies", list);

		return "selectByName.jsp";
	}

	@RequestMapping("/movieDetail")
	public String movieDetail(String id, HttpServletRequest request) {

		Movie movie = movieService.findMovieById(id);
		RecommendMovie reMovie = movieService.findById(id); // ∞¥id≤È’“

		String reMoviesId = reMovie.getRecommend_movie_id();
		String[] reMoviesIds = reMoviesId.split(",");
		List<Movie> recommendMovies = new ArrayList<>();
		for (String s : reMoviesIds) {
			recommendMovies.add(movieService.findMovieById(s));
		}

		request.setAttribute("reMovies", recommendMovies);
		request.setAttribute("movie", movie);
		return "movieDetail.jsp";
	}
}

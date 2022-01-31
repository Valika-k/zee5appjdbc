package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;

import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
public interface MovieService {
	public String addMovie(Movie movie);
	public String updateMovie(String id, Movie movie) throws IdNotFoundException;
	public Optional<Movie> getMovieById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public Movie[] getAllMovies() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
	public String deleteMovieById(String id) throws IdNotFoundException;
	public List<Movie> getAllMovieDetails();
	Optional<List<Movie>> getAllMoviesList() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
}

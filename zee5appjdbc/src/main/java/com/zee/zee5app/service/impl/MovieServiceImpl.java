package com.zee.zee5app.service.impl;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.dto.Register;
import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.repository.impl.MovieRepositoryImpl;
import com.zee.zee5app.service.MovieService;
import com.zee.zee5app.service.UserService;
import com.zee.zee5app.service.MovieService;

public class MovieServiceImpl implements MovieService{
	
	private MovieServiceImpl()  throws IOException {
		// TODO Auto-generated constructor stub
	}
	

	private static MovieService service;
	
	public static MovieService getInstance() throws IOException {
		
		if(service==null) {
			service = new MovieServiceImpl();
		}
		
		return service;
	}
	
	//MovieRepository MovieRepository ;

	MovieRepository movieRepository  =  MovieRepositoryImpl.getInstance();
	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return movieRepository.addMovie(movie);
	}

	@Override
	public String updateMovie(String id, Movie movie) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return movieRepository.updateMovie( id, movie);
	}

	@Override
	public Optional<Movie> getMovieById(String id)  throws IdNotFoundException, InvalidIdLengthException, InvalidNameException{
		// TODO Auto-generated method stub
		return movieRepository.getMovieById(id);
	}

	@Override
	public Movie[] getAllMovies() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException{
		return this.movieRepository.getAllMovies();
	}

	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return movieRepository.deleteMovieById(id);
	}
	
	@Override
	public Optional<List<Movie>> getAllMoviesList() throws InvalidNameException, IdNotFoundException,
			InvalidIdLengthException

	{
		return this.movieRepository.getAllMoviesList();
	}
//	@Override
//	public List<Movie> getAllMovieDetails() {
//		// TODO Auto-generated method stub
//		return movieRepository.getAllMovieDetails();
//	}

	@Override
	public List<Movie> getAllMovieDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

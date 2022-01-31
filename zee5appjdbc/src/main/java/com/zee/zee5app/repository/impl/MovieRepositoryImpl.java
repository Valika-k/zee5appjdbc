package com.zee.zee5app.repository.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;



import com.zee.zee5app.dto.Movie;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.MovieRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;
public class MovieRepositoryImpl implements MovieRepository{
	private Set<Movie> set = new LinkedHashSet<>();
	DBUtils dbUtils = DBUtils.getInstance();
	private static MovieRepository repository;

	private MovieRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
		//loginRepository = LoginRepositoryImpl.getInstance();
		dbUtils = DBUtils.getInstance();
	}
	
	public static MovieRepository getInstance() throws IOException {
		if(repository==null) {
			repository = new MovieRepositoryImpl();
		}
		return repository;
	}

	@Override
	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub

		Connection connection= null;
		PreparedStatement preparedStatement = null;

		String insertStatement = "insert into movies"
				+ " (movieId,name,ageLimit,cast,genre,length,trailer,releasedate, language)"
				+ " values(?,?,?,?,?,?,?,?,?)";

		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1,movie.getMovieId());
			preparedStatement.setString(2, movie.getName());
			preparedStatement.setInt(3, movie.getAgeLimit());
			preparedStatement.setString(4, movie.getCast());
			preparedStatement.setString(5, movie.getGenre());
			preparedStatement.setString(6, movie.getLength());
			preparedStatement.setBlob(7, movie.getTrailer());
			preparedStatement.setString(8, movie.getReleasedate());
			preparedStatement.setString(9, movie.getLanguage());
			
			//preparedStatement.setString(6, movie.getPassword());
			
			int result =preparedStatement.executeUpdate();

			
			if (result > 0) {
					connection.commit();
					return "success";
				} else {
					connection.rollback();
					return "fail";
				}
			

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}

	@Override 
	public String updateMovie(String id, Movie movie) throws IdNotFoundException{
//		// TODO Auto-generated method stub
		Optional<Movie> optional=this.getMovieById(id);
		if(optional.isPresent()) {
			boolean result=set.remove(optional.get());
			set.add(movie);
			if(result) {
				return "success";
			}
			else 
				return "fail";
		}
		
		return "fail";
	}

	@Override
	public Optional<Movie> getMovieById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String selectStatement = "select * from movies where movieId=?";
		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);

			
			resultSet =  preparedStatement.executeQuery();

			if (resultSet.next()) {

				Movie movie = new Movie();
				movie.setMovieId(resultSet.getString("movieId"));
				movie.setName(resultSet.getString("name"));
				movie.setAgeLimit(resultSet.getInt("ageLimit"));
				movie.setCast(resultSet.getString("cast"));
				movie.setGenre(resultSet.getString("genre"));
				movie.setLength(resultSet.getString("length"));
				movie.setTrailer(resultSet.getBlob("trailer"));
				movie.setReleasedate(resultSet.getString("releasedate"));
				movie.setLanguage(resultSet.getString("language"));
				return Optional.of(movie);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			dbUtils.closeConnection(connection);
		}
		
		return Optional.empty();
		
	}

	@Override
	public Movie[] getAllMovies() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException{

		Optional<List<Movie>> movies = this.getAllMoviesList();

		if (movies.isPresent()) {
	
			return movies.get().toArray(new Movie[movies.get().size()]);
		}
		
		return null;
	}

	@Override
	public String deleteMovieById(String id) throws IdNotFoundException {
		Connection connection = dbUtils.getConnection();
		String delQuery = "DELETE FROM movies where movieId=?";
		try {
			PreparedStatement prepStatement = connection.prepareStatement(delQuery);
			prepStatement.setString(1, id);
			int result = prepStatement.executeUpdate();
			if (result > 0) {
				connection.commit();
				return "success";
			} else {
				connection.rollback();
				throw new IdNotFoundException("Invalid Id");
			}

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbUtils.closeConnection(connection);
		}
		return "fail";
	}

	@Override
	public Optional<List<Movie>> getAllMoviesList() throws InvalidNameException, IdNotFoundException,
	InvalidIdLengthException {

		{
			List<Movie> movies = new ArrayList<>();
			Connection connection = dbUtils.getConnection();

			String getQuery = "SELECT * FROM movies";
			try {
				PreparedStatement prepStatement = connection.prepareStatement(getQuery);
				ResultSet result = prepStatement.executeQuery();

				while (result.next()) {
					Movie movie = new Movie();
					
					movie.setMovieId(result.getString("movieId"));
					movie.setName(result.getString("name"));
					movie.setAgeLimit(result.getInt("ageLimit"));
					movie.setCast(result.getString("cast"));
					movie.setGenre(result.getString("genre"));
					movie.setLength(result.getString("length"));
					movie.setTrailer(result.getBlob("trailer"));
					movie.setReleasedate(result.getString("releasedate"));
					movie.setLanguage(result.getString("language"));
					movies.add(movie);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbUtils.closeConnection(connection);
			}

			return Optional.ofNullable(movies);
	}


	}
}


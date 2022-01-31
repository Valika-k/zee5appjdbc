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

import com.zee.zee5app.dto.Series;

import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;

import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.utils.DBUtils;
import com.zee.zee5app.utils.PasswordUtils;
public class SeriesRepositoryImpl implements SeriesRepository{
	private TreeSet<Series> set = new TreeSet<>();
	DBUtils dbUtils = DBUtils.getInstance();
	private static SeriesRepository repository;

	private SeriesRepositoryImpl() throws IOException{
		// TODO Auto-generated constructor stub
		//loginRepository = LoginRepositoryImpl.getInstance();
		dbUtils = DBUtils.getInstance();
	}

	public static SeriesRepository getInstance() throws IOException {
		if(repository==null) {
			repository = new SeriesRepositoryImpl();
		}
		return repository;
	}
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub

		Connection connection= null;
		PreparedStatement preparedStatement = null;

		String insertStatement = "insert into series"
				+ " (id,seriesName,ageLimit,cast,genre,length,trailer,releasedate, language, NoOfEpisodes)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";

		connection = dbUtils.getConnection();
		
		try {
			preparedStatement = connection.prepareStatement(insertStatement);
			preparedStatement.setString(1,series.getId());
			preparedStatement.setString(2, series.getSeriesName());
			preparedStatement.setInt(3, series.getAgeLimit());
			preparedStatement.setString(4, series.getCast());
			preparedStatement.setString(5, series.getGenre());
			preparedStatement.setString(6, series.getLength());
			preparedStatement.setBlob(7, series.getTrailer());
			preparedStatement.setString(8, series.getReleasedate());
			preparedStatement.setString(9, series.getLanguage());
			preparedStatement.setString(10, series.getNoOfEpisodes());
			
	
			
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
	public String updateSeries(String id, Series series) throws IdNotFoundException{
//		// TODO Auto-generated method stub
		Optional<Series> optional=this.getSeriesById(id);
		if(optional.isPresent()) {
			boolean result=set.remove(optional.get());
			set.add(series);
			if(result) {
				return "success";
			}
			else 
				return "fail";
		}
		
		return "fail";
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		Connection connection= null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String selectStatement = "select * from series where seriesId=?";
		
		connection = dbUtils.getConnection();
		try {
			preparedStatement = connection.prepareStatement(selectStatement);
			preparedStatement.setString(1, id);

			
			resultSet =  preparedStatement.executeQuery();

			if (resultSet.next()) {

				Series series = new Series();
				series.setId(resultSet.getString("id"));
				series.setSeriesName(resultSet.getString("seriesName"));
				series.setAgeLimit(resultSet.getInt("ageLimit"));
				series.setCast(resultSet.getString("cast"));
				series.setGenre(resultSet.getString("genre"));
				series.setLength(resultSet.getString("length"));
				series.setTrailer(resultSet.getBlob("trailer"));
				series.setReleasedate(resultSet.getString("releasedate"));
				series.setLanguage(resultSet.getString("language"));
				series.setNoOfEpisodes(resultSet.getString("NoOfEpisodes"));
				
				return Optional.of(series);
				
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
	public Series[] getAllSeries() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException{

		Optional<List<Series>> series = this.getAllSeriesList();

		if (series.isPresent()) {
	
			return series.get().toArray(new Series[series.get().size()]);
		}
		
		return null;
	}

	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		Connection connection = dbUtils.getConnection();
		String delQuery = "DELETE FROM series where id=?";
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
	public Optional<List<Series>> getAllSeriesList() throws InvalidNameException, IdNotFoundException,
	InvalidIdLengthException {

		{
			List<Series> shows = new ArrayList<>();
			Connection connection = dbUtils.getConnection();

			String getQuery = "SELECT * FROM series";
			try {
				PreparedStatement prepStatement = connection.prepareStatement(getQuery);
				ResultSet result = prepStatement.executeQuery();

				while (result.next()) {
					Series series = new Series();
					
					series.setId(result.getString("id"));
					series.setSeriesName(result.getString("seriesName"));
					series.setAgeLimit(result.getInt("ageLimit"));
					series.setCast(result.getString("cast"));
					series.setGenre(result.getString("genre"));
					series.setLength(result.getString("length"));
					series.setTrailer(result.getBlob("trailer"));
					series.setReleasedate(result.getString("releasedate"));
					series.setLanguage(result.getString("language"));
					series.setNoOfEpisodes(result.getString("NoOfEpisodes"));
					shows.add(series);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbUtils.closeConnection(connection);
			}

			return Optional.ofNullable(shows);
	}


	}

	@Override
	public List<Series> getAllSeriesDetails() {
		// TODO Auto-generated method stub
		return null;
	}
}

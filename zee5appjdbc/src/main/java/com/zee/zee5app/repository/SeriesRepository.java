package com.zee.zee5app.repository;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
public interface SeriesRepository {
	public String addSeries(Series movie);
	public String updateSeries(String id, Series movie) throws IdNotFoundException;
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException;
	public Series[] getAllSeries() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
	public List<Series> getAllSeriesDetails();
	public String deleteSeriesById(String id) throws IdNotFoundException;
	Optional<List<Series>> getAllSeriesList()
			throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;

}

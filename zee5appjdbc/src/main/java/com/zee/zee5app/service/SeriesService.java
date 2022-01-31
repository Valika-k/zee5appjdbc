package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;

import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
public interface SeriesService {
	public String addSeries(Series movie);
	public String updateSeries(String id, Series movie) throws IdNotFoundException;
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException, InvalidIdLengthException, InvalidNameException;
	public Series[] getAllSeries() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
	public String deleteSeriesById(String id) throws IdNotFoundException;
	public List<Series> getAllSeriesDetails();
	Optional<List<Series>> getAllSeriesList()
			throws InvalidNameException, IdNotFoundException, InvalidIdLengthException;
}

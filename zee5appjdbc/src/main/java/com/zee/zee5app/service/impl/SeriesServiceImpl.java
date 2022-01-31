package com.zee.zee5app.service.impl;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.Exception.IdNotFoundException;
import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;
import com.zee.zee5app.repository.SeriesRepository;

import com.zee.zee5app.repository.impl.SeriesRepositoryImpl;

import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.UserService;


public class SeriesServiceImpl implements SeriesService{
	
	private SeriesServiceImpl()  throws IOException {
		// TODO Auto-generated constructor stub
	}
	

	private static SeriesService service;
	
	public static SeriesService getInstance() throws IOException {
		
		if(service==null) {
			service = new SeriesServiceImpl();
		}
		
		return service;
	}
	
	//SeriesRepository SeriesRepository ;

	SeriesRepository seriesRepository  =  SeriesRepositoryImpl.getInstance();
	@Override
	public String addSeries(Series series) {
		// TODO Auto-generated method stub
		return seriesRepository.addSeries(series);
	}

	@Override
	public String updateSeries(String id, Series series) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return seriesRepository.updateSeries( id, series);
	}

	@Override
	public Optional<Series> getSeriesById(String id)  throws IdNotFoundException, InvalidIdLengthException, InvalidNameException{
		// TODO Auto-generated method stub
		return seriesRepository.getSeriesById(id);
	}

	@Override
	public Series[] getAllSeries() throws InvalidNameException, IdNotFoundException, InvalidIdLengthException{
		return this.seriesRepository.getAllSeries();
	}

	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return seriesRepository.deleteSeriesById(id);
	}
	
	@Override
	public Optional<List<Series>> getAllSeriesList() throws InvalidNameException, IdNotFoundException,
			InvalidIdLengthException

	{
		return this.seriesRepository.getAllSeriesList();
	}
//	@Override
//	public List<Series> getAllSeriesDetails() {
//		// TODO Auto-generated method stub
//		return seriesRepository.getAllSeriesDetails();
//	}

	@Override
	public List<Series> getAllSeriesDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

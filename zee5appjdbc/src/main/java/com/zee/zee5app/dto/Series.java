package com.zee.zee5app.dto;


import java.sql.Blob;

import lombok.Data;

@Data
public class Series {


	private String id;
	private String seriesName;
	private String cast;
	private int ageLimit;
	private String genre;
	private Blob trailer;
	private String releasedate;
	private String language;
	private String length;
	private String NoOfEpisodes;
		

}

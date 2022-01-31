package com.zee.zee5app.dto;


import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Movie {


		 
		private String movieId;
		private String name;
		private String cast;
		private int ageLimit;
		private String genre;
		private Blob trailer;
		private String releasedate;
		private String language;
		private String length;
		

}

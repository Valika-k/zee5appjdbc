package com.zee.zee5app.dto;

import java.util.Objects;

import com.zee.zee5app.Exception.InvalidIdLengthException;
import com.zee.zee5app.Exception.InvalidNameException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Setter
@Getter
@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//AllArgsConstructor
public class Movie {



	public Movie() {
		// TODO Auto-generated constructor stub
	}



	@Setter(value = AccessLevel.NONE)
	private String id;
	private String Name;
	private String releasedate;
	private String language;
	private String cast;
	private String length;


	public void setId(String id) throws InvalidIdLengthException {
		if(id.length()<0) {
			throw new InvalidIdLengthException
			("id length is lessthan or eq to 0");

		}
		this.id = id;
	}






}


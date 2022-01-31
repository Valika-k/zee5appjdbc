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

@Setter
@Getter
@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//AllArgsConstructor
public class Series implements Comparable<Series>{
	public Series(String id, String Name, String releasedate, String language, String cast, String length)
			throws InvalidIdLengthException, InvalidNameException {
		super();
		this.setId(id);
		this.setName(Name);
		this.releasedate=releasedate;
		this.language=language;
		this.cast=cast;
		this.length=length;
	}

	private void setName(String name2) {
		// TODO Auto-generated method stub
		
	}

	@Setter(value = AccessLevel.NONE)
	private String id;
	@Setter(value = AccessLevel.NONE)
	private String Name;
	@Setter(value = AccessLevel.NONE)
	private String releasedate;
	private String language;
	private String cast;
	private String length;

	public void setFirstName(String Name) throws InvalidNameException {
		
		if(Name==null || Name=="" ) {
			throw new InvalidNameException("Name is not valid");
		}
		this.Name = Name;
	}

	public void setId(String id) throws InvalidIdLengthException {
		if(id.length()<0) {
			throw new InvalidIdLengthException
			("id length is lessthan or eq to 0");

		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id,Name,releasedate,language,cast,length);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Series other = (Series) obj;
		return Objects.equals(id, other.id) && Objects.equals(Name, other.Name)
				&& Objects.equals(releasedate, other.releasedate) && Objects.equals(language, other.language)
				&& Objects.equals(cast, other.cast) && Objects.equals(length, other.length);
	}

	@Override
	public int compareTo(Series o) {
		// TODO Auto-generated method stub
		return o.id.compareTo(this.getId());
}
}

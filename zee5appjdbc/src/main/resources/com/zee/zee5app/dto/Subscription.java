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

public class Subscription implements Comparable<Subscription>{
	
	public Subscription(String id, String dateOfpurchase, String country, String paymentMode, String ExpiryDate)
			throws InvalidIdLengthException, InvalidNameException {
		super();
		this.setId(id);
		this.dateOfpurchase=dateOfpurchase;
		this.country=country;
		this.paymentMode = paymentMode;
		this.ExpiryDate = ExpiryDate;
	}
	
	@Setter(value = AccessLevel.NONE)
	private String id;
	@Setter(value = AccessLevel.NONE)
	private String dateOfpurchase;
	@Setter(value = AccessLevel.NONE)
	private String country;
	private String paymentMode;
	private String ExpiryDate;
	
	



	public void setId(String id) throws InvalidIdLengthException {
		if(id.length()<0) {
			throw new InvalidIdLengthException
			("id length is lessthan or eq to 0");

		}
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id,dateOfpurchase, country, paymentMode, ExpiryDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		return Objects.equals(id, other.id) && Objects.equals(dateOfpurchase, other.dateOfpurchase)
				&& Objects.equals(country, other.country) && Objects.equals(paymentMode, other.paymentMode)
				&& Objects.equals(ExpiryDate, other.ExpiryDate);
	}

	@Override
	public int compareTo(Subscription o) {
		// TODO Auto-generated method stub
		return o.id.compareTo(this.getId());
		
		
}

	public String getId() {
		// TODO Auto-generated method stub
		return this.getId();
	}
}
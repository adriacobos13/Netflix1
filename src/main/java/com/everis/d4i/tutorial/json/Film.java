package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.util.Objects;

public class Film implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8180946360395320937L;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Double rate;

	public Film(String name, String shortDescription, String longDescription, Double rate) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.rate = rate;
	}
	
	

	public Film() {
		super();
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Film)) {
			return false;
		}
		Film other = (Film) obj;
		return Objects.equals(other.name, this.name);
	}

	@Override
	public String toString() {
		return Objects.toString(this);
	}

}

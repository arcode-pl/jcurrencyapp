package com.example.jcurrencyapp.model.db;

//@Entity
//@Table(name = "country")
public class Country {

	private int countryId;
	private String countryName;
	
	public Country(String countryName) {
		this.countryName = countryName;
	}
	
//	@Id
//	@Column(name = "country_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int id) {
		this.countryId = id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String name) {
		this.countryName = name;
	}

}

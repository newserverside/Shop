package com.shop;

public class Product {

	private String shortName;
	private String shortDescription;

	public Product(String shortName, String shortDescription) {
		this.shortName = shortName;
		this.shortDescription = shortDescription;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public void buyBtn() {
		// send to cart
	}

}

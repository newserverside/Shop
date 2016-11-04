package com.shop;

public class Cart {

	private String shortName;
	private String price;
	private String quantity;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void deleteBtn() {
		// delete product from cart
	}

	public void chekoutBtn() {
		// send to checkout
	}
	
	
	public void increaseQuantity(){
		// increase product quantity by 1
	}

	
	public void decreaseQuantity(){
		// decrease product quantity by 1
		
	}
	
	public void shortNameBtn(){
		// send to product
	}
}

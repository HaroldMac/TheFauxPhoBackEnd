package com.thefauxpho.The.Faux.Pho.model;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class CustomerOrder {
	
	@Id @GeneratedValue
	private long id;
	private String orderNumber, customerName, customerEmail;
	private Date orderTime;
	
	@ManyToMany
	@JoinTable(
		name = "customerOrder_food",
		joinColumns = @JoinColumn(name = "custoemrOrder_id"),
		inverseJoinColumns = @JoinColumn(name = "food_id"))
	private List<Food> foods;
	
	public CustomerOrder() {}
	
	public CustomerOrder(String customerName, String customerEmail) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.orderNumber = this.generateOrderNumber();
	}

	public CustomerOrder(String customerName, String customerEmail, List<Food> foods) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.orderNumber = this.generateOrderNumber();
		this.foods = foods;
	}
	
	
	public CustomerOrder(String customerName, String customerEmail, Date orderTime) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.orderTime = orderTime;
		this.orderNumber = this.generateOrderNumber();
	}

	public CustomerOrder(String customerName, String customerEmail, Date orderTime, List<Food> foods) {
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.orderTime = orderTime;
		this.foods = foods;
		this.orderNumber = this.generateOrderNumber();
	}
	

	public String getCustomerName() {
		return customerName;
	}


	public void SetCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public List<Food> getFoods() {
		return foods;
	}
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	
	private String generateOrderNumber() {
		Random rand = new Random();
		int firstTwoNumber = rand.nextInt(89) + 10;		//generate a number between 10 and 99;
		int lastTwoNumber = rand.nextInt(89) + 10;		//generate a number between 10 and 99;
		String newOrderNumber = Integer.toString(firstTwoNumber) +  customerName.toUpperCase().charAt(0) + customerEmail.toUpperCase().charAt(0) + Integer.toString(lastTwoNumber);
		return newOrderNumber;
	}

}

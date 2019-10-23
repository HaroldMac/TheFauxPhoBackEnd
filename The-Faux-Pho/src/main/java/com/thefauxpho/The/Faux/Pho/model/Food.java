package com.thefauxpho.The.Faux.Pho.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Food {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name, type, description, imagePath;
	private double price;
	
	@ManyToMany(mappedBy = "foods")
	private List<CustomerOrder> order;
	
	public Food() {}

	public Food(String name, String type, String description, String imagePath, double price) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.imagePath = imagePath;
		this.price = price;

	}

	
	public Food(String name, String type, String description, String imagePath, double price,
			List<CustomerOrder> order) {
		this.name = name;
		this.type = type;
		this.description = description;
		this.imagePath = imagePath;
		this.price = price;
		this.order = order;
	}

	
	public Food(long id, String name, String type, String description, String imagePath, double price,
			List<CustomerOrder> order) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.imagePath = imagePath;
		this.price = price;
		this.order = order;
	}
	
	public Food(long id, String name, String type, String description, String imagePath, double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.imagePath = imagePath;
		this.price = price;
	}


	public List<CustomerOrder> getOrders() {
		return order;
	}


	public void setOrders(List<CustomerOrder> order) {
		this.order = order;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}

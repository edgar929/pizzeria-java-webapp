package model;

import java.io.Serializable;
import java.util.ArrayList;

import enums.DeliveryMode;
import enums.Size;

public class Pizza implements Serializable{

	private String name;
	private DeliveryMode delivery;
	private Size size;
	private ArrayList<String> ingredients = new ArrayList<>();
	private double price;
	
	public String getDelivery() {
		return delivery.getDelivery();
	}
	
	public Pizza(String nm, DeliveryMode mode, Size sz, String[] options, double pr) 
	{
		name = nm;
		delivery = mode;
		size = sz;
		price = pr;
		for ( int i =0; i<options.length; i++)
		{
			ingredients.add(options[i]);
		}
	}
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public String getSize() {
		return size.get_size();
	}
	
	public void setDelivery(DeliveryMode delivery) {
		this.delivery = delivery;
	}
	
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
}

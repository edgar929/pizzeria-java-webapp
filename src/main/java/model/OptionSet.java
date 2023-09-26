package model;
/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

import java.io.Serializable;
import java.util.*;

class OptionSet implements Serializable
{
	private String name;
	private ArrayList<Option> choices = new ArrayList<>();
	
	protected OptionSet()
	{
		name = "meat choice";
	}
	
	protected OptionSet(String n)
	{
		name = n;
	}
	
	//getters
	protected String getName()
	{
		return name;
	}
	
	
	protected void setName(String n)
	{
		name = n;
	}
	
	
	protected int getOptionChoice(String name)
	{
		int index = -1;
		for (Option option : choices)
		{
			if (option.getName() == name)
				index = choices.indexOf(option);
		}
		return index;
	}

	protected boolean deleteOption(String name)
	{
		int optIndex = getOptionChoice(name);
		if (optIndex == -1)
			return false;
		
        Option option = choices.get(optIndex);
		return choices.remove(option);
	}
	
	protected boolean addOption(String name, double price)
	{
		return choices.add(new Option(name, price));	
	}
	
	protected boolean updateOption(String name, String newName, double newPrice)
	{
		int index = getOptionChoice(name);
		if (index == -1)
			return false;
		
		choices.get(index).setName(newName);
		choices.get(index).setPrice(newPrice);
		
		return true;
	}
	
	protected boolean updateOptionPrice(double newPrice, String name)
	{
		int optIndex = getOptionChoice(name);
		if (optIndex == -1)
			return false;
		
		Option option = choices.get(optIndex);
		option.setPrice(newPrice);
		return true;
	}
	
	protected LinkedHashMap<String, Double> getAllOptions()
	{
	    LinkedHashMap<String, Double> options = new LinkedHashMap<>();
	    for (Option option : choices) {
	    	System.out.println("_"+option.getName());
            options.put(option.getName(), option.getPrice());
        }
	    return options;
	}
	
	protected void print()
	{
		for (Option option : choices)
		{
			System.out.println("	option name: "+option.getName() +", price: "+option.getPrice());
		}
	}
	
	public String toString() {
		return name;
	}
	
class Option implements Serializable
{
	private String name;
	private double price; 
	
	protected Option()
	{
		name = "beef";
		price = 800.0;
	}
	protected Option(String n, double p)
	{
		name = n;
		price = p;
	}
	protected String getName() 
	{
		return name;
	}
	
	protected double getPrice() 
	{
		return price;
	}
	
	
	protected void setName(String n) 
	{
		name = n;
	}
	
	protected void setPrice(double pr) {
		price = pr;
	}
	
	protected void print()
	{
		System.out.println("option name:" +name+"\n"+name+" price: "+price);
	}
	
	public String toString()
	{
		return "name:"+name+", price:"+price;
	}
}
}

package model;
/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import enums.DeliveryMode;
import enums.Size;
import model.OptionSet.Option;

public class PizzaConfig implements Serializable
{
	//instance variables
	private String name;
	private double basePrice;
	private ArrayList<OptionSet> options =  new ArrayList<>();
	
	private Size pizzaSize;
	private DeliveryMode delivery;
	
	public PizzaConfig()
	{
		name = "Domino";
		basePrice = 0;
	}
	
	//constructor to initialise new object with name, price and options array size
	public PizzaConfig(String n, double price)
	{
		name = n;
		basePrice = price;
	}
	
	public PizzaConfig(double price)
	{
		basePrice = price;
	}
	
	//getters
	public double getBasePrice() 
	{
		return basePrice;
	}
	public String getName()
	{
		return name;
	}
	
	public DeliveryMode getDelivery() {
		return delivery;
	}
	
	public Size getPizzaSize() {
		return pizzaSize;
	}
	//setters
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//CRUD operations
	
	//method to find if option set exists and return it
 	//finding OptionSet by name and return its index
	public int findOptionSet(String name)
	{
		synchronized(options)
		{
			int index = -1;
	 		for (OptionSet option : options)
			{
				if (option.getName().equalsIgnoreCase(name))
					index =  options.indexOf(option);
			}
			return index;
		}
	}
	
 	
	public int findOption(String name, String optionSetName)
	{
		int optionSet = findOptionSet(optionSetName);
		if ( optionSet == -1 )
			return -1;
		
		OptionSet set = options.get(optionSet);
		int optionIndex = set.getOptionChoice(name);
		return optionIndex;
	}
	
	public synchronized boolean deleteOption(String name, String optionSetName)
	{
		int optionSetIndex = findOptionSet(optionSetName);
		OptionSet optionSet = options.get(optionSetIndex);
		boolean isDeleted = optionSet.deleteOption(name);
		return isDeleted;
	}
	
	public synchronized boolean deleteOptionSet(String optionSetName)
	{
		int index = findOptionSet(optionSetName);
		if (index == -1)
			return false;
		
		OptionSet deletedSet = options.remove(index);
		
		if(deletedSet != null)
			return true;
		else
			return false;
	}

	public synchronized boolean addOption(String name, double price, String optionSetName)
	{
		int index = findOptionSet(optionSetName);
		if (index == -1)
			return false;
		OptionSet optionSet = options.get(index);
		boolean isAdded = optionSet.addOption(name, price);
		return isAdded;
	}
	
	public synchronized boolean addOptionSet(String name)
	{
		return options.add(new OptionSet(name));
	}
	
	public synchronized boolean updateOption(String name, String newName, double newPrice, String optionSetName)
	{
		int index = findOptionSet(optionSetName);
		if (index == -1)
			return false;
		OptionSet optionSet = options.get(index);
		boolean isUpdated = optionSet.updateOption(name, newName, newPrice);
		return isUpdated;
	}

	public synchronized boolean updateOptionPrice(String optionSetName, double newPrice, String name)
	{
		int index = findOptionSet(optionSetName);
		if (index == -1)
			return false;
		OptionSet optionSet = options.get(index);
		optionSet.updateOptionPrice(newPrice, name);
		return true;
	}
	
	public synchronized boolean updateOptionSet(String optionSetName, String newOptionSetName)
	{
		int index = findOptionSet(optionSetName);
		if (index == -1)
			return false;
		
		OptionSet optionSet = options.get(index);
		optionSet.setName(newOptionSetName);
		
		return true;
	}
	
	public synchronized void updatePizzaConfig(String newName, double newBasePrice)
	{
		setName(newName);
		setBasePrice(newBasePrice);
	}

	public ArrayList<String> availableOptionSets()
	{
	    ArrayList<String> optionSets = new ArrayList<>();
	    for (OptionSet optionSet : options) {
            optionSets.add(optionSet.getName());
        }
	    
	    return optionSets;
	}
	
	public LinkedHashMap<String, Double> availableOptions(String optionSetName)
    {
	    int index = findOptionSet(optionSetName);
        if (index == -1)
            return null;
        OptionSet optionSet = options.get(index);
        return optionSet.getAllOptions();
    }
	
	public void print()
	{
		synchronized(options)
		{
			System.out.println("Pizza config name: "+name);
			System.out.println(name+" Config base price  : "+basePrice);
			if(!options.isEmpty())
			{
				for (OptionSet optionSet : options)
				{
					System.out.println("Option set name "+optionSet.getName());
					optionSet.print();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder build = new StringBuilder("Pizza config");
		return build.append(name).append("options").toString(); 
	}
}

/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

package server;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import model.Pizza;
import model.PizzaConfig;
import wrapper.CreatePizzeria;
import wrapper.UpdatePizzeria;

public class PizzeriaServer implements PizzeriaInterface
{

	@Override
	public Boolean loadProperties(Properties prop, CreatePizzeria api) {
		return api.configurePizzeria(prop);
	}

	@Override
	public PizzaConfig printPizzeria(String pizzeria, CreatePizzeria api) 
	{
		return api.getPizzeria(pizzeria);
	}

	@Override
	public ArrayList<String> availablePizzerias(CreatePizzeria api) 
	{
		return api.allAvailablePizzerias();
	}


	@Override
	public boolean updateBasePrice(String pizzeria, double price, UpdatePizzeria api) 
	{
		boolean isUpdated = api.updateBasePrice(pizzeria, price);
		return isUpdated;
	}

	@Override
	public boolean addOption(String option, Double price, String set, String pizzeria, CreatePizzeria api) 
	{
		return api.createOption(pizzeria, set, option, price);
	}

	@Override
	public boolean deletePizzeria(String pizzeria, CreatePizzeria api) 
	{
		return api.deletePizzeria(pizzeria);
	}

	@Override
	public ArrayList<String> availableOptionSets(String pizzeria, CreatePizzeria api) {
		return api.allAvailableOptionSets(pizzeria);
	}

	@Override
	public LinkedHashMap<String, Double> availableOptions(String pizzeria, String optionset, CreatePizzeria api) 
	{
		return api.allAvailableOptions(pizzeria, optionset);
	}

	@Override
	public boolean orderPizza(Pizza pizza, CreatePizzeria api)
	{
		return api.createOrder(pizza);
	}

}

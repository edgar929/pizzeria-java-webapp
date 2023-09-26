package database;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.Pizza;
import model.PizzaConfig;

public interface PizzeriaDaoInterface 
{
	public boolean createPizzeria(PizzaConfig pizzaConfig);
	public boolean createTables();
	public PizzaConfig getPizzeria(String pizzeriaName);
	public boolean createOptionSet(String pizzeria, String optionSet);
	public boolean createOption(String option, double price, String optionSet, String pizzeria);
	public boolean isOptionSetExists(String optionset);
	public ArrayList<String> availablePizzerias();
	public LinkedHashMap<String, Double> availableOptions(String set, String pizzeria);
	public ArrayList<String> availableOptionSets(String pizzeria);
	public boolean createOrder(Pizza pizza);
	public double getOptionPrice(String option, String optSet, String pizzeria);
	public boolean createOrderDetail(int id, String ingredient, double price);
	public boolean updateBasePrice(String pizzeria, double newPrice);
	public boolean deletePizzeria(String pizzeria);
}

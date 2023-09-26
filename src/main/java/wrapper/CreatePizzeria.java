package wrapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import model.Pizza;
import model.PizzaConfig;

/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

public interface CreatePizzeria
{
	public void configurePizzeria(String filename);
	public Boolean configurePizzeria(Properties props);
	public void printPizzeria(String pizzeriaName);
	public boolean createOption(String pizzeria, String optionSet, String name, double price);
	public void createOptionSet(String pizzeria, String optionSet);
	public void printPizzerias();
	public PizzaConfig getPizzeria(String pizzeria);
	public ArrayList<String> allAvailablePizzerias();
	public ArrayList<String> allAvailableOptionSets(String pizzeria);
	public boolean deletePizzeria(String pizzeriaName);
	public LinkedHashMap<String, Double> allAvailableOptions(String pizzeria, String optionset);
	public boolean createOrder(Pizza pizza);
}

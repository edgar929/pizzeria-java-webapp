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
import wrapper.*;

public interface PizzeriaInterface
{
	  public Boolean loadProperties(Properties prop, CreatePizzeria api);
	  public PizzaConfig printPizzeria(String pizzeria, CreatePizzeria api);
	  public ArrayList<String> availablePizzerias(CreatePizzeria api);
	  public ArrayList<String> availableOptionSets(String pizzeria, CreatePizzeria api);
	  public boolean updateBasePrice(String pizzeria, double newPrice, UpdatePizzeria api);
	  public boolean addOption(String option, Double price,String set, String pizzeria, CreatePizzeria api);
	  public boolean deletePizzeria(String pizzeria, CreatePizzeria api);
	  public LinkedHashMap<String, Double> availableOptions(String pizzeria, String optionset, CreatePizzeria api);
	  public boolean orderPizza(Pizza pizza, CreatePizzeria api);
}

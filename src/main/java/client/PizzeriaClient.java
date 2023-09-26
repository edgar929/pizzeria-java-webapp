package client;

import java.util.*;

import model.Pizza;
import model.PizzaConfig;

public interface PizzeriaClient
{
  public boolean loadProperties(String path);
  public PizzaConfig printPizzeria(String pizzeria);
  public ArrayList<String> availablePizzerias();
  public boolean updateBasePrice(String pizzeria, double newPrice);
  public boolean addOption(String option, Double price,String set, String pizzeria);
  public boolean deletePizzeria(String pizzeria);
  public boolean OrderPizza(Pizza pizza);
}

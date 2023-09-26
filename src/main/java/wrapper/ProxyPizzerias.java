package wrapper;
/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import enums.ExceptionTypes;

import java.util.Properties;
import java.util.Set;

import exception.ClassifyException;
import exception.CustomException;
import io.BuildConfig;
import model.Pizza;
import model.PizzaConfig;

public abstract class ProxyPizzerias
{
	/*hashmap variable for all pizzerias
	  it static so it can be shared between references to the API.
	 */
	private static LinkedHashMap<String, PizzaConfig> configs = new LinkedHashMap<>();
	
	/*
	 1. createPizzeria method puts new pizzaConfig to configs api
	 2. when price is <=0 we throw a self healing exception to add a default price of 5000
	 3. when a pizzaConfig which has the same name as pizzeria key exists we throw 
	 	PizzeriaExists exception which handles it by adding a default extra _1 on the name
	 */
	private boolean createPizzeria(String pizzeriaName, PizzaConfig pizzaConfig)
	{
		try
		{
			if(pizzaConfig.getBasePrice() <= 0 )
				throw ClassifyException.theException(ExceptionTypes.PRICE_MISSING, pizzaConfig);
			
			if(configs.containsKey(pizzeriaName) )
				throw ClassifyException.theException(ExceptionTypes.PIZZERIA_EXISTS, pizzaConfig);
		
			configs.put(pizzeriaName, pizzaConfig);
			
		} 
		catch (CustomException e)
		{
			e.fix(); 
			configs.put(pizzaConfig.getName(), pizzaConfig); 
		}
		return true;
	}
	
	public synchronized void configurePizzeria(String filename) 
	{
		BuildConfig io = new BuildConfig();
		PizzaConfig pizza = null;
		try
		{
			if(!filename.contains(".txt"))
				throw ClassifyException.theException(ExceptionTypes.WRONG_FILE_EXTENSION, filename);
			try
			{
				pizza = io.buildPizzaConfig(filename);
			} catch (IOException e)
			{
					throw ClassifyException.theException(ExceptionTypes.FILE_NOT_EXIST, filename);
			}
        	createPizzeria(pizza.getName(), pizza);
		} catch (CustomException e)
		{
			e.fix();
			try
			{
				pizza = io.buildPizzaConfig(e.getName());
				createPizzeria(pizza.getName(), pizza);
			} catch (IOException e1)
			{
				e.printStackTrace();
			}
		} 
	}
	
	public Boolean configurePizzeria(Properties props)
	{
		BuildConfig io = new BuildConfig();
		PizzaConfig config = io.parseProperties(props);
		Boolean isCreated = createPizzeria(config.getName(), config);
		return isCreated;
	}
	
	//print all pizzeria configs
	public  void printPizzerias()
	{
		synchronized(configs)
		{
			for (PizzaConfig config : configs.values())
			{
				config.print();
			}
		}
	}
	
	public synchronized void printPizzeria(String pizzeriaName)
	{
		try
		{
			if(isPizzeriaExists(pizzeriaName))
			{
				PizzaConfig pizza = configs.get(pizzeriaName);
				pizza.print();
			}
			else
				throw ClassifyException.theException(ExceptionTypes.NOT_FOUND, "Pizzeria");
		} 
		catch (CustomException e)
		{
			e.fix();
		}
	}
	
	public void updateOptionSetName(String pizzeriaName, String optionSetname, String newName)
	{
		PizzaConfig pizzeria = null;
		try
		{
			if(isPizzeriaExists(pizzeriaName))
			{
				pizzeria = configs.get(pizzeriaName);
				int index = pizzeria.findOptionSet(newName);
				
				if( index != -1)
					throw ClassifyException.theException(ExceptionTypes.OPTIONSET_EXISTS,  newName);
				
				pizzeria.updateOptionSet(optionSetname, newName);
			}
		} 
		catch (CustomException e)
		{
			e.fix();
			pizzeria.updateOptionSet(optionSetname, e.getName());
		}
	}
	
	public boolean updateBasePrice(String pizzeriaName, double newPrice)
	{
		PizzaConfig pizzeria = null;
		boolean isUpdated = false;
		try
		{
			if(isPizzeriaExists(pizzeriaName))
			{
				pizzeria = configs.get(pizzeriaName);
				
				if (newPrice <= 0)
					throw ClassifyException.theException(ExceptionTypes.PRICE_MISSING, pizzeria);
				
				pizzeria.setBasePrice(newPrice);
				isUpdated = true;
			}
		}
		catch (CustomException e)
		{
			e.fix(); 	
			configs.put(pizzeria.getName(), pizzeria); //finally store our pizzeria
		}
		return isUpdated;
	}
	
	public PizzaConfig getPizzeria(String pizzeria)
	{
		PizzaConfig config = null;
		try
		{
			if(isPizzeriaExists(pizzeria))
			{
				config = configs.get(pizzeria);
			}
			else
				throw ClassifyException.theException(ExceptionTypes.NOT_FOUND, "Pizzeria");
		} 
		catch (CustomException e)
		{
			e.fix();
		}
		return config;
	}
	
	public void updateOptionPrice(String pizzeriaName, String optionName, String option, double newPrice)
	{
		if(isPizzeriaExists(pizzeriaName))
		{
			PizzaConfig pizzeria = configs.get(pizzeriaName);
			pizzeria.updateOptionPrice(optionName, newPrice, option);
		}
	}

	public void createOptionSet(String pizzeria,  String optionSet)
	{
		PizzaConfig pizzaConfig = null;
		try
		{
			if(isPizzeriaExists(pizzeria))
			{
				pizzaConfig = configs.get(pizzeria);
				
				if (pizzaConfig.findOptionSet(optionSet) != -1)
					throw ClassifyException.theException(ExceptionTypes.OPTIONSET_EXISTS, pizzaConfig);
				else
					pizzaConfig.addOptionSet(optionSet);
			}
			else
				throw ClassifyException.theException(ExceptionTypes.NOT_FOUND, "option");
		} 
		catch (CustomException e)
		{
			e.fix();
		}
	}
	
	public boolean createOption(String pizzeria,  String optionSet, String name, double price)
	{
		PizzaConfig pizzaConfig = null;
		boolean isAdded = false;
		try
		{
			if(isPizzeriaExists(pizzeria))
			{
				pizzaConfig = configs.get(pizzeria);
				
				if (pizzaConfig.findOption(name, optionSet) != -1)
					throw ClassifyException.theException(ExceptionTypes.OPTION_EXISTS,  name);
				else
					isAdded = pizzaConfig.addOption(name, price, optionSet);
			}
			else
				throw ClassifyException.theException(ExceptionTypes.NOT_FOUND, "option");
			
		} catch (CustomException e)
		{
			e.fix();
			if (e.getClass().getName().contains(ExceptionTypes.NOT_FOUND.get_exception()))
				pizzaConfig.addOption(e.getName(), price, optionSet);
		}
		return isAdded;
	}
	
	public synchronized boolean deletePizzeria(String pizzeriaName)
	{
		try
		{
			if(isPizzeriaExists(pizzeriaName))
				configs.remove(pizzeriaName);
			else
				throw ClassifyException.theException(ExceptionTypes.NOT_FOUND, "option");
			
		} catch (CustomException e)
		{
			e.fix();
		}
		return true;
	}
	
	public ArrayList<String> allAvailablePizzerias()
	{
		ArrayList<String> pizzerias = new ArrayList<>();
		Set<Entry<String, PizzaConfig>> set = configs.entrySet();
		Iterator<Entry<String, PizzaConfig>> it = set.iterator();
		while(it.hasNext())
		{
			Entry<String, PizzaConfig> pizza = it.next();
			pizzerias.add(pizza.getKey());
		}
		return pizzerias;
	}
	
	
	//reusable method to check if pizzeria exists
	private boolean isPizzeriaExists(String pizzeria)
	{
		synchronized(configs)
		{
			boolean isExist = false;
			Iterator<String> it = configs.keySet().iterator();
			while(it.hasNext())
			{
				if(it.next().equalsIgnoreCase(pizzeria))
					isExist = true;
			}
			
			return isExist;
		}
	}
	
	public ArrayList<String> allAvailableOptionSets(String pizzeria)
	{
		return null;
	}
	public boolean createOrder(Pizza pizza) {
		return false;
	}
	public LinkedHashMap<String, Double> allAvailableOptions(String pizzeria, String optionset)
	{
		return null;
	}
	public String toString() {
		StringBuilder build = new StringBuilder("Proxy pizzeria");
		return build.append("configs").toString();
	}

}

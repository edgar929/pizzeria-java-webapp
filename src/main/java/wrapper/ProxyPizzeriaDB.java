package wrapper;

import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Properties;

import database.PizzeriaDAO;
import enums.ExceptionTypes;
import exception.ClassifyException;
import exception.CustomException;
import io.BuildConfig;
import model.Pizza;
import model.PizzaConfig;

public abstract class ProxyPizzeriaDB {
   
    PizzeriaDAO dao = new PizzeriaDAO();
    
    private boolean createPizzeria(String pizzeriaName, PizzaConfig pizzaConfig)
    {
        try
        {
            if(pizzaConfig.getBasePrice() <= 0 )
                throw ClassifyException.theException(ExceptionTypes.PRICE_MISSING, pizzaConfig);
            
            if(dao.getPizzeria(pizzeriaName) != null )
                throw ClassifyException.theException(ExceptionTypes.PIZZERIA_EXISTS, pizzaConfig);
        
            dao.createPizzeria(pizzaConfig);
            
        } 
        catch (CustomException e)
        {
            e.fix(); 
            dao.createPizzeria(pizzaConfig); 
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
    
    public void createPizza(String pizzeriaName, PizzaConfig pizzaConfig) {
        createPizzeria(pizzeriaName, pizzaConfig);
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
  		ArrayList<String> pizzerias = dao.availablePizzerias();
  		for (String string : pizzerias) {
			System.out.println(string);
		}
  	}
    
    public synchronized void printPizzeria(String pizzeriaName)
    {
       PizzaConfig pizzeria = dao.getPizzeria(pizzeriaName);
       pizzeria.print();
    }
    
    public void updateOptionSetName(String pizzeriaName, String optionSetname, String newName)
    {
        
    }
    
    public boolean updateBasePrice(String pizzeriaName, double newPrice)
    {
        return dao.updateBasePrice(pizzeriaName, newPrice);
    }
    
    public PizzaConfig getPizzeria(String pizzeria)
    {
        return dao.getPizzeria(pizzeria);
    }
    
    public void updateOptionPrice(String pizzeriaName, String optionName, String option, double newPrice)
    {
       
    }

    public void createOptionSet(String pizzeria,  String optionSet)
    {
      
    }
    
    public boolean createOption(String pizzeria,  String optionSet, String name, double price)
    {
    	PizzaConfig pizzaConfig = null;
		boolean isAdded = false;
		try
		{
			if(isPizzeriaExists(pizzeria))
			{
				pizzaConfig = dao.getPizzeria(pizzeria);
				
				if (pizzaConfig.findOption(name, optionSet) != -1)
					throw ClassifyException.theException(ExceptionTypes.OPTION_EXISTS,  name);
				else
					isAdded = dao.createOption(name, price, optionSet, pizzeria);
			}
			else
				throw ClassifyException.theException(ExceptionTypes.NOT_FOUND, "option");
			
		} catch (CustomException e)
		{
			e.fix();
			if (e.getClass().getName().contains(ExceptionTypes.NOT_FOUND.get_exception()))
				dao.createOption(e.getName(), price, optionSet, pizzaConfig.getName());
		}
        return isAdded;
    }
    
    public synchronized boolean deletePizzeria(String pizzeriaName)
    {
        return dao.deletePizzeria(pizzeriaName);
    }
    
    public ArrayList<String> allAvailablePizzerias()
    {
        return dao.availablePizzerias();
    }
    
    public ArrayList<String> allAvailableOptionSets(String pizzeria)
    {
    	return dao.availableOptionSets(pizzeria);
    }
    //reusable method to check if pizzeria exists
    private boolean isPizzeriaExists(String pizzeria)
    {
        return false;
    }
    
    public LinkedHashMap<String, Double> allAvailableOptions(String pizzeria, String optionset)
    {
    	return dao.availableOptions(optionset, pizzeria);
    }
    
	public boolean createOrder(Pizza pizza) {
		return dao.createOrder(pizza);
	}
	
    public String toString() {
        StringBuilder build = new StringBuilder("Proxy pizzeria");
        return build.append("configs").toString();
    }

}

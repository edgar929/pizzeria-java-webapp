package exception;

import java.util.logging.Logger;

import model.PizzaConfig;

public abstract class CustomException extends Exception
{
	PizzaConfig config; 
	private String name;
	private Logger logger = Logger.getLogger(CustomException.class.getName()); 
	boolean isLogOn = true;
	
	public CustomException()
	{
		super();
	}
	
	public CustomException(PizzaConfig pizza)
	{
		config = pizza;
	}
	
	public CustomException(String n)
	{
		name = n;
	}
	
	public CustomException(String n, PizzaConfig pizza)
	{
		config = pizza;
		name = n;
	}
	
	public abstract void fix();
	
	//log method 
	public void log(String msg)
	{
		if (isLogOn)
			logger.info(msg);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String strError) {
		name = strError;
	}
}

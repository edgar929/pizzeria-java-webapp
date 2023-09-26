package exception;

import model.PizzaConfig;

public class MissingNameException extends CustomException
{

	public MissingNameException(PizzaConfig pizza)
	{
		super(pizza);
	} 
	
	//if pizzaConfig name is missing it takes the Pizzeria name
	@Override
	public void fix() {
		log("Config name missing !!");
		config.setName("ccc");
	}

}

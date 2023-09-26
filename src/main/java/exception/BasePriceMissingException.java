package exception;

import model.PizzaConfig;

public class BasePriceMissingException extends CustomException
{
    public BasePriceMissingException(PizzaConfig pizza)
	{
		super(pizza);
	}
    
	@Override
	public void fix() {
		log("base price is missing!!");
		config.setBasePrice(5000);
	}



}

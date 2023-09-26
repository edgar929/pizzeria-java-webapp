package exception;


import model.PizzaConfig;

public class OptionSetExistException extends CustomException
{
	public OptionSetExistException(PizzaConfig pizza)
	{
		super(pizza);
	} 
	
	@Override
	public void fix() {
		log("OptionSet already exists!!");
		setName(getName()+"_1");
		config.addOptionSet(getName());
	}

}

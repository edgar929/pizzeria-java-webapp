package exception;



import model.PizzaConfig;

public class PizzeriaExistsException extends CustomException
{
	
    public PizzeriaExistsException(PizzaConfig pizza)
	{
		super(pizza);
	}
    
	@Override
	public void fix() 
	{
		log("Pizza config already exists !!!");
		String newName;
			
		newName = config.getName()+"_1";
		config.setName(newName);
	}

	
}

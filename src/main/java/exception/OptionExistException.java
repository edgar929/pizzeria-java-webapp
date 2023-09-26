package exception;

public class OptionExistException extends CustomException
{
	public OptionExistException(String str)
	{
		super(str);
	} 
	
	@Override
	public void fix() {
		log("Option already exists!!");
		setName(getName()+"_1");
	}
}

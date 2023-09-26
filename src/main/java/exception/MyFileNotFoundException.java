package exception;

public class MyFileNotFoundException extends CustomException
{


	public MyFileNotFoundException(String file)
	{
		super(file);
	}
	
	@Override
	public void fix() {
		log("**file not found in directory!! **");
		setName(getName().substring(getName().lastIndexOf("/")+1));
	}

}

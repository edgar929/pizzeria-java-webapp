package exception;

public class WrongFileExtension extends CustomException
{

	public WrongFileExtension(String path)
	{
		super(path);
	}
	@Override
	public void fix() {
		log("**unexcepted file extension !! **");
		String ext = getName().substring(getName().indexOf("."));
		
        if(!ext.equalsIgnoreCase(".txt")) {
           setName(getName().replace(ext, ".txt"));
        }  
	}

}

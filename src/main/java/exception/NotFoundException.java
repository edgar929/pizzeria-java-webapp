package exception;

public class NotFoundException extends CustomException
{
	private String message;
	public NotFoundException(String msg)
	{
		message = msg;
	}
	@Override
	public void fix() {
		log(message + " not found!!!");
		return;
	}
}

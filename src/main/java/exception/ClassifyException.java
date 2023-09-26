package exception;

import enums.ExceptionTypes;
import model.PizzaConfig;

public class ClassifyException
{
	public static CustomException theException(ExceptionTypes exception, PizzaConfig pizza)
	{
		if (exception.get_exception().equals(ExceptionTypes.PRICE_MISSING.get_exception()))
			return new BasePriceMissingException(pizza);
		if (exception.get_exception().equals(ExceptionTypes.PIZZERIA_EXISTS.get_exception()))
			return new PizzeriaExistsException(pizza);
		if (exception.get_exception().equals(ExceptionTypes.OPTIONSET_EXISTS.get_exception()))
			return new OptionSetExistException(pizza);
//		if (exception.equalsIgnoreCase("ConfigNameMissing"))
//			return new MissingNameException(pizza);
		
		return null;
	}
	
	public static CustomException theException(ExceptionTypes exception, String str)
	{
		if (exception.get_exception().equals(ExceptionTypes.OPTION_EXISTS.get_exception()))
			return new OptionExistException(str);
		if (exception.get_exception().equals(ExceptionTypes.FILE_NOT_EXIST.get_exception()))
			return new MyFileNotFoundException(str);
		if (exception.get_exception().equals(ExceptionTypes.WRONG_FILE_EXTENSION.get_exception()))
			return new WrongFileExtension(str);
		if (exception.get_exception().equals(ExceptionTypes.NOT_FOUND.get_exception()))
			return new NotFoundException(str);

		return null;
	}
}

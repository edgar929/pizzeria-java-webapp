package enums;

public enum ExceptionTypes
{
	CONFIG_NAME_MISSING("ConfigNameMissing"),
	PRICE_MISSING("PriceMissing"),
	PIZZERIA_EXISTS("PizzeriaExists"),
	OPTIONSET_EXISTS("OptionSet Exists"),
	OPTION_EXISTS("Option Exists"),
	NOT_FOUND("not found"),
	WRONG_FILE_EXTENSION("wrong file extension"),
	FILE_NOT_EXIST("FileNotExists");
	
	private String _exception;
	
	private ExceptionTypes(String exc)
	{
		_exception = exc;
	}
	
	public String get_exception() {
		return _exception;
	}
	
	public void set_exception(String _exception) {
		this._exception = _exception;
	}
}

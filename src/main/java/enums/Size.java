package enums;
/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

public enum Size
{
	UNKNOWN("unknown",0.0),
	SMALL("small", 1000.0),
	MEDIUM("medium", 2000.0),
	LARGE("large", 3500.0);
	
	private String _size;
	private double price;
	
	private Size(String size, double pr)
	{
		_size = size;
		price = pr;
	}
	
	public String get_size() 
	{
		return _size;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public static Size fromString(String stringValue)
	{
		for (Size choice : Size.values())
		{
			if (stringValue.equalsIgnoreCase(choice._size))
				return choice;
		}
		if (stringValue.equalsIgnoreCase("small"))
			return SMALL;
		if (stringValue.equalsIgnoreCase("medium"))
			return MEDIUM;
		if (stringValue.equalsIgnoreCase("large"))
			return LARGE;
		return UNKNOWN;
	}
	
	@Override
	public String toString() {
		return "size: "+_size +", price: "+price;
	}
}

package enums;
/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

public enum DeliveryMode
{
	UNKNOWN("Unknow", 0.0),
	EAT_IN("Eat_in", 0.0),
	TAKEAWAY("Take_away", 0.0),
	DELIVERY("Delivery", 1000.0);
	
	private double price;
	private String delivery;
	
	private DeliveryMode(String mode, double amount)
	{
		price = amount;
		delivery = mode;
	}
	
	private DeliveryMode() {
		// TODO Auto-generated constructor stub
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	public String getDelivery() {
		return delivery;
	}
	
	public static DeliveryMode fromString(String stringValue)
	{
		for (DeliveryMode choice : DeliveryMode.values())
		{
			if (stringValue.equalsIgnoreCase(choice.delivery))
				return choice;
		}
		if (stringValue.equalsIgnoreCase("Eat in"))
			return EAT_IN;
		if (stringValue.equalsIgnoreCase("Delivery"))
			return DELIVERY;
		if (stringValue.equalsIgnoreCase("Take away"))
			return TAKEAWAY;
		return UNKNOWN;
	}
	
	@Override
	public String toString() {
		return "delivery mode: "+ delivery +", price: "+price;
	}
}

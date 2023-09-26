package wrapper;
/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

public interface UpdatePizzeria
{
	public void updateOptionSetName(String pizzeriaName, String optionSetname, String newName);
	public boolean updateBasePrice(String pizzeriaName, double newPrice);
	public void updateOptionPrice(String pizzeriaName, String optionName, String Option, double newPrice);
}

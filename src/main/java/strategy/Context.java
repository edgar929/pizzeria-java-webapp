package strategy;

import java.io.ObjectOutputStream;

import wrapper.PizzeriaConfigDB;
import wrapper.PizzeriaConfigAPI;

public class Context
{
	Strategy strategy;
	
	public Context(Strategy str)
	{
		strategy = str;
	}
	public void executeStrategy(PizzeriaConfigAPI api, ObjectOutputStream out)
	{
		strategy.operate(api, out);
	}
	public void executeStrategy(PizzeriaConfigDB db, ObjectOutputStream out)
    {
        strategy.operate(db, out);
    }
	
}

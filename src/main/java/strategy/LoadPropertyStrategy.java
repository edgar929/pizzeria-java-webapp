package strategy;

import java.io.*;
import java.util.Properties;

import wrapper.PizzeriaConfigDB;
import server.Request;
import wrapper.*;

public class LoadPropertyStrategy implements Strategy
{
	private Properties property;

	public LoadPropertyStrategy(Request pro)
	{
		property = (Properties) pro.getObject();
	}
	
	@Override
	public void operate(PizzeriaConfigAPI api, ObjectOutputStream out) {
	    
		Boolean isCreated = server.loadProperties(property, api);
		try
		{
			out.writeObject(isCreated);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

    @Override
    public void operate(PizzeriaConfigDB db, ObjectOutputStream out) 
    {
        Boolean isCreated = server.loadProperties(property, db);
        try
        {
            out.writeObject(isCreated);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}

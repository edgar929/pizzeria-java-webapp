package strategy;

import java.io.ObjectOutputStream;

import wrapper.PizzeriaConfigDB;
import server.PizzeriaServer;
import wrapper.PizzeriaConfigAPI;

public interface Strategy
{
	PizzeriaServer server = new PizzeriaServer();
	
	public void operate(PizzeriaConfigAPI api, ObjectOutputStream out);
	public void operate(PizzeriaConfigDB db, ObjectOutputStream out);
	
}

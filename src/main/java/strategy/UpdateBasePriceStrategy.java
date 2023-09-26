package strategy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Properties;

import wrapper.PizzeriaConfigDB;
import server.Request;
import wrapper.PizzeriaConfigAPI;

public class UpdateBasePriceStrategy implements Strategy
{
    private Properties property;

    public UpdateBasePriceStrategy(Request pro)
    {
        property = (Properties) pro.getObject();
    }
    
    @Override
    public void operate(PizzeriaConfigAPI api, ObjectOutputStream out) 
    {
        try
        {
            String pizzeria = property.getProperty("pizzeria");
            Double price = Double.parseDouble(property.getProperty("newPrice"));
            out.writeObject(server.updateBasePrice(pizzeria, price, api));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void operate(PizzeriaConfigDB db, ObjectOutputStream out) {
        try
        {
            String pizzeria = property.getProperty("pizzeria");
            Double price = Double.parseDouble(property.getProperty("newPrice"));
            out.writeObject(server.updateBasePrice(pizzeria, price, db));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

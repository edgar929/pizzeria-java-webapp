package strategy;


import java.io.*;
import java.util.Properties;

import wrapper.PizzeriaConfigDB;
import server.Request;
import wrapper.PizzeriaConfigAPI;

public class AddOptionStrategy  implements Strategy
{
    private Properties property;

    public AddOptionStrategy(Request pro)
    {
        property = (Properties) pro.getObject();
    }
    
    @Override
    public void operate(PizzeriaConfigAPI api, ObjectOutputStream out) 
    {
        try
        {
            String pizzeria = property.getProperty("pizzeria");
            String optionSet = property.getProperty("optionSet");
            String option = property.getProperty("option");
            Double price = Double.parseDouble(property.getProperty("price"));
            
            out.writeObject(server.addOption(option, price, optionSet, pizzeria, api));
           
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
            String optionSet = property.getProperty("optionSet");
            String option = property.getProperty("option");
            Double price = Double.parseDouble(property.getProperty("price"));
            
            out.writeObject(server.addOption(option, price, optionSet, pizzeria, db));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

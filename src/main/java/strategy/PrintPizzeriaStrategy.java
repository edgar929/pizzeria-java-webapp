package strategy;

import java.io.IOException;
import java.io.ObjectOutputStream;

import wrapper.PizzeriaConfigDB;
import model.PizzaConfig;
import server.Request;
import wrapper.PizzeriaConfigAPI;

public class PrintPizzeriaStrategy implements Strategy
{
    private String pizzeriaName;

    public PrintPizzeriaStrategy(Request pro)
    {
        pizzeriaName =  (String) pro.getObject();
    }
    
    @Override
    public void operate(PizzeriaConfigAPI api, ObjectOutputStream out) {
        
        PizzaConfig pizza = server.printPizzeria(pizzeriaName, api);
        try
        {
            out.writeObject(pizza);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void operate(PizzeriaConfigDB db, ObjectOutputStream out) {
        PizzaConfig pizza = server.printPizzeria(pizzeriaName, db);
        try
        {
            out.writeObject(pizza);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

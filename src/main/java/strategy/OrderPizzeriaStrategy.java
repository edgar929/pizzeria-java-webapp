package strategy;


import java.io.*;
import java.util.Properties;

import model.Pizza;
import wrapper.PizzeriaConfigDB;
import server.Request;
import wrapper.PizzeriaConfigAPI;

public class OrderPizzeriaStrategy  implements Strategy
{
    private Pizza pizza;

    public OrderPizzeriaStrategy(Request pro)
    {
    	pizza = pro.getPizza();
    }
    
    @Override
    public void operate(PizzeriaConfigAPI api, ObjectOutputStream out) 
    {
        
    }

    @Override
    public void operate(PizzeriaConfigDB db, ObjectOutputStream out) {
        try
        {
            out.writeObject(server.orderPizza(pizza, db));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

package strategy;

import java.io.*;

import wrapper.PizzeriaConfigDB;
import server.Request;
import wrapper.PizzeriaConfigAPI;

public class DeletePizzeriaStrategy implements Strategy
{
    private String pizzeriaName;

    public DeletePizzeriaStrategy(Request pro)
    {
        pizzeriaName =  (String) pro.getObject();
    }
    
    @Override
    public void operate(PizzeriaConfigAPI api, ObjectOutputStream out) 
    {
        try
        {
            out.writeObject(server.deletePizzeria(pizzeriaName, api));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void operate(PizzeriaConfigDB db, ObjectOutputStream out) {
        try
        {
            out.writeObject(server.deletePizzeria(pizzeriaName, db));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

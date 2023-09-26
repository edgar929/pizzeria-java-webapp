package strategy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import wrapper.PizzeriaConfigDB;
import wrapper.PizzeriaConfigAPI;

public class AvailablePizzeriasStrategy implements Strategy{

    @Override
    public void operate(PizzeriaConfigAPI api, ObjectOutputStream out) 
    {
        ArrayList<String> pizzerias = server.availablePizzerias(api);
        try {
            out.writeObject(pizzerias);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void operate(PizzeriaConfigDB db, ObjectOutputStream out) {
        ArrayList<String> pizzerias = server.availablePizzerias(db);
        try {
            out.writeObject(pizzerias);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import enums.Requests;
import model.Pizza;
import model.PizzaConfig;
import server.Request;

public class Client implements PizzeriaClient
{
	ObjectOutputStream out;
	ObjectInputStream in;
	Socket clientSocket;
	
	public Client(String _hostName, int _port) throws IOException 
	{
            clientSocket =  new Socket(_hostName, _port);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
    }

	
	public boolean loadProperties(String path) 
	{
	    File f = new File(path);
        if(!f.exists())
            return false;
        
		Properties props= new Properties();
		FileInputStream in;
		boolean isLoaded = false;
		try
		{
			in = new FileInputStream(path);
			props.load(in); 
			Request protocol = new Request(Requests.UPLOAD_PROPERTY.get_request(), props);
			
              isLoaded = (boolean) connect(protocol);
           
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return isLoaded;
	}


	
	public PizzaConfig printPizzeria(String pizzeria) 
	{
	    String pizzeriaN = new String(pizzeria); 
		Request request = new Request(Requests.PRINT_PIZZERIA.get_request(), pizzeriaN);
		return (PizzaConfig) connect(request);
	}

	
	public ArrayList<String> availablePizzerias() 
	{	
	    Request request = new Request(Requests.AVAILABLE_PIZZERIAS.get_request());
	    ArrayList<String> pizzerias = (ArrayList<String>) connect(request);
		return pizzerias;
	}

	
	public boolean updateBasePrice(String pizzeria, double newPrice)
	{
	    String price = Double.toString(newPrice);
	    Properties props = new Properties();
	    props.setProperty("pizzeria", pizzeria);
	    props.setProperty("newPrice", price);
	    
	    Request request = new Request(Requests.UPDATE_BASEPRICE.get_request(), props);
	    boolean isUpdated = (boolean) connect(request);
		return isUpdated;
	}

	
	public boolean addOption(String option, Double price, String optionSet, String pizzeria) 
	{
	    String _price = Double.toString(price);
        Properties props = new Properties();
        props.setProperty("pizzeria", pizzeria);
        props.setProperty("price", _price);
        props.setProperty("optionSet", optionSet);
        props.setProperty("option", option);
        
        Request request = new Request(Requests.ADD_OPTION.get_request(), props);
		boolean isAdded = (boolean) connect(request);
		return isAdded;
	}

	
	
	public boolean deletePizzeria(String pizzeria) 
	{
	    String pizzeriaN = new String(pizzeria); 
        Request request = new Request(Requests.DELETE_PIZZERIA.get_request(), pizzeriaN);
        boolean isDeleted = (boolean) connect(request);
		return isDeleted;
	}
	
	   public Object connect(Request obj)
	   {
	        Object response = null;
	        try  
	        {
	            out.writeObject(obj);
                
                while ((response =  in.readObject()) != null)
                {
                    return response;
                }
              
            } catch (UnknownHostException e) {
                System.err.println(" hostname does not exists");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't connect ");
                System.err.println("Error: " + e.getMessage());
                System.exit(1);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } 
            return response;
            
    	    }




		@Override
		public boolean OrderPizza(Pizza pizza) {
			Request request = new Request(Requests.ORDER_PIZZERIA.get_request(), pizza);
			return (boolean) connect(request);
		}
	   
	   
}

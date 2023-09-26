package ui;

import java.io.*;
import java.util.ArrayList;
import client.Client;
import model.PizzaConfig;

public class ClientUI
{
	public static void main(String[] args) throws IOException {
		
		Client client = new Client("localhost", 1111);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int i = 1;
		
		while(i != 0)
		{
			operations();
			System.out.println("Enter your choice:");
			String choice = reader.readLine();
			switch (choice)
			{
			    case "1":
			    {
                    providePropertiesPath( reader, client);
                    break;
                }
                case "2":
                {
                    availablePizzerias(client);
                    break;
                }
                case "3":
                {
                    printPizzeria(client, reader);
                    break;
                }
                case "4":
                {
                    deletePizzeria(client, reader);
                    break;
                }
                case "5":
                {
                    updateBasePrise(client, reader);
                    break;
                }
                case "6":
                {
                    showAvailableOptionSets(client, reader);
                    break;
                }
                case "7":
                {
                    System.exit(1);
                    break;
                }
                default:
                    System.out.println("Wrong choice, try again!!!");
                    break;
			}
		}
	}
	
	private static void operations()
	{
		System.out.println("____________________________________");
		System.out.println("1. load properties file");
		System.out.println("2. show available pizzeria");
		System.out.println("3. Print pizzeria");
		System.out.println("4. Delete Pizzeria");
		System.out.println("5. Update base price");
		System.out.println("6. Add option");
		System.out.println("7. Exit");
	}
	
	private static void providePropertiesPath(BufferedReader reader, Client client) throws IOException
	{
		System.out.println("Enter the path of your file:");
		String path = reader.readLine();
		boolean isLoaded = client.loadProperties(path);
		if(!isLoaded)
			System.out.println("not uploaded");
		else
		    System.out.println("File uploaded successfully !");
	}
	private static void availablePizzerias(Client client)
	{
		ArrayList<String> pizzerias = client.availablePizzerias();
		if(!pizzerias.isEmpty())
		{
			for (String string : pizzerias)
				System.out.println(string);
		}
		else
			System.out.println("no available pizzerias");
	}
	
	private static void printPizzeria(Client client, BufferedReader reader) throws IOException
	{
		String choice;
		ArrayList<String> pizzerias = client.availablePizzerias();
		if(!pizzerias.isEmpty()) {
		    System.out.println("choose pizzeria to print below:");
		    availablePizzerias(client);
		    choice = reader.readLine();
		    PizzaConfig pizza = client.printPizzeria(choice);
	        pizza.print();
		}
		else
		    System.out.println(" no available pizzeria");
	}
	
	private static void deletePizzeria(Client client, BufferedReader reader) throws IOException
	{
		String choice;
		ArrayList<String> pizzerias = client.availablePizzerias();
        if(!pizzerias.isEmpty()) {
    		System.out.println("choose pizzeria to delete below:");
    		availablePizzerias(client);
    		choice = reader.readLine();
    		if (client.deletePizzeria(choice))
    		    System.out.println(choice +" Deleted successfully");
    		else
    		    System.out.println("Failed to delete "+choice);
        }else 
            System.out.println("no available pizzeria");
	}
	private static void updateBasePrise(Client client, BufferedReader reader) throws IOException
	{
		String choice;
		Double newPrice;
		ArrayList<String> pizzerias = client.availablePizzerias();
        if(!pizzerias.isEmpty()) {
    		System.out.println("choose pizzeria to update below:");
    		availablePizzerias(client);
    		choice = reader.readLine();
    		System.out.println("Enter new price:");
    		newPrice = Double.parseDouble(reader.readLine());
    		boolean isUpdated = client.updateBasePrice(choice, newPrice);
    		if (isUpdated)
    			System.out.println(choice +" updated successfully");
    		else
    			System.out.println(choice+" failed to update");
        }else
            System.out.println("no available pizzeria");
	}
	
	private static void showAvailableOptionSets(Client client, BufferedReader reader) throws IOException
	{
		String choice, option, pizzeria;
		double price;
		ArrayList<String> pizzerias = client.availablePizzerias();
        if(!pizzerias.isEmpty()) {
    		System.out.println("choose pizzeria:");
    		availablePizzerias(client);
    		pizzeria = reader.readLine();
    		System.out.println("    Select one of the following and add option:");
    		PizzaConfig pizza = client.printPizzeria(pizzeria);
            pizza.print();
    		choice = reader.readLine();
    		System.out.println("Enter option name:");
    		option = reader.readLine();
    		System.out.println("Enter price:");
    		price = Double.parseDouble(reader.readLine());
    		boolean isAdded = client.addOption(option, price, choice, pizzeria);
    		if (isAdded)
    			System.out.println("** option added successfully **");
    		else
    			System.out.println("** failed to add option, try again!! **");
        }else
            System.out.println("no available pizzeria");
	}
}

/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/
package server;

import java.net.*;

import strategy.Context;
import strategy.Factory;
import strategy.Strategy;
import wrapper.PizzeriaConfigAPI;

import java.io.*;

public class ClientHandler extends Thread
{
	static int clientNumber = 0;
	PizzeriaConfigAPI _api;
	Socket clientSocket;
	
	public ClientHandler(Socket socket) throws IOException
	{
        clientSocket = socket;
        clientNumber++;
        setName("Client " + clientNumber);
	}

	public void run()
	{    
	   try(
	           ObjectInputStream   request = new ObjectInputStream(clientSocket.getInputStream());
	           ObjectOutputStream   response = new ObjectOutputStream(clientSocket.getOutputStream()); )
	   {
	       Request clientRequest;
	       while((clientRequest = (Request) request.readObject()) != null)
	       {
    		   Strategy strategy = Factory.getStrategy(clientRequest);
    		   Context context = new Context(strategy);
    		   if (Server.api != null)
    		       context.executeStrategy(Server.api, response);
    		   else
    		       context.executeStrategy(Server.db, response);
	       }
	   }
	   catch (IOException | ClassNotFoundException e)
	   {
		   System.err.println("Exception caught when trying to read requests");
	       System.err.println(e.getMessage());
	   }
	}
	}

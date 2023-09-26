/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

package server;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

import wrapper.PizzeriaConfigDB;
import wrapper.PizzeriaConfigAPI;


public class Server
{
     static PizzeriaConfigAPI api = null;
     static PizzeriaConfigDB db = null;
    
	public static void main(String[] args) throws IOException
	{
	    db = new PizzeriaConfigDB();
	    Scanner sc = new Scanner(System.in);
	    System.out.println("NB: this program uses database as default storage");
	    System.out.println("Do you want to use LinkedHashMap instead? type Y(yes) or N(no)");
	    String choice = sc.nextLine();
	    sc.close();
	    if (choice.equalsIgnoreCase("Y"))
	    {
	        api = new PizzeriaConfigAPI();
	        db = null;
	    }
	        
	    int             acceptTimeout = -1;
	    ServerSocket    socket = null;

	    Socket          clientSocket;
	    ClientHandler   clientHandler;
	    
	    boolean running = true;
	    
	    if ((socket = openSocket(1111, acceptTimeout)) == null)
	        System.exit(2);
	    
	    System.out.println("Timeout: " + socket.getSoTimeout());
	    
	    while (running)
	    {
	        clientSocket = acceptConnection(socket);
	        if (clientSocket != null)
	        {
	            clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
	            System.out.println("Server now handling " + clientHandler.getName());
	        }
	    }

	    System.out.println("The Server is ending.");
	}

	private static ServerSocket openSocket(int portNumber, int acceptTimeout)
	{
	    ServerSocket    socket = null;
	    try
	    {
	        socket = new ServerSocket(portNumber);
	        if (acceptTimeout > 0)
	            socket.setSoTimeout(acceptTimeout);
	    }
	    catch (BindException e)
	    {
	        System.err.println("Server cannot bind to port " + portNumber);
	        System.err.println(e);
	        System.exit(3);
	    }
	    catch (Exception e)
	    {
	        System.err.println("Server caught exception when trying to create socket ");
	        System.err.println(e);
	    }

	    return socket;
	}

	private static Socket acceptConnection(ServerSocket socket)
	{
	    try
	    {
	        Socket clientSocket = socket.accept();     
	        System.out.println("Server connected to " + clientSocket);
	        return clientSocket;
	    }
	    catch (SocketTimeoutException e)
	    {
	        System.out.println("Server timed out waiting for a connection.");
	    }
	    catch (IOException e)
	    {
	        System.err.println("Server caught exception when trying to listen on port "
	            + socket.getLocalPort() );
	        System.err.println(e);
	    }
	    return null;
	}

	  
}

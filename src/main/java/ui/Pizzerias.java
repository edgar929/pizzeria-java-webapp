package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.Client;
import client.PizzeriaClient;

/**
 * Servlet implementation class Pizzerias
 */
@WebServlet("/Pizzerias")
public class Pizzerias extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Client client;
    public Pizzerias() {
        super();
        try {
			client = new Client("localhost", 1111);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		session.removeAttribute("client");
		session.setAttribute("client", client);
				
		ArrayList<String> pizzerias = client.availablePizzerias();
		String htmlRespone = "<html>";
        htmlRespone += "<form action='OrderPizzaServlet' method='GET'>";      
        htmlRespone += "<select name=\"pizzeria\" id=\"pizzeria\">";
        pw.println(htmlRespone);
        for (String string : pizzerias) {
        	pw.println("<option value="+string+ ">"+string+"</option>");
		}
        htmlRespone += "</select>";
        htmlRespone += "<button type='submit'> submit </submit>";
        htmlRespone += "</form>";
        htmlRespone += "</html>";
       
		pw.println(htmlRespone);
	
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		String selected = request.getParameter("pizzeria");
		pw.println(selected);
		pw.close();
	}

}

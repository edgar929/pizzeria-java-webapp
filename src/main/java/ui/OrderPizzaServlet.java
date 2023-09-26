package ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.Client;
import enums.DeliveryMode;
import enums.Size;
import model.Pizza;
import model.PizzaConfig;

/**
 * Servlet implementation class OrderPizzaServlet
 */
@WebServlet("/OrderPizzaServlet")
public class OrderPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	Client client;
	
    public OrderPizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("Text/html");
		PrintWriter pw = response.getWriter();
		client = (Client) request.getSession(false).getAttribute("client");
		String selected = request.getParameter("pizzeria");
		PizzaConfig pizza = client.printPizzeria(selected);
		request.setAttribute("pizzeria", pizza.getName());
		pw.println("Pizzeria: "+ pizza.getName());
		pw.println("Base price: "+ pizza.getBasePrice());
		ArrayList<String> optSet = pizza.availableOptionSets();
		pw.println( "<html>");
		pw.println( "<form action='' method='POST'>");   
		for (String string : optSet) {
			pw.println(string);
			LinkedHashMap<String, Double> options = pizza.availableOptions(string);
			Set<String> keys = options.keySet();

			for (String key : keys) {   
				 pw.println("<br><input type=\"checkbox\" id="+key+" name='option' value="+key+">");
				 pw.println( "<label for="+key+">"+key+" price: "+options.get(key)+"$</label><br>");
			}
		}
		pw.println("Delivery mode:");
		pw.println("<br><input type='radio' id='eatin' name='delivery' value="+DeliveryMode.EAT_IN.getDelivery()+">");
		pw.println("<label for='eatin'>Eat In</label><br>");
		pw.println("<input type='radio' id='delivery' name='delivery' value="+DeliveryMode.DELIVERY.getDelivery()+">");
		pw.println("<label for='delivery'>Delivery</label><br>");
		pw.println("<input type='radio' id='takeaway' name='delivery' value="+DeliveryMode.TAKEAWAY.getDelivery()+">");
		pw.println("<label for='takeaway'>Take Away</label><br>");
		
		pw.println("Size:");
		pw.println("<br><input type='radio' id='large' name='size' value="+Size.LARGE.get_size()+">");
		pw.println("<label for='large'>Large</label><br>");
		pw.println("<input type='radio' id='medium' name='size' value="+Size.MEDIUM.get_size()+">");
		pw.println("<label for='medium'>Medium</label><br>");
		pw.println("<input type='radio' id='small' name='size' value="+Size.SMALL.get_size()+">");
		pw.println("<label for='small'>Small</label><br>");
		
		pw.println("<button type='submit'> submit </submit>");
		pw.println( "</form>");
		pw.println("</html>");
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String options[] = request.getParameterValues("option");
		String deliveryMode = request.getParameter("delivery");
		
		String size = request.getParameter("size");
		String pizzeria = request.getParameter("pizzeria");
		PizzaConfig config = client.printPizzeria(pizzeria);
		ArrayList<String> optSet = config.availableOptionSets();
		double totalPrice = config.getBasePrice();
		PrintWriter out = response.getWriter();
		for (String string : optSet) {
			LinkedHashMap<String, Double> allOptions = config.availableOptions(string);
			out.println(string);
			Set<String> keys = allOptions.keySet();

			for (int i =0; i<options.length; i ++) {
				if(keys.contains(options[i]))
					totalPrice += (double) allOptions.get(options[i]);
			}
		}
		
		totalPrice += DeliveryMode.fromString(deliveryMode).getPrice();
		totalPrice += Size.fromString(size).getPrice();
		Pizza pizza = new Pizza(pizzeria, DeliveryMode.fromString(deliveryMode), Size.fromString(size), options , totalPrice);
		
		request.setAttribute("ingredients", options);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("size", size);
		request.setAttribute("pizzeria", pizzeria);
		request.setAttribute("deliveryMode", deliveryMode);
		client.OrderPizza(pizza);
		request.getRequestDispatcher("OrderedPizza.jsp").forward(request, response);
	}

}

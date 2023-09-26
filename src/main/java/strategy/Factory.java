package strategy;

import enums.Requests;
import server.Request;

public class Factory
{
	public static Strategy getStrategy(Request request)
	{
	    
		if (request.getRequest().equalsIgnoreCase(Requests.UPLOAD_PROPERTY.get_request()))
			return new LoadPropertyStrategy(request);
		
		if (request.getRequest().equalsIgnoreCase(Requests.AVAILABLE_PIZZERIAS.get_request()))
		    return new AvailablePizzeriasStrategy();
		
		if (request.getRequest().equalsIgnoreCase(Requests.PRINT_PIZZERIA.get_request()))
		    return new PrintPizzeriaStrategy(request);
		
		if (request.getRequest().equalsIgnoreCase(Requests.UPDATE_BASEPRICE.get_request()))
            return new UpdateBasePriceStrategy(request);
		
		if (request.getRequest().equalsIgnoreCase(Requests.DELETE_PIZZERIA.get_request()))
            return new DeletePizzeriaStrategy(request);
		
		if (request.getRequest().equalsIgnoreCase(Requests.ADD_OPTION.get_request()))
            return new AddOptionStrategy(request);
		
		if (request.getRequest().equalsIgnoreCase(Requests.ORDER_PIZZERIA.get_request()))
			return new OrderPizzeriaStrategy(request);
		
		return null;
	}
}

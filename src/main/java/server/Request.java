/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/

package server;

import java.io.Serializable;

import model.Pizza;

public class Request implements Serializable
{
    private String request;
    private Serializable object;
    private Pizza pizza;
    
    public Request(String req, Serializable obj)
    {
        request = req;
        object = obj;
    }
    
    public Request(String req, Pizza pizz)
    {
        request = req;
        pizza = pizz;
    }

    public Request(String req)
    {
        request = req;
    }
    public Serializable getObject() {
        return object;
    }
    
    public String getRequest() {
        return request;
    }
    
    public Pizza getPizza() {
		return pizza;
	}
    
}

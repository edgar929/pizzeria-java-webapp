package io;
/*
On my honor, as a Carnegie-Mellon Africa student, I have neither given nor received unauthorized assistance on this work. 

@author Edgar shumbusho
*/
import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;

import model.PizzaConfig;

public class BuildConfig
{
  public PizzaConfig buildPizzaConfig(String filename) throws IOException
  {
	  PizzaConfig pizza = new PizzaConfig();
	 
	  FileReader file = new FileReader(filename);
      BufferedReader buff = new BufferedReader(file);
      String line;
 
      line = buff.readLine();
      String optionSet = "";
      String option = "";
      double price;
      
      while (line != null)
      {
          StringTokenizer st = new StringTokenizer(line,",");  
           while (st.hasMoreTokens()) {  
               String str= st.nextToken();
               if (str.contains("pizzaConfig"))
                   pizza.setName(str.substring(str.lastIndexOf(":") + 2));
               if (str.contains("basePrice"))
                   pizza.setBasePrice(Integer.parseInt(str.substring(str.lastIndexOf(":") + 2)));
               if (str.contains("optionSet"))
               {
                   optionSet = str.substring(str.lastIndexOf(":") + 2);
                   pizza.addOptionSet(optionSet);
               }
               if (str.contains("option"))
                   option = str.substring(str.lastIndexOf(":") + 2);
               if (str.contains("price"))
               {
                   price = Double.parseDouble(str.substring(str.lastIndexOf(":") + 2));
                   pizza.addOption(option, price, optionSet);
               }
               
           }  
          line = buff.readLine();
      }
      buff.close();
   
	  return pizza;
  }
  
  public PizzaConfig parseProperties(Properties property)
  {
	  PizzaConfig pizza = new PizzaConfig();
	  String optionSet = "";
      String option = "";
      double price;
      
	  if(!property.equals(null))
	  {
		  pizza.setName(property.getProperty("Pizzeria"));
		  pizza.setBasePrice(Double.parseDouble(property.getProperty("BasePrice")));
		  optionSet = property.getProperty("OptionSet1");
		  option = property.getProperty("Option1a");
		  price = Double.parseDouble(property.getProperty("Price1a"));
		  pizza.addOptionSet(optionSet);
		  pizza.addOption(option, price, optionSet);
		  option = property.getProperty("Option1b");
		  price = Double.parseDouble(property.getProperty("Price1b"));
		  pizza.addOption(option, price, optionSet);
		  
		  optionSet = property.getProperty("OptionSet2");
		  option = property.getProperty("Option2a");
		  price = Double.parseDouble(property.getProperty("Price2a"));
		  pizza.addOptionSet(optionSet);
		  pizza.addOption(option, price, optionSet);
		  option = property.getProperty("Option2b");
		  price = Double.parseDouble(property.getProperty("Price2b"));
		  pizza.addOption(option, price, optionSet);
	  }
	  return pizza;
  }
}



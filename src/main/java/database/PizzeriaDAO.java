package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Map.Entry;

import model.Pizza;
import model.PizzaConfig;

public class PizzeriaDAO extends DbConnection implements PizzeriaDaoInterface
{
   public PizzeriaDAO() {
      connect();
   }
   
   public boolean createTables() 
   {
	   boolean isCreated= false;
       String table1 = "create table if not exists pizzerias ("
               + "id SERIAL,"
               + "name varchar(25),"
               + "baseprice DOUBLE PRECISION ,"
               + "PRIMARY KEY(name));";
       
       String table2 = "create table if not exists optionsets ("
               + "id SERIAL,"
               + "name varchar(25) primary key)";
       
       String table3 = "create table if not exists pizzeria_optionset ("
               + "id SERIAL,"
               + "pizzeria varchar(25),"
               + "optionset varchar(25),"
               + "primary key(pizzeria, optionset),"
               + "foreign key(pizzeria) references pizzerias(name) ON DELETE CASCADE,"
               + "foreign key(optionset) references optionsets(name));";
       
       String table4 = "create table if not exists options ("
               + "id SERIAL,"
               + "name varchar(25),"
               + "price DOUBLE PRECISION ,"
               + "optionset varchar(25),"
               + "pizzeria varchar(25),"
               + "PRIMARY KEY(name, optionset, pizzeria),"
               + "foreign key(pizzeria, optionset) references pizzeria_optionset(pizzeria, optionset) ON DELETE CASCADE);";
       
       String table5 = "create table if not exists orders ("
               + "id SERIAL,"
               + "pizzeria varchar(25),"
               + "ingredient varchar(255),"
               + "deliveryMode varchar(25),"
               + "size varchar(25),"
               + "totalPrice DOUBLE PRECISION ,"
               + "primary key(id));";
       
       String table6 = "create table if not exists order_details ("
    		   + "id SERIAL,"
               + "order_id int,"
               + "ingredient varchar(25) ,"
               + "price DOUBLE PRECISION ,"
               + "PRIMARY KEY(id),"
               + "foreign key(order_id) references orders(id));";
       
       try {
    	   preparedStatement = connection.prepareStatement(table1);
    	   preparedStatement.executeUpdate();
	       preparedStatement = connection.prepareStatement(table2);
	       preparedStatement.executeUpdate();
	       preparedStatement = connection.prepareStatement(table3);
	       preparedStatement.executeUpdate();
	       preparedStatement = connection.prepareStatement(table4);
	       preparedStatement.executeUpdate();
	       preparedStatement = connection.prepareStatement(table5);
	       preparedStatement.executeUpdate();
	       preparedStatement.close();
	       isCreated = true;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return isCreated;
   }
   
   public boolean createPizzeria(PizzaConfig pizzaConfig)
   {
	   boolean isCreated = false;
       try 
       {
           String insert = "insert into pizzerias(name, baseprice) values(?,?)";
           preparedStatement = connection.prepareStatement(insert);
           preparedStatement.setString(1, pizzaConfig.getName());
           preparedStatement.setDouble(2, pizzaConfig.getBasePrice());
           preparedStatement.executeUpdate();
           preparedStatement.close();
           isCreated = true;
           if(!pizzaConfig.availableOptionSets().isEmpty())
           {
               ArrayList<String> optionSets = pizzaConfig.availableOptionSets();
               
               for (String optionSet : optionSets) {
                   createOptionSet(pizzaConfig.getName(), optionSet);
                   if(pizzaConfig.availableOptions(optionSet)!=null) {
                       LinkedHashMap<String, Double> options = pizzaConfig.availableOptions(optionSet);
                       Set<String> keys = options.keySet();
                       for(String key: keys) {
                           createOption(key, options.get(key), optionSet, pizzaConfig.getName());
                       }
                   }
               }
               isCreated = true;
           }
        } 
        catch (SQLException e) 
        {
           e.printStackTrace();
        }  
       return isCreated;
   }
   

   public PizzaConfig getPizzeria(String pizzeriaName)
   {
       PizzaConfig config = new PizzaConfig();
       try 
       {
           String sql = "SELECT p.name as name, p.baseprice as baseprice, o.optionset as optionset_name FROM public.pizzeria_optionset o "
           		+ "left join public.pizzerias p ON p.name = o.pizzeria where p.name=? ";
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1, pizzeriaName);
           resultSet = preparedStatement.executeQuery();
           
           
           while(resultSet.next()){
               config.setName(resultSet.getString(1));
               config.setBasePrice(resultSet.getDouble(2));
               config.addOptionSet(resultSet.getString(3));
               String optSet = resultSet.getString(3);
               LinkedHashMap<String, Double> options = availableOptions(resultSet.getString(3), resultSet.getString(1));
               Set<String> keys = options.keySet();
               for(String key: keys) {
                   config.addOption(key, options.get(key), optSet);
               }
           }
           preparedStatement.close();
           return config;
       }
       catch (SQLException ex) {
           ex.printStackTrace();
       }
       return null;
   }
   public boolean createOptionSet(String pizzeria, String optionSet)
   {
       boolean created = false;
       try 
       {
           String insertOptionSet = "insert into optionsets (name) values(?)";
           
           String qry = "insert into pizzeria_optionset(pizzeria, optionset) values(?,?)";
           
           if( isOptionSetExists(optionSet)) {
               
               preparedStatement = connection.prepareStatement(qry);
               preparedStatement.setString(1, pizzeria);
               preparedStatement.setString(2, optionSet);
               preparedStatement.executeUpdate();  
               preparedStatement.close();  
           } 
           else
           {
               preparedStatement = connection.prepareStatement(insertOptionSet);
               preparedStatement.setString(1, optionSet);
               preparedStatement.executeUpdate(); 
               preparedStatement = connection.prepareStatement(qry);
               preparedStatement.setString(1, pizzeria);
               preparedStatement.setString(2, optionSet);
               preparedStatement.executeUpdate(); 
               preparedStatement.close();
           }
           created = true;
       }
       catch (SQLException ex) {
           ex.printStackTrace();
       }
       
       return created;
   }
   
   public boolean createOption(String option, double price, String optionSet, String pizzeria)
   {
       boolean created = false;
       try 
       {
           String insertOption = "insert into options (name, price, optionset,pizzeria) values(?,?,?,?)";
           preparedStatement = connection.prepareStatement(insertOption);
           preparedStatement.setString(1, option);
           preparedStatement.setDouble(2, price);
           preparedStatement.setString(3, optionSet);
           preparedStatement.setString(4, pizzeria);
           preparedStatement.executeUpdate();  

           preparedStatement.close();  
           created = true;
       }
       catch (SQLException ex) {
           ex.printStackTrace();
       }
       return created;
   }
   
   public boolean isOptionSetExists(String optionset) 
   {
       String sql = "select * from optionsets where name = ?";
       boolean created = false;
       try 
       {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, optionset);
			resultSet = preparedStatement.executeQuery();
	       
	       if (resultSet.next() == false)
	           return false;
	       
	       preparedStatement.close();
	       created = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
       
       return created;
       
   }
   
   public ArrayList<String> availablePizzerias()
   {
	   ArrayList<String> pizzerias = new ArrayList<>();
	   String qry = "select * from pizzerias";
	   try {
			preparedStatement = connection.prepareStatement(qry);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				pizzerias.add(resultSet.getString(2));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	   
       return pizzerias;
   }
   
   public LinkedHashMap<String, Double> availableOptions(String set, String pizzeria)
   {
	   LinkedHashMap<String, Double> options = new LinkedHashMap<>();
	   String sql = "select * from options where optionset = ? and pizzeria = ?";
	   try 
	   {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, set);
			preparedStatement.setString(2, pizzeria);
		    ResultSet resultSet1 = preparedStatement.executeQuery();
		    
		    while(resultSet1.next())
		    {
		    	options.put(resultSet1.getString(2), resultSet1.getDouble(3));
		    }
		    preparedStatement.close();
		} 
	   	catch (SQLException e) 
	   	{
			e.printStackTrace();
		}
       
       return options;
	   
   }
   
   public ArrayList<String> availableOptionSets(String pizzeria)
   {
	   ArrayList<String> optionsets = new ArrayList<>();
	   String qry = "select * from pizzeria_optionset where pizzeria=?";
	   try {
			preparedStatement = connection.prepareStatement(qry);
			preparedStatement.setString(1, pizzeria);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				optionsets.add(resultSet.getString(3));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
       return optionsets;
   }
   
   public boolean createOrder(Pizza pizza)
   {
	   boolean created = false;
       try 
       {
           String insertOrder = "insert into orders (pizzeria, ingredient, deliveryMode, size, totalPrice) values(?,?,?,?,?)";
           preparedStatement = connection.prepareStatement(insertOrder);
           ArrayList<String> ingredients = pizza.getIngredients();
           String options = "";
           for (String ingr : ingredients) 
           {
        	   options += (ingr +", ");
        	   
           }
           preparedStatement.setString(1, pizza.getName());
    	   preparedStatement.setString(2, options);
           preparedStatement.setString(3, pizza.getDelivery());
           preparedStatement.setString(4, pizza.getSize());
           preparedStatement.setDouble(5, pizza.getPrice());
           preparedStatement.executeUpdate();
             created = true;
           preparedStatement.close();
       }
       catch (SQLException ex) {
           ex.printStackTrace();
       }
       return created;
   }
   
   public double getOptionPrice(String option, String optSet, String pizzeria) {
	   String sql = "select price from options where name = ? and optionset = ? and pizzeria = ?";
	   double price = 0;
       try {
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, option);
		resultSet = preparedStatement.executeQuery();
	   
	    if (resultSet.next())
	       price = resultSet.getDouble(1);
	       
	   preparedStatement.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
       
       return price;
   }
   
   public boolean createOrderDetail(int id, String ingredient, double price)
   {
	   boolean created = false;
       try {
    	String insertOrderDetails = "insert into order_details (order_id, ingredient, price) values(?,?,?)";
		preparedStatement = connection.prepareStatement(insertOrderDetails);
	    preparedStatement.setInt(1, id);
        preparedStatement.setString(2, ingredient);
        preparedStatement.setDouble(4, price);
		preparedStatement.executeUpdate();
		created = true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
       return created;
   }
   
   public boolean updateBasePrice(String pizzeria, double newPrice)
   {
	   boolean updated =false;
	   String update = "update pizzerias set baseprice=? where name=?;";
       try {
		preparedStatement = connection.prepareStatement(update);
		preparedStatement.setDouble(1, newPrice);
	    preparedStatement.setString(2, pizzeria);
	    preparedStatement.executeUpdate();
	    preparedStatement.close();
	    updated = true;
	} catch (SQLException e) {
		e.printStackTrace();
	}
      return updated;
   }

	@Override
	public boolean deletePizzeria(String pizzeria) 
	{
		boolean created = false;
	       try 
	       {
	           String insertOption = "delete from pizzerias where name=?";
	           preparedStatement = connection.prepareStatement(insertOption);
	           preparedStatement.setString(1, pizzeria);
	           preparedStatement.executeUpdate();  

	           preparedStatement.close();  
	           created = true;
	       }
	       catch (SQLException ex) {
	           ex.printStackTrace();
	       }
	       return created;
	}

    
}

package database;

import java.sql.SQLException;

import model.PizzaConfig;

public class DaoPatternTest {
	  public static void main(String[] args) throws SQLException 
	  {
		  PizzeriaDAO dao = new PizzeriaDAO();
		  DbConnection db = new DbConnection();
		  System.out.println("1. connecting the database");
		  db.connect();
		  System.out.println("______________________________________");
		  boolean isCreated;
		  System.out.println("2. creating tables");
		  isCreated=dao.createTables();
		  if (isCreated)
			  System.out.println("Tables are created successfully !!");
		  else
			  System.out.println("Failed to create tables !!");
//		  
//		  System.out.println("__________________________________");
//		  System.out.println("3. creating pizza config");
//		  PizzaConfig config = new PizzaConfig("camellia", 3000);
//		  config.addOptionSet("meatchoice");
//		  config.addOption("beef", 4000, "meatchoice");
//		  isCreated=dao.createPizzeria(config);
//		  if (isCreated)
//			  System.out.println("pizzeria are created successfully !!");
//		  else
//			  System.out.println("Failed to create pizzeria !!");
//		  
//		  System.out.println("__________________________________");
//		  System.out.println("4. Update baseprice");
//		  isCreated = dao.updateBasePrice("camellia", 1000);
//		  if (isCreated)
//			  System.out.println("pizzeria baseprice are updated successfully !!");
//		  else
//			  System.out.println("Failed to update pizzeria !!");
//		  
//		  System.out.println("__________________________________");
//		  System.out.println("5. Delete pizzeria");
//		  isCreated = dao.deletePizzeria("camellia");
//		  if (isCreated)
//			  System.out.println("pizzeria deleted are updated successfully !!");
//		  else
//			  System.out.println("Failed to delete pizzeria !!");
//		  
		  
	}
}

package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbConnection {

    private final static String url = "jdbc:postgresql://localhost:5432/postgres";
    private final static String user = "postgres";
    private final static String password = "123456";

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
 
    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            if(connection != null) {
                System.out.println("connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                 ex.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                                ex.printStackTrace();

            }
        }
    }

}

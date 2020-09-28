package syn.vtip302.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class launch {

    public static void main(String[] args) {
       
        try {
            
            String url = "jdbc:mysql://localhost:3306/computer?&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

            Properties authorization = new Properties();
            authorization.put("user", "root"); 
            authorization.put("password", "PassW0Rd"); 
            
            Connection connection = DriverManager.getConnection(url, authorization);
           
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            ResultSet table = statement.executeQuery("SELECT * FROM `computer properties`");

            table.first(); 
            for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                System.out.print(table.getMetaData().getColumnName(j) + "\t\t");
            }
            System.out.println();

            table.beforeFirst(); 
            while (table.next()) {
                for (int j = 1; j <= table.getMetaData().getColumnCount(); j++) {
                    System.out.print(table.getString(j) + "\t\t");
                }
                System.out.println();
            }

            if (table != null) {
                table.close();
            } 
            if (statement != null) {
                statement.close();
            } 
            if (connection != null) {
                connection.close();
            } 

        } catch (SQLException e) {
            System.err.println("Error accessing database!");
        }
    }
}

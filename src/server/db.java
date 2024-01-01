package server;

import java.sql.*;

public class db {
    private static String JDBC_URL = "jdbc:sqlserver://localhost:53655;databaseName=Expenses;IntegratedSecurity=true;trustServerCertificate=true";
    private static String USERNAME = "sa";
    private static String PASSWORD = "sa@123456";
    public Connection connection;

    public db(){
        try{
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Connected");

        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();

        }
    }
    public void close_connection(){
        try {
            connection.close();
        }catch (SQLException e){
            System.out.println("error");
            e.printStackTrace();
        }

    }
    public  void open_connection(){
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }catch (SQLException e){
            System.out.println("error");
            e.printStackTrace();
        }
    }

//    // replace with your stored procedure name
//    String storedProcedure = "{call get_expenses(?,?)}";
//            try (CallableStatement callableStatement = connection.prepareCall(storedProcedure)) {
//
//        // Set input parameters if any
//        callableStatement.setString(1, "john_doe");
//        callableStatement.setString(2, "G");
//
//        // Execute the stored procedure
//        callableStatement.execute();
//
//        // Retrieve output parameters or result set
//        // Example: If the stored procedure returns a result set
//        try (ResultSet resultSet = callableStatement.getResultSet()) {
//            while (resultSet.next()) {
//                // Process the result set data
//                int budgetId = resultSet.getInt("bid");
//                String budgetName = resultSet.getString("tag_name");
//                String budgetLimit = resultSet.getString("username");
//
//
//                System.out.println("Budget ID: " + budgetId);
//                System.out.println("tag Name: " + budgetName);
//                System.out.println("username: " + budgetLimit);
//
//            }
//        }
//
//    } catch (SQLException e){
//        System.out.println("Error");
//        e.printStackTrace();
//    }

}

package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class db {
    private static String JDBC_URL = "jdbc:sqlserver://localhost:53655;databaseName=Expenses;IntegratedSecurity=true;trustServerCertificate=true";
    private static String USERNAME = "sa";
    private static String PASSWORD = "sa@123456";
    public Connection connection;

    public String LogUser ="renamrsh";
    private List<String> tag_options = new ArrayList<>();
    private List<List<String>> budget_options = new ArrayList<>();

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
    //for what is this function? checking if connection is open?
    public  void open_connection(){
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }catch (SQLException e){
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public void executeStoredProcedure() {
        String storedProcedure_gtags = "{call get_tags(?,?)}";
        String storedProcedure_gbudget = "{call get_budget_data(?,?)}";
        try (CallableStatement callableStatement = connection.prepareCall(storedProcedure_gtags)) {

            callableStatement.setString(1, LogUser);
            callableStatement.setString(2, "G");

            callableStatement.execute();


            try (ResultSet resultSet = callableStatement.getResultSet()) {
                while (resultSet.next()) {

                    String tag_name = resultSet.getString("tag_name");
                    tag_options.add(tag_name);

                }
            }
        } catch (SQLException e) {
            System.out.println("Error");
            //e.printStackTrace();
        }
        try (CallableStatement callableStatement_budget = connection.prepareCall(storedProcedure_gbudget)) {

            callableStatement_budget.setString(1, LogUser);
            callableStatement_budget.setString(2, "G");

            callableStatement_budget.execute();

            List<String> budget_name_list = new ArrayList<>();
            List<String> budget_data_list = new ArrayList<>();

            try (ResultSet resultSet = callableStatement_budget.getResultSet()) {
                while (resultSet.next()) {

                    String budget_name = resultSet.getString("name");
                    budget_name_list.add(budget_name);
                    String budget_data = resultSet.getString("currency") + resultSet.getString("limit");
                    budget_data_list.add(budget_data);


                }
            }
            //System.out.println(budget_data_list);
            budget_options.add(budget_name_list);
            budget_options.add(budget_data_list);

        } catch (SQLException e) {
            System.out.println("Error");
            //e.printStackTrace();
        }
    }


    public List<String> getTagOptions() {
        //System.out.println(tag_options);
        return tag_options;
    }
    public List<List<String>> getBudgetOptions() {
        //System.out.println(budget_options);
        return budget_options;
    }
    public void executeEditingExpensesProcedure(String username, Integer eid, String comment, int amount, String tagname, int bid, char operation) {
        String storedProcedure_editing_expenses = "{call editing_expenses(?, ?, ?, ?, ?, ?, ?)}";

        try (CallableStatement callableStatement = connection.prepareCall(storedProcedure_editing_expenses)) {
            callableStatement.setString(1, username);
            callableStatement.setInt(2, eid);
            callableStatement.setString(3, comment);
            callableStatement.setInt(4, amount);
            callableStatement.setString(5, tagname);
            callableStatement.setInt(6, bid);
            callableStatement.setString(7, String.valueOf(operation));

            callableStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
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

package com.jdbc.day1;

import java.sql.*;

public class BasicTest {

    public static void main(String[] args) throws SQLException {
        final String DB_URL = "jdbc:oracle:thin:@54.227.18.240:1521:xe";
        final String username = "hr";
        final String password = "hr";

        Connection connection = DriverManager.getConnection(DB_URL, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = statement.executeQuery("SELECT * FROM employees");

        while (result.next()) {
            System.out.println("| " + result.getString(1) +" | " + result.getString(2) +
                    " " + result.getString(3) +" | " + result.getString("salary"));
        }
//        result.beforeFirst();

//        while (result.next()) {
//            System.out.println(result.getString("salary"));
//        }


        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSetMetaData resultSetMetaData = result.getMetaData();

        System.out.println("JDBC Driver: "+databaseMetaData.getDriverName());
        System.out.println("JDBC Driver version: "+databaseMetaData.getDriverVersion());
        System.out.println("Database name: "+databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: "+databaseMetaData.getDatabaseProductVersion());

        System.out.println("========================================");
        //iterate rows
        for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
            System.out.printf("%-20s", resultSetMetaData.getColumnName(columnIndex));
        }
        result.beforeFirst();
        System.out.println("");
        while (result.next()) {
            //iterate columns
            for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                System.out.printf("%-20s", result.getString(columnIndex));
            }
            System.out.println("");
        }
        result.close();
        statement.close();
        connection.close();
    }
}

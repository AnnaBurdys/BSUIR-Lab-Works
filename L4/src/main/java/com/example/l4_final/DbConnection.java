package com.example.l4_final;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public Connection Connect () throws ClassNotFoundException, SQLException {
         String user = "root";
         String password = "user1234";
         String url = "jdbc:mysql://localhost:3306/books";
         Class.forName("com.mysql.cj.jdbc.Driver");
         Connection conn = DriverManager.getConnection(url,user,password);
         return conn;
    }
}

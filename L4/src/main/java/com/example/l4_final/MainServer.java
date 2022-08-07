package com.example.l4_final;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainServer {

        private static Connection connection;

        public static final String USER_NAME = "root";
        public static final String PASSWORD = "user1234";
        public static String URL = "jdbc:mysql://localhost:3306/books";
        public static int clientNum = 0;

        public static void main(String[] args) throws Exception {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("Database connection successful");
                System.out.println("Waiting for connection..");

                try (ServerSocket serverSock = new ServerSocket(1234)) {
                    while (true) {
                        Socket clientSock = serverSock.accept();
                        clientNum += 1;
                        System.out.println("Connection successful");
                        MyThread myThread = new MyThread(connection, clientSock);
                        myThread.setName(String.valueOf(clientNum));
                        myThread.start();
                    }
                } finally {
                    System.out.println("Server is closed");
                    connection.close();
                    System.out.println("Database connection finished");
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }
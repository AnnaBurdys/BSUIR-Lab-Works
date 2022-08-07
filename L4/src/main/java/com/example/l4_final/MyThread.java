package com.example.l4_final;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyThread extends Thread {
    private static Socket clientSock;
    private static Connection connection;
    private static Statement statement;

    public MyThread(Connection connection, Socket clientSock) {
        MyThread.connection = connection;
        MyThread.clientSock = clientSock;
    }

    private boolean isInsertQuery(String string) {
        String[] array = string.split(" ");
        return array[0].trim().equals("INSERT");
    }

    private boolean isSelectQuery(String string) {
        String[] array = string.split(" ");
        return array[0].trim().equals("SELECT");
    }

    private boolean isDeleteQuery(String string) {
        String[] array = string.split(" ");
        return array[0].trim().equals("DELETE");
    }

    private boolean isUpdateQuery(String string) {
        String[] array = string.split(" ");
        return array[0].trim().equals("UPDATE");
    }

    @Override
    public void run() {
        try {
            while (true) {
                final DataInputStream inputStream = new DataInputStream(clientSock.getInputStream());
                final DataOutputStream outputStream = new DataOutputStream(clientSock.getOutputStream());

                String input = "";
                String output = "error";
                while (true) {
                    input = inputStream.readUTF();
                    if (isInsertQuery(input)) {
                        statement = connection.createStatement();
                        statement.executeUpdate(input);
                    } else if (isSelectQuery(input)) {
                        statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(input);
                        while (resultSet.next()) {
                            String bookId = resultSet.getString("BookId");
                            String bookName = resultSet.getString("Name");
                            String authorName = resultSet.getString("Author");
                            String year = resultSet.getString("Year");
                            String genre = resultSet.getString("Genre");
                            String price = resultSet.getString("Price");
                            String rate = resultSet.getString("Rate");
                            output = bookId + " " +
                                    bookName + " " +
                                    authorName + " " +
                                    year + " " +
                                    genre + " " +
                                    price + " " +
                                    rate;
                        }
                        outputStream.writeUTF(output);
                        outputStream.flush();
                    } else if (isDeleteQuery(input)) {
                        statement = connection.createStatement();
                        statement.executeUpdate(input);
                    } else if (isUpdateQuery(input)) {
                        statement = connection.createStatement();
                        statement.executeUpdate(input);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
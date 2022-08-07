package com.example.l2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс - сервер, принимает запросы от клиентов и отдает данные
 */
public class Server {
    public static void main(String args[]) {
        int port = 1777;

        try {
            ServerSocket servSocket = new ServerSocket(port);

            while (true) {
                System.out.println("Waiting for a connection on " + port);

                Socket fromClientSocket = servSocket.accept();

                try (Socket localSocket = fromClientSocket;
                     PrintWriter pw = new PrintWriter(localSocket.getOutputStream(), true);
                     BufferedReader br = new BufferedReader(new InputStreamReader(localSocket.getInputStream()))) {

                    String str;
                    while ((str = br.readLine()) != null) {
                        System.out.println("The message: " + str);
                        if (str.equals("End of connection")) {
                            pw.println("End of connection");
                            break;
                        } else {
                            StringBuilder buffer = new StringBuilder(str);
                            buffer.reverse();
                            str = "Server returns: " + buffer;
                            pw.println(str);
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
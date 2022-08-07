package com.example.l3;

import java.io.IOException;
import java.net.*;
import java.text.DecimalFormat;

import static java.lang.Math.pow;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server();
    }

    float sum1 = 0, sum2 = 0;
    DatagramSocket st;
    byte[] buf = new byte[100]; // массив для передачи данных клиенту
    DatagramPacket dp = new DatagramPacket(buf, 100); // дейтаграмма с данными для клиента

    Server() throws IOException, InterruptedException {
        while (true) {
        st = new DatagramSocket(12345); // создание нового сокета

            int a, b, c;
            st.receive(dp);
            String str = new String(dp.getData());
            str = str.substring(0, dp.getLength());
            System.out.println("number " + str + " received as a");
            a = Integer.parseInt(str);

            st.receive(dp);
            str = new String(dp.getData());
            str = str.substring(0, dp.getLength());
            System.out.println("number " + str + " received as b");
            b = Integer.parseInt(str);

            st.receive(dp);
            str = new String(dp.getData());
            str = str.substring(0, dp.getLength());
            System.out.println("number " + str + " received as c\n\n");
            c = Integer.parseInt(str);
            if ((a<b)&&(b<c)&&(a>1)) {
    Thread t1 = new Thread(() -> {
        for (int i = a; i < b; i++) {
            sum1 += (pow(i, 2) + i - 1);
        }
    }), t2 = new Thread(() -> {
        for (int i = b; i < c; i++) {
            sum2 += (float) (5 / (3 * i - 1));
        }
    });
    t1.start();
    t2.start();
    t1.join();
    t2.join();
}
            sendBack();  // вызов метода для отправки данных клиенту
        }
    }

    private void sendBack() throws IOException {
        double totalSum = sum1 - sum2;
        DecimalFormat df = new DecimalFormat("####.####"); // определение формата вывода
        df.format(totalSum);
        String str = String.valueOf(totalSum);
        byte[] send = str.getBytes(); // строка записывается в массив
        dp = new DatagramPacket(send, send.length, InetAddress.getByName("localhost"), 12346);
        st.send(dp);
        st.close();
    }
}
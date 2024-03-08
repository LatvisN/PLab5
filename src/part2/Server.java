package part2;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket server = new DatagramSocket(1111);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            server.receive(receivePacket);
            String message = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();



            String[] perem = message.split(",");
            double x = Double.parseDouble(perem[0]);
            System.out.println(x);
            double y = Double.parseDouble(perem[1]);
            System.out.println(y);
            //double z = Double.parseDouble(perem[2]);

            double f = 5*Math.atan(x)- (double) 1 / 4 *Math.cos((x + 3 * Math.abs(x-y) + Math.pow(x, 2))/(Math.pow(Math.abs(x+Math.pow(y,2)),3) + Math.pow(x, 3)));
            System.out.println(f);

            String answer = String.valueOf(f);
            sendData = answer.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            server.send(sendPacket);

            BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true));
            //writer.write(x + " " + y + " " + z + " " + answer + "\n");
            writer.write(x + " " + y + " " + answer + "\n");
            writer.close();

            //System.out.println("Client connected from " + IPAddress + ":" + port);


        }
    }

}


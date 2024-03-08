package part2;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData;
        byte[] receiveData = new byte[1024];
        Scanner scanner = new Scanner(System.in);
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        //double z = scanner.nextDouble();
        scanner.close();
        String message = x + "," + y;
        sendData = message.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1111);
        client.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        client.receive(receivePacket);
        String response = new String(receivePacket.getData());
        System.out.println(response.trim());
        client.close();

    }
}


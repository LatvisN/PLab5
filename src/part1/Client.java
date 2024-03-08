package part1;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    public static void main(String[] arg) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("server connecting....");
            Socket clientSocket = new Socket("127.0.0.1",2525);
            System.out.println("connection established....");
            BufferedReader stdin =
                    new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos =
                    new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream  cois =
                    new ObjectInputStream(clientSocket.getInputStream());



            System.out.println("Enter any number to send to server \n\t('0' − programme terminate)");
            int clientMessage;
            clientMessage = in.nextInt();
            System.out.println("you've entered: "+clientMessage);
            while(clientMessage != 0) {
                coos.writeObject(clientMessage);
                System.out.println("~server~: "+cois.readObject());
                System.out.println("-------------------------------------");
                clientMessage = in.nextInt();
                System.out.println("you've entered: "+clientMessage);
            }



            coos.close();
            cois.close();
            clientSocket.close();
        }catch(Exception e)	{
            e.printStackTrace();
        }
    }
}

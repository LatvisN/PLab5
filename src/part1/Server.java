package part1;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Server
{
    public static void main(String[] arg)
    {
        ServerSocket serverSocket = null;
        Socket clientAccepted     = null;
        ObjectInputStream  sois   = null;
        ObjectOutputStream soos   = null;
        try {
            System.out.println("server starting....");
            serverSocket = new ServerSocket(2525);
            clientAccepted = serverSocket.accept();
            System.out.println("connection established....");
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());


            Random random = new Random();
            int[] arr;
            arr = new int[10];
            int clientMessageRecieved;
            clientMessageRecieved = (int)sois.readObject();
            while(clientMessageRecieved != 0)
            {
                System.out.println("message recieved: '"+clientMessageRecieved+"'");
                int maxnumber = clientMessageRecieved;
                IntStream.range(0, 10)
                        .forEach(index -> arr[index] = random.nextInt(1, maxnumber + 1));
                System.out.println(Arrays.toString(arr));
                String arraySend = Arrays.toString(arr);
                soos.writeObject(arraySend);
                clientMessageRecieved = (int)sois.readObject();
            }



        }catch(Exception e)	{

        } finally {
            try {
                sois.close();
                soos.close();
                clientAccepted.close();
                serverSocket.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

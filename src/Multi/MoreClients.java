package Multi;

import java.io.*;
import java.net.*;
import java.util.*;

class MoreClients {
    public static void main(String[] args)
    {
        try (Socket socket = new Socket("localhost", 1234)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);
            String line = null;
            System.out.println("Connected to server. \n");
            System.out.println("Введите число.");


            while (!"0".equalsIgnoreCase(line)) {
                line = sc.nextLine();
                out.println(line);
                out.flush();
                System.out.println("Server replied: " + in.readLine());
            }



            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

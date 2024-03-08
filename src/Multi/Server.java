package Multi;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Arrays;
import java.util.stream.IntStream;

class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
            System.out.println("Server started. \n");

            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected: " + client.getInetAddress().getHostAddress());
                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }


        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                Random random = new Random();
                int[] arr;
                arr = new int[10];

                String line;
                while (!"0".equalsIgnoreCase(line = in.readLine())) {
                    System.out.printf("Sent from the client: %s\n", line);
                    //работа
                    int maxnumber = Integer.parseInt(line);
                    IntStream.range(0, 10)
                            .forEach(index -> arr[index] = random.nextInt(1, maxnumber + 1));
                    System.out.println(Arrays.toString(arr) + "\n");
                    String arraySend = Arrays.toString(arr);
                    //
                    out.println(arraySend);//отправка клиенту
                }
                System.out.println("Client disconnected.");
            }


            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();
                        clientSocket.close();
                        System.out.println("Socket closed.");
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

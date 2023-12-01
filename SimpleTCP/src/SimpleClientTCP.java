import java.io.*;
import java.net.*;
import java.util.*;


public class SimpleClientTCP {
    static int PORT = 1234;
    static Scanner inFromUser = null;

    public static void main(String args[]) throws Exception {
        System.out.printf("Client gestartet\n");

        inFromUser = new Scanner(System.in);
        System.out.printf("Bitte IP-Adresse des Servers eingeben: ");
        String server = inFromUser.nextLine();
        InetAddress address = InetAddress.getByName(server);

        Socket socket = new Socket(address, PORT);

        protocol(socket);
        inFromUser.close();
    }

    public static void protocol(Socket socket) throws Exception {

        Scanner inFromServer = new Scanner(socket.getInputStream());
        PrintStream outToServer = new PrintStream(socket.getOutputStream());

        System.out.printf("Was wollen Sie dem Server senden? ");

        String request = inFromUser.nextLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append(request);
        }
        long startTime = System.currentTimeMillis();
        outToServer.println(sb);

        long endTime = startTime;
        while (inFromServer.hasNextLine()) {
            endTime = System.currentTimeMillis();
            String reply = inFromServer.nextLine();
            System.out.println("RECEIVED FROM SERVER: " + reply);
        }
        System.out.println("Transmission time: " + (endTime - startTime));
        socket.close();
    }
}

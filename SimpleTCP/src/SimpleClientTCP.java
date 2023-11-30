import java.io.*;
import java.net.*;
import java.util.*;


public class SimpleClientTCP {
    static int PORT = 1234;
    static Scanner inFromUser = null;
    static long startTime;
    static long endTime;

    public static void main(String args[]) throws Exception {
        System.out.printf("Client gestartet\n");

        inFromUser = new Scanner(System.in);
        System.out.printf("Bitte IP-Adresse des Servers eingeben: ");
        String server = inFromUser.nextLine();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        System.out.printf("Was wollen Sie dem Server senden? ");
        String request = inFromUser.nextLine();
        for (int i = 0; i < 10000; i++) {
            Socket socket = new Socket(address, PORT);
            if (i == 0) {
                startTime = System.currentTimeMillis();
            }
            protocol(socket, i + ". " + request);
        }
        inFromUser.close();
        System.out.println("Dauer der Übertragung: " + (endTime - startTime));
    }

    public static void protocol(Socket socket, String request) throws Exception {
        Scanner inFromServer = new Scanner(socket.getInputStream());
        PrintStream outToServer = new PrintStream(socket.getOutputStream());

        outToServer.println(request);

        while (inFromServer.hasNextLine()) {
            String reply = inFromServer.nextLine();
            System.out.println("RECEIVED FROM SERVER: " + reply);
        }
        endTime = System.currentTimeMillis();
        socket.close();
    }
}

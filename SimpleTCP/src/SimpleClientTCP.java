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
                outToServer.println(request);

                while (inFromServer.hasNextLine()) {
                    String reply = inFromServer.nextLine();
                    System.out.println("RECEIVED FROM SERVER: " + reply);
                }

                socket.close();
	}
}

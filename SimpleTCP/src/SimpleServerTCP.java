import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleServerTCP {
        static int PORT = 1234;

	public static void main(String args[]) throws Exception {
		System.out.printf("Server gestartet\n");

                ServerSocket servsocket = new ServerSocket(PORT);

                while (true) {
                        Socket socket = servsocket.accept();
                        protocol(socket);
                }
	}

	public static void protocol(Socket socket) throws Exception {

                Scanner inFromClient = new Scanner(socket.getInputStream());
                PrintStream outToClient = new PrintStream(socket.getOutputStream());

                String request = inFromClient.nextLine();
                System.out.println("RECEIVED FROM CLIENT: " + request);

                outToClient.println("I am the server. This is what I heard from you:");
                outToClient.println(request);

                socket.close();
	}
}

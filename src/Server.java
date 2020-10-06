import java.io.*;
import java.net.*;

public class Server {
    String serverName = "[SERVER NAME]";
    int serverPort = -1;
    ServerSocket server = null;
    Socket client = null;
    String stringFromUser = null;
    String stringReworked = null;
    BufferedReader inFromClient;
    DataOutputStream outToClient;

    //costruttori
    public Server(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    //metodi
    public Socket attendi() {
        try {
            System.out.println("1 SERVER partito in esecuzione ...");

            server = new ServerSocket(serverPort);

            client = server.accept();

            server.close();

            inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outToClient = new DataOutputStream(client.getOutputStream());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.exit(1);
        }

        return client;
    }
    public void comunica() {
        try {
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo.\n" +
                    "Attendo ...");
            stringFromUser = inFromClient.readLine();
            System.out.println("6 ricevuta la stringa dal client: " + stringFromUser);

            stringReworked = stringFromUser.toUpperCase();
            System.out.println("7 invio la stringa modificata al client ...");
            outToClient.writeBytes(stringReworked + "\n");

            System.out.println("9 SERVER: fine elaborazione ... buona notte!");
            client.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la risposta del server!");
            System.exit(1);
        }
    }
    //main
    public static void main(String[] args) {
        Server server = new Server("127.0.0.1", 6789);
        while (true) {
            server.attendi();
            server.comunica();
        }
    }
}
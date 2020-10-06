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
    public void power_up() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);

            for(;;) {
                System.out.println("Server in attesa ...");
                Socket socket = serverSocket.accept();
                System.out.println("Server socket " + socket);
                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza de server!");
            System.exit(1);
        }
    }
    //main
    public static void main(String[] args) {
        Server server = new Server("127.0.0.1", 6789);
        server.power_up();
    }
}
import java.io.*;
import java.net.*;

public class Client {
    private String serverName = "[SERVER NAME]";
    private int serverPort = -1;
    private Socket mySocket;
    private BufferedReader keyboard;
    private String userString;
    private String stringFormServer;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    //costruttore
    public Client(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    //metodi
    public Socket connetti() {
        System.out.println("2 CLIENT partito in esecuzione ...");
        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            mySocket = new Socket(serverName, serverPort);
            outToServer = new DataOutputStream(mySocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        }
        catch (UnknownHostException e) {
            System.out.println("Host sconosciuto");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }

        return mySocket;
    }
    public void comunica() {
        try {
            System.out.println("4 ... inserisci la stringa da trasmettere al server:");
            userString = keyboard.readLine();

            System.out.println("5 ... invio la stringa al serve e aspetto");
            outToServer.writeBytes(userString + "\n");

            stringFormServer = inFromServer.readLine();
            System.out.println("8 ... risposta dal server:\n" + stringFormServer);

            System.out.println("9 CLIENT: termina elaborazione e chiudi la connessione");
            mySocket.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!");
            System.exit(1);
        }
    }

    //main
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 6789);
        client.connetti();
        client.comunica();
    }

}
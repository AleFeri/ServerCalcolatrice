import java.io.*;
import java.net.*;

class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringFromUser = null;
    String stringReworked = null;
    BufferedReader inFromClient;
    DataOutputStream outToClient;

    //Costruttore
    public ServerThread(Socket socket) {
        this.client = socket;
    }

    //metodi
    public void run() {
        try {
            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void comunica() throws Exception {
        inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outToClient = new DataOutputStream(client.getOutputStream());

        for (; ; ) {
            stringFromUser = inFromClient.readLine();

            if (stringFromUser == null || stringFromUser.equals("FINE")) {
                outToClient.writeBytes(stringFromUser + " (=>server in chiusura...)\n");
                System.out.println("Echo sul server in chiusura: " + stringFromUser);
                break;
            } else {
                outToClient.writeBytes(stringFromUser + " (ricevuta e ritrasmessa)\n");
                System.out.println("6 Echo sul server: " + stringFromUser);
            }
        }
        outToClient.close();
        inFromClient.close();
        System.out.println("9 Chiusur socket: " + client);
        client.close();
    }
}
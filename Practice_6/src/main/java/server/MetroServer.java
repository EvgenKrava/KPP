package server;

import client.ClientHandler;
import other.MetroCardBank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
    MetroCardBank bnk;
    private ServerSocket serverSocket = null;
    private int serverPort;

    public MetroServer() {
        this(8080);
    }

    public MetroServer(int port) {
        this.bnk = new MetroCardBank();
        this.serverPort = port;
    }

    public MetroCardBank getBnk() {
        return bnk;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(serverPort);
            System.out.println("Server is start");
            while (true) {
                System.out.println("Waiting new client");
                Socket socket = serverSocket.accept();
                System.out.println("New client: " + socket + " "+socket.isConnected());
                ClientHandler clientHandler = new ClientHandler(this.getBnk(), socket);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                serverSocket.close();
                System.out.println("Server stopped");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MetroServer srv = new MetroServer(7891);
        srv.start();
    }

}

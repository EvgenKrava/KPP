package server;

import card.MetroCardBank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private MetroCardBank metroCardBank;
    private ServerSocket serverSocket;
    private int port = 8080;
    private boolean work = true;

    public Server(){
        this.metroCardBank = new MetroCardBank();
    }

    public Server(int port){
        this();
        this.port = port;
    }

    @Override
    public void run() {
        work = true;
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server has started");
            while (work){
                System.out.println("Waiting new client...");
                Socket socket = serverSocket.accept();
                System.out.println("New client:\t"+socket);
                RequestHandler requestHandler = new RequestHandler(metroCardBank, socket);
                requestHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server socket error");
        }finally {
            try {
                serverSocket.close();
                System.out.println("Server stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(7891);
        server.start();
    }

    public void finish() throws IOException {
        work = false;
    }
}

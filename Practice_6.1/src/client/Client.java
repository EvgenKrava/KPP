package client;

import card.MetroCard;
import card.User;
import card.operations.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private int port = 8080;

    private String server = "localhost";

    private Socket socket = null;

    private ObjectOutputStream objectOutputStream = null;

    private ObjectInputStream objectInputStream = null;

    public Client(String server, int port) {
        try {
            this.server = server;
            this.port = port;
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void request(MetroCardOperation operation) {
        try {
            objectOutputStream.writeObject(operation);
            objectOutputStream.flush();
            System.out.println(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        try {
            objectOutputStream.writeObject(new StopOperation());
            objectOutputStream.flush();
            System.out.println(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    public String getServer() {
        return server;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 7891);
        AddMetroCardOperation addMetroCardOperation = new AddMetroCardOperation(new MetroCard("1488288", new User("Evgen", "Kravchenko", "M", "19.06.2000"), "HNU Karazina"));
        client.request(addMetroCardOperation);
        client.request(new AddMoneyOperation("1488288", 1000));
        client.request(new ShowBalanceMetroCardOperation("1488288"));
        client.request(new GetMoneyOperation("1488288", 200));
        client.request(new ShowBalanceMetroCardOperation("1488288"));
        client.request(new StopOperation());
        client.finish();
    }
}

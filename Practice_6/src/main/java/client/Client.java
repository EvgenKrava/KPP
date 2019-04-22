package client;

import card.operations.*;
import other.MetroCard;
import other.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private int port;
    private String server;
    private Socket socket = null;
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;

    public Client(String server){
        this(server, 8080);
    }

    public Client(String server, int port) {
        this.port = port;
        this.server = server;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(server, port), 1000);
            os = new ObjectOutputStream(socket.getOutputStream());
            is = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void finish() {
        try {
            os.writeObject(new StopOperation());
            os.flush();
            System.out.println(is.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void applyOperation(CardOperation op) {
        try {
            os.writeObject(op);
            os.flush();
            System.out.println(is.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public int getPort() {
        return port;
    }

    public String getServer() {
        return server;
    }

    public static void main(String[] args) {

        Client client = new Client("localhost", 7891);

        AddMetroCardOperation op = new AddMetroCardOperation();
        MetroCard metroCard = new MetroCard();
        metroCard.setUser(new User("Evgen", "Kravchenko", "M", "19.06.2000"));
        metroCard.setSerialNumber("1488228");
        metroCard.setCollege("KhNU Karazina");
        metroCard.setBalance(1500);
        client.applyOperation(op);

        client.applyOperation(new ShowBalanceOperation("1488288"));

        AddMoneyOperation addMoneyOperation = new AddMoneyOperation("1488288", 15);
        addMoneyOperation.setMoney(100);
        client.applyOperation(addMoneyOperation);


        ShowBalanceOperation showBalanceOperation = new ShowBalanceOperation("1488288");

        client.applyOperation(showBalanceOperation);

        client.finish();
    }
}

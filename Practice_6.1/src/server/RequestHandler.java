package server;

import card.MetroCard;
import card.MetroCardBank;
import card.operations.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler extends Thread {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private boolean work;
    private MetroCardBank metroCardBank;
    private Socket socket;

    public RequestHandler(MetroCardBank metroCardBank, Socket socket) {
        this.metroCardBank = metroCardBank;
        this.socket = socket;
        this.work = true;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("RequestHandlerError");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        synchronized (metroCardBank) {
            System.out.println("RequestHandler has started");
            while (work) {
                Object object;
                try {
                    object = objectInputStream.readObject();
                    this.defineOperation(object);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void defineOperation(Object object) throws IOException {
        if (object instanceof StopOperation) {
            this.finish();
        } else if (object instanceof AddMetroCardOperation) {
            this.addMetroCard(object);
        } else if (object instanceof AddMoneyOperation) {
            this.addMoney(object);
        } else if (object instanceof GetMoneyOperation) {
            this.getMoney(object);
        } else if (object instanceof RemoveMetroCardOperation) {
            this.removeMetroCard(object);
        } else if (object instanceof ShowBalanceMetroCardOperation) {
            this.showBalance(object);
        } else {
            this.error();
        }
    }

    private void error() throws IOException {
        objectOutputStream.writeObject("RequestHandler.error()");
        objectOutputStream.flush();
    }

    private void showBalance(Object object) throws IOException {
        ShowBalanceMetroCardOperation showBalanceMetroCardOperation = (ShowBalanceMetroCardOperation) object;
        MetroCard metroCard = metroCardBank.getMetroCardForSerialNumber((showBalanceMetroCardOperation.getSerialNumber()));
        if (metroCard == null) {
            objectOutputStream.writeObject("Metro Card balance is not access");
            objectOutputStream.flush();
        } else {
            objectOutputStream.writeObject("Metro Card:\t" + showBalanceMetroCardOperation.getSerialNumber() + "\tbalance:\t" + metroCard.getBalance());
            objectOutputStream.flush();
        }
    }

    private void removeMetroCard(Object object) throws IOException {
        RemoveMetroCardOperation removeMetroCardOperation = (RemoveMetroCardOperation) object;
        if (metroCardBank.removeCard(removeMetroCardOperation.getSerialNumber())) {
            objectOutputStream.writeObject("Metro Card removed");
            objectOutputStream.flush();
        } else {
            objectOutputStream.writeObject("Metro Card not removed");
            objectOutputStream.flush();
        }
    }

    private void getMoney(Object object) throws IOException {
        GetMoneyOperation getMoneyOperation = (GetMoneyOperation) object;
        if (metroCardBank.getMoney(getMoneyOperation.getSerialNumber(), getMoneyOperation.getMoney())) {
            objectOutputStream.writeObject("Money getting");
            objectOutputStream.flush();
        } else {
            objectOutputStream.writeObject("Money not getting");
            objectOutputStream.flush();
        }
    }

    private void addMoney(Object object) throws IOException {
        AddMoneyOperation addMoneyOperation = (AddMoneyOperation) object;

        if (metroCardBank.addMoney(addMoneyOperation.getSerialNumber(), addMoneyOperation.getMoney())) {
            objectOutputStream.writeObject("Money added");
            objectOutputStream.flush();
        } else {
            objectOutputStream.writeObject("Money not added");
            objectOutputStream.flush();
        }

    }

    private void addMetroCard(Object object) throws IOException {
        AddMetroCardOperation addMetroCardOperation = (AddMetroCardOperation) object;
        MetroCard metroCard = addMetroCardOperation.getMetroCard();
        metroCardBank.addCard(metroCard);
        objectOutputStream.writeObject("Metro Card added");
        System.out.println("Metro Card added");
        objectOutputStream.flush();
    }

    private void finish() throws IOException {
        work = false;
        objectOutputStream.writeObject("Finish work:\t" + socket);
        objectOutputStream.flush();
    }
}

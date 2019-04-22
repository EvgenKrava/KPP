package client;

import card.operations.*;
import other.MetroCardBank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private ObjectInputStream is = null;

    private ObjectOutputStream os = null;

    private boolean work;

    private MetroCardBank bank;

    private Socket socket;

    public ClientHandler(MetroCardBank bnk, Socket socket) {
        this.bank = bnk;
        this.socket = socket;
        this.work = true;
        try {
            this.is = new ObjectInputStream(socket.getInputStream());
            this.os = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void run() {
        synchronized (bank) {
            System.out.println("Client Handler Started for: " + socket);
            while (work) {
                Object obj;
                try {
                    obj = is.readObject();
                    processOperation(obj);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error: " + e);
                }
            }
            try {
                System.out.println("Client Handler Stopped for: " + socket);
                socket.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    private void processOperation(Object obj) throws
            IOException, ClassNotFoundException {
        if (obj instanceof StopOperation) {
            finish();
        } else if (obj instanceof AddMetroCardOperation) {
            addCard(obj);
        } else if (obj instanceof AddMoneyOperation) {
            addMoney(obj);
        } else if (obj instanceof PayMoneyOperation) {
            payMoney(obj);
        } else if (obj instanceof RemoveCardOperation) {
            removeCard(obj);
        } else if (obj instanceof ShowBalanceOperation) {
            showBalance(obj);
        } else {
            error();
        }
    }

    private void finish() throws IOException {
        work = false;
        os.writeObject("Finish work " + socket);
        os.flush();
    }

    private void addCard(Object obj)
            throws IOException {
        bank.addCard(((AddMetroCardOperation) obj).getCard());
        os.writeObject("Card added");
        os.flush();
    }

    private void addMoney(Object obj)
            throws IOException, ClassNotFoundException {
        AddMoneyOperation op = (AddMoneyOperation) obj;
        boolean res = bank.addMoney(op.getSerialNumber(), op.getMoney());
        if (res) {
            os.writeObject("Balance up");
            os.flush();
        } else {
            os.writeObject("Balance dont up");
            os.flush();
        }
    }

    private void payMoney(Object obj)
            throws IOException, ClassNotFoundException {
        PayMoneyOperation op = (PayMoneyOperation) obj;
        boolean res = bank.getMoney(op.getSerialNumber(), op.getMoney());
        if (res) {
            os.writeObject("Money get");
            os.flush();
        } else {
            os.writeObject("Money not get");
            os.flush();
        }
    }

    private void removeCard(Object obj)
            throws IOException, ClassNotFoundException {
        RemoveCardOperation op = (RemoveCardOperation) obj;
        boolean res = bank.removeCard(op.getSerialNumber());
        if (res) {
            os.writeObject("Card deleted: " + op.getSerialNumber());
            os.flush();
        } else {
            os.writeObject("Metro card is not access: " + op.getSerialNumber());
            os.flush();
        }
    }

    private void showBalance(Object obj)
            throws IOException, ClassNotFoundException {
        ShowBalanceOperation op = (ShowBalanceOperation) obj;
        int ind = bank.findMetroCard(op.getSerialNumber());
        if (ind >= 0) {
            os.writeObject("Card : " + op.getSerialNumber() + " balance: "
                    + bank.getStore().get(ind).getBalance());
            os.flush();
        } else {
            os.writeObject("Card balance is not access: " + op.getSerialNumber());
        }
    }

    private void error() throws IOException {
        os.writeObject("Method ClientHandler.error() started");
        os.flush();
    }

}

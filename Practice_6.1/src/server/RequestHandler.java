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
                Operation operation;
                try {
                    operation = (Operation) objectInputStream.readObject();
                    if(operation instanceof StopOperation){
                        finish();
                        socket.close();
                        break;
                    }
                    objectOutputStream.writeObject(operation.operate(metroCardBank));

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void finish() throws IOException {
        work = false;
        objectOutputStream.writeObject("Finish work:\t" + socket);
        objectOutputStream.flush();
    }
}

package server;

import interfaces.Conferee;
import interfaces.Registruble;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements Registruble {
    ArrayList<Conferee> conferees;

    Server() throws RemoteException{
        conferees = new ArrayList<>();
    }

    public static void main(String[] args) throws RemoteException {
        String localhost = "127.0.0.1";
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String serviceName = "reg";
        System.setProperty(RMI_HOSTNAME, localhost);
        Registry registry = LocateRegistry.createRegistry(1099);
        Registruble stub = new Server();
        registry.rebind("reg", stub);
    }

    @Override
    public int registration(Conferee conferee) {
        conferees.add(conferee);
        return conferees.size();
    }

    @Override
    public String getInfo() {
        return conferees.toString();
    }
}

package client;

import interfaces.Conferee;
import interfaces.Registruble;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String... args) throws RemoteException, NotBoundException, MalformedURLException {
//        String localhost = "127.0.0.1";
//        String RMI_HOSTNAME = "java.rmi.server.hostname";
//        String SERVICE_PATH = "rmi://localhost/reg";
        Conferee conferee = new Conferee(
                "name",
                "surname",
                "org",
                "repo",
                "email");
//        System.setProperty(RMI_HOSTNAME, localhost);
//
//        Registruble registruble =(Registruble) Naming.lookup(SERVICE_PATH);
//        System.out.println(registruble.registration(conferee));
//        System.out.println(registruble.getInfo());
        String SERVICE_PATH = "rmi://localhost/reg";
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        Registruble comp = (Registruble)registry.lookup("reg");
        System.out.println(comp.registration(conferee));
        System.out.println(comp.getInfo());
    }
}

package server;

import compute.Compute;
import compute.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Compute {

    public Server() {
        super();
    }

    @Override
    public <T> T executeTask(Task<T> task) throws RemoteException {
        return task.execute();
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        Compute server = new Server();
        try {
            Compute stub = (Compute) UnicastRemoteObject.exportObject(server, 1234);
            Registry registry = LocateRegistry.getRegistry();
            String name = "Compute";
            registry.rebind(name, stub);
            System.out.println("Server ready to work");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

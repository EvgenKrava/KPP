import client.ClientGui;
import server.ServerGui;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Demo {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, UnknownHostException {
        ClientGui.main(null);
        ServerGui.main(null);
    }
}

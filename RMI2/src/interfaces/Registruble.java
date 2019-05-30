package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Registruble extends Remote {
    public int registration(Conferee conferee) throws RemoteException;
    public String getInfo() throws RemoteException;
}

package ex_2.server;

import ex_2.remote.Conferee;
import ex_2.remote.ConfereeAdded;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RegisterConferees extends UnicastRemoteObject implements ConfereeAdded {

    private List<Conferee> conferees;

    RegisterConferees() throws RemoteException {
        super();
        conferees = new ArrayList<>();
    }

    public  boolean addConferee(String name, String familyName, String placeOfWork, String reportTitle, String email){
        return conferees.add(new Conferee(name, familyName, placeOfWork, reportTitle, email));
    }

    @Override
    public boolean addConferee(Conferee conferee){
        return conferees.add(conferee);
    }

    @Override
    public Conferee getInfo(int index) {
        if (index>=size())
            return null;
        return conferees.get(index);
    }

    public Conferee getConferee(int index){
        return conferees.get(index);
    }

    public void setConferee(Conferee conferee, int index){
        conferees.set(index, conferee);
    }

    public void setConferees(List<Conferee> conferees) {
        this.conferees = conferees;
    }

    public List<Conferee> getConferees() {
        return conferees;
    }

    public int size(){
        return conferees.size();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Conferee c: conferees){
            s.append(c.toString()).append("\n");
        }
        return s.toString();
    }
}

package ex_2.remote;

import java.io.Serializable;
import java.rmi.Remote;

public interface ConfereeAdded extends Remote , Serializable {
    boolean addConferee(Conferee conferee);
    Conferee getInfo(int index);
}

package ex_1.udpWork;

import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {
    private InetAddress inetAddress;
    private int port;

    User(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

    @Override
    public String toString() {
        return "Internet Address:\t"+inetAddress+"\tport:\t"+port;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

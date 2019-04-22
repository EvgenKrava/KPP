package ex_3.bulletinBoardService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSenderReceiver {

    private String name;
    private InetAddress addr;
    private int port = 3456;
    private MulticastSocket multicastSocket;

    MulticastSenderReceiver(String name) {
        this.name = name;
        try {
            addr = InetAddress.getByName("224.0.0.1");
            multicastSocket = new MulticastSocket(port);
            new Receiver().start();
            new Sender().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Sender extends Thread {
        public void run() {
            try {
                BufferedReader fromUser = new BufferedReader(new
                        InputStreamReader(System.in));
                while (true) {
                    String msg = name + ":" + fromUser.readLine();
                    byte[] out = msg.getBytes();
                    DatagramPacket pkt = new DatagramPacket(out, out.length, addr,
                            port);
                    multicastSocket.send(pkt);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class Receiver extends Thread {
        public void run() {
            try {
                byte[] in = new byte[256];
                DatagramPacket pkt = new DatagramPacket(in, in.length);
                multicastSocket.joinGroup(addr);
                while (true) {
                    multicastSocket.receive(pkt);
                    System.out.println(new String(pkt.getData(), 0, pkt.getLength()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

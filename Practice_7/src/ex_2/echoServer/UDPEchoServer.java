package ex_2.echoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer extends UDPServer{

    public final static int DEFAULT_PORT = 8080;

    public UDPEchoServer() {
        super(DEFAULT_PORT);
    }

    @Override
    protected void respond(DatagramSocket datagramSocket, DatagramPacket datagramPacket) throws IOException {
        DatagramPacket packet = new DatagramPacket(datagramPacket.getData(), datagramPacket.getLength(), datagramPacket.getAddress(), datagramPacket.getPort());
        datagramSocket.send(packet);
    }

    public static void main(String[] args) {
        UDPServer server = new UDPEchoServer();
        Thread t = new Thread(server);
        t.start();
        System.out.println("Start echo-server...");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server.shutDown();
        System.out.println("Finish echo-server...");
    }
}

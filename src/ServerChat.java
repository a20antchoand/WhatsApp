import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServerChat {


    public static void main(String args[]) throws Exception {

        MulticastSocket server = new MulticastSocket(8885);
        InetAddress group = InetAddress.getByName("224.2.2.5");
        //getByName - returns IP address of the given host
        server.joinGroup(group);

        System.out.print("Escoltant");

        boolean infinite = true;
        /* Server continually receives data and prints them */

        while(infinite) {

            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf, buf.length);
            server.receive(data);
            String msg = new String(data.getData()).trim();
            DatagramPacket dataReturn = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, 8885);

            server.send(dataReturn);
        }
    }

}

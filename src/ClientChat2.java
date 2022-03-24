import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientChat2 {

    public static void main(String args[]) throws Exception {

        MulticastSocket chat = new MulticastSocket(8885);
        InetAddress group = InetAddress.getByName("224.2.2.5");
        chat.joinGroup(group);

        String msg = "";
        System.out.println("Type a message for the server:");
        BufferedReader br = new BufferedReader(new
                InputStreamReader(System.in));
        msg = br.readLine();
        DatagramPacket data = new DatagramPacket(msg.getBytes(), 0, msg.length(), group, 8885);
        chat.send(data);

        DatagramPacket datagramPacket;
        do {
            byte buf[] = new byte[1024];
            datagramPacket = new DatagramPacket(buf, buf.length);
            chat.receive(datagramPacket);
            System.out.println(datagramPacket.getLength());
            String msg2 = new String(data.getData()).trim();
            System.out.print(msg2);
        } while (datagramPacket.getLength() == -1);
    }

}

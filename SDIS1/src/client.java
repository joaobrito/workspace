import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		DatagramSocket socket = null;
		
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sendMessage(socket, "teste");
		
	}
	
	public static void sendMessage(DatagramSocket socket, String message){
		DatagramPacket p = null;
		try {
			p = new DatagramPacket(message.getBytes(), message.getBytes().length, InetAddress.getByName("ubuntu"),5555);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			socket.send(p);
		} catch (IOException e) {
			System.out.println("ERROR - Sending message");
			e.printStackTrace();
		}
		
	}

}



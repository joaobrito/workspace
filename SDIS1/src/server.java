import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;


public class server {
	//hashmap<Plate, owner>
	public static HashMap<String, String> plates;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		plates = new HashMap<String, String>();
		int port = 5555;
		if(args.length != 0){
			port = Integer.parseInt(args[0]);
			System.out.println("Port is set to " + port);
		}
		else
			System.out.println("Port is set to default - 5555");			
		
		DatagramSocket s = null;
		
		try {
			s = new DatagramSocket(port);
		} catch (SocketException e) {

			System.out.println("ERROR - creating socket");
		}
		
		byte[] buf = new byte[256];
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		
		try {
			s.receive(packet);
		} catch (IOException e) {
			System.out.println("ERROR - receiving packet");
			e.printStackTrace();
		}
		messageReceived(packet);
	}
	
	public static void messageReceived(DatagramPacket data){
		String message = new String(data.getData());
		System.out.println("message = " + message);
	}
	
	private static String parseMessage(String message){
		String[] messageSplit = message.split(" ");
		if (messageSplit.length > 2 || messageSplit.length <= 0)
			return "ERROR";
		
		if(messageSplit[0].compareTo("register") == 0 ){
			return "" + registerPlate(messageSplit[1], messageSplit[2]);
		}
		else
			if(messageSplit[0].compareTo("lookup") == 0 )
				return lookupPlate(messageSplit[1]);
	}

	private static String lookupPlate(String plate) {
		if(plates.containsKey(plate))
		return plates.get(plate);
	}

	private static int registerPlate(String plate, String owner) {
		if(plates.containsValue(plate))
			return -1;
		else
			plates.put(plate, owner);
		return plates.size();
	}
}

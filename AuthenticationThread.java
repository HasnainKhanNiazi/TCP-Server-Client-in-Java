import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AuthenticationThread extends Thread{
	private Socket cs;//creating a socket 
	private byte Tag_Bit;// Creating a variabe Tag_Bit to send ACK or NACK
	private static int last_Packet = 0;
	AuthenticationThread(Socket s) // Making constructor
	{
		cs = s; //Assigning passed socket in contructor to Socket s
	}

	public void run()
	{
		try {
			ObjectInputStream obs = new ObjectInputStream(cs.getInputStream());// Creating a ObjectInputStream objct to read an object that is sent by client
			Packet os = (Packet) obs.readObject();// Reading packet 
			if(os.sms != null && last_Packet == os.sequence_number){// checking that is there is any sms or not and also checking that this is the expected packet or not 
				last_Packet++;
				System.out.println(os.sequence_number+" "+os.sms);// Prints the data which is sent by client
				Tag_Bit = (byte) 1;// assigning value 1 to Tag_Bit for sending ACK
				ObjectOutputStream obus = new ObjectOutputStream(cs.getOutputStream());//Creating ObjectOutputStream object to write a packet and send it to client
				Server_Packet packet = new Server_Packet(os.sequence_number,Tag_Bit);//creating a packet
				obus.writeObject(packet);//Send an object to client
				
				cs.close();//closing the socket
			}
			else{
				System.out.println("Recieved an unexpected Packet");
				Tag_Bit = (byte) 0;// assigning value 0 to Tag_Bit for sending NACK
				ObjectOutputStream obus = new ObjectOutputStream(cs.getOutputStream());//Creating ObjectOutputStream object to write a packet and send it to client
				Server_Packet packet = new Server_Packet(os.sequence_number,Tag_Bit);//creating a packet
				obus.writeObject(packet);//Send an object to client
				cs.close();//closing the socket
			}
		}
		catch (Exception e)//catching exception
		{
			System.out.println("Exception Found");//printing exception
		}
	}
}
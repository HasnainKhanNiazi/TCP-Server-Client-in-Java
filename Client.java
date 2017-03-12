import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	private Scanner sc = new Scanner(System.in);//making a scanner to get input from user
	private int Sequence_Number = 0;//Making an integer for sequence_Number

	private void funtion(){
		String n = sc.nextLine();//Getting a string from user
		String[] parts = n.split(" ");//Splitting the string into sub parts by using built in method split
				for(int i=0;i<parts.length;i++)//Loop which will run from 0 to length of parts
				{
					Identify(parts[Sequence_Number]);//Calling a method Identify and passing a string to it's arguments
				}
	}

	private void Identify(String s){
		try
		{
			Socket client = new Socket("localhost",2222);//Making a client socket to connect with server
			ObjectOutputStream obs = new ObjectOutputStream(client.getOutputStream());//Creating ObjectOutputStream object to write a packet and send it to client
			Packet Packetc = new Packet(Sequence_Number,s);//creating a packet to send it to server
			obs.writeObject(Packetc);//Sending an object to server

			//server response
			ObjectInputStream obis = new ObjectInputStream(client.getInputStream());// Creating a ObjectInputStream objct to read an object that is sent by server
			Server_Packet PacketS =(Server_Packet) obis.readObject();//reading object

			if(PacketS.sequence_number == Sequence_Number && PacketS.Tag_Bit == 1){//checking that is it ACK or not 
				Timer t = new Timer();//Creating an object of timer class
				t.run();//start the timer
				Sequence_Number++;//if Server send ac ACK then sequence_number should be moved forward
				System.out.println(PacketS.sequence_number+" "+PacketS.Tag_Bit+"   ACK");//prints the sequence_Number and ACK to let user know that packet has been sent
				client.close();//closing the socket
			}
			else{
				System.out.println(PacketS.sequence_number+ PacketS.Tag_Bit+" NACK");//printing that it is NACK of this sequence_Number
				client.close();//closing the socket
			}
		}
		catch(Exception ex)//catching exception
		{
			System.out.println(ex);//printing exception 
		}
	}
	public static void main(String args[])
	{
		Client c = new Client();//making an object of client class
		System.out.println("Enter a sms to send");//Printing an sms for user to enter a string
		while(true){//using a while loop because I don't want client to be closed
			c.funtion();// calling a method named as function
		}
	}
}
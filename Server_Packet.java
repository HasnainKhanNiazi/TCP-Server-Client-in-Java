import java.io.Serializable;

public class Server_Packet implements Serializable{
	
	byte Tag_Bit = (byte) 1;//creating a tag bit fo sending ack or not
	int sequence_number;// creating an Integr of sequence number 

	public Server_Packet(int sequence_number,byte Tag_Bit)//constructor
	{
		this.Tag_Bit = Tag_Bit;//assigning value of Tag_Bit 
		this.sequence_number = sequence_number;//assigning value of Sequence_Number 
	}
}

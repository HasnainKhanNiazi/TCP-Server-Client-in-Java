import java.io.Serializable;

public class Packet implements Serializable{
	String sms;//Creating an sms string
	int sequence_number;//creating a sequence number
	
	public Packet(int sequence_number,String sms) {//constructor
		this.sms = sms;//assigning value of string named sms 
		this.sequence_number = sequence_number;//assigning value of Sequence_Number
	}
}
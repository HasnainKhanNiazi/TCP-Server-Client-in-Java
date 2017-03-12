import java.net.*;

public class MTServer{

	public static void main(String args[])
	{
		System.out.println("Waiting for client request");//printing a message to
		try
		{
			ServerSocket ss = new ServerSocket(2222);//Creating a server socket at port number 2222
			while (true)//using a while loop to make it iterative
			{
				Socket client = ss.accept();//listening for cleint socket and accept
				AuthenticationThread a = new AuthenticationThread(client);//Creating an object of thread class and passing client socket to it's constructor
				a.start();//starting a thread
			}
		}
		catch(Exception e)//catching exception
		{
			System.out.println(e);//printing expection
		}
	}
}
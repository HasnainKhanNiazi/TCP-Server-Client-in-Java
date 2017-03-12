//This class just run a thread which causes sleeps for 1 second.

public class Timer implements Runnable{

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
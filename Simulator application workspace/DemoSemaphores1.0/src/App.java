import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
	
	static int HARIAK = 200;
	
	public static void main(String[] args) throws Exception {
	
		
		ExecutorService executor = Executors.newCachedThreadPool();
	
		 for(int i=0; i<HARIAK; i++) {
			 executor.submit(new Runnable() {
				public void run() {
					Connection.getInstance().connectA();
					Connection.getInstance().connectB();
				} 
			 });
		 }
		 
		 executor.shutdown();
		 executor.awaitTermination(1, TimeUnit.DAYS);
	}
}


/*
 * Semaphore sem = new Semaphore(4);
 * Executor: to create threads
 * sem.acquire(); //wait 
 * sem.release(); //signal
 * System.out.println("Available permits: " + sem.availablePermits()); //token 
*/
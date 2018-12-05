import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();

	
	private Semaphore semA, semB; //sem init
	private int connections = 0;
	
	private Connection() {
		initSem();
	}
	
	private void initSem() {
		semA = new Semaphore(1, true);
		semB = new Semaphore(0, true);

	}

	public static Connection getInstance() {
		return instance;
	}

	public void connectA() {
		try {
			semA.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			taskA();
	}
	
	public void taskA() {
		
		synchronized (this) {
			connections++;
			System.out.println("A");
		}
	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(connections<3) 
				{	
					semA.release();
				}
			else {
				connections = 0;
				semB.release();
				
			}
		
	
	}
	public void connectB() {
		try {
			semB.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			taskB();
		
	}
	
	public void taskB() {
	
			System.out.println("B");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			semA.release();
	}	
}
	

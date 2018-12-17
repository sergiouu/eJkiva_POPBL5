
class Fifo {
	final int MAX_BUFFER_SIZE = 3;
	int head ;
	int tail ;
	int numElems ;
	int buffer [];
	
	
	public Fifo() {
		head = 0;
		tail = 0;
		numElems = 0;
		buffer = new int [MAX_BUFFER_SIZE];
	}
	
	public synchronized void put(int element) {

		while(numElems == MAX_BUFFER_SIZE)
			try {
				System.out.println("buffer beteta");

				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		buffer[head] = element;
		head = (head+1) % MAX_BUFFER_SIZE;
		numElems++;
		notifyAll();
	}
	
	
	public synchronized int get() {
		int element;

		while(numElems == 0)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		element = buffer[tail];
		tail = (tail+1) % MAX_BUFFER_SIZE;
		numElems--;
		notifyAll();
		return element;
	}
	
	
}
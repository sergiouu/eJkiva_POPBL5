public class Principal {
	
	
   public static void main(String[] args) {
	   
      Fifo c = new Fifo();
      Producer p1 = new Producer(c, 1);
      Producer p2 = new Producer(c, 2);
      Consumer c1 = new Consumer(c, 1);
      c1.start();
      p1.start();
      p2.start();
      
      try {
		c1.join();
		p1.join();
		p2.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
      
   }
}
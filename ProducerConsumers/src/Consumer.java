class Consumer extends Thread {
   private Fifo fifo;
   int id;
   
   public Consumer(Fifo f, int id) {
      fifo = f;
      this.id=id;
   }
   public void run() {
	  int num;
	  
      for (int i = 0; i < 200; i++) {
          num=fifo.get();
          try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          System.out.println("Consumer #" + this.id + " get: " + num);
  		//System.out.println("Buffer:"+fifo.buffer[0]+fifo.buffer[1]+fifo.buffer[2]);

      }
      
      
   }
}
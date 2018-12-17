class Producer extends Thread {
   private Fifo fifo;
   int id;
   
   public Producer(Fifo f, int id) {
      fifo = f;
      this.id = id;
   }
   public void run() {
	  int num=0;
	  
      for (int i = 0; i < 100; i++) {
         fifo.put(id*1000+num);
         try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //System.out.println("Producer #" + this.id + " put: " + num);
 		//System.out.println("Buffer:"+fifo.buffer[0]+fifo.buffer[1]+fifo.buffer[2]);

         num=num+2;
      }
   }
}
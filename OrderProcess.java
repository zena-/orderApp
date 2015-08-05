public class OrderProcess extends Thread {
	private static int ordersProcessed; // keep a count of the orders processed
	private OrderQueue orderQueue;

	public OrderProcess(String name, OrderQueue q){
		super(name); // call super and pass it the name
		orderQueue = q; // get a reference to the one and only queue
	}

	public void run(){
		Order order;

		while (ordersProcessed < OrderApp.MAX_ORDERS) { // while there are more orders to be process i.r less than MAX_ORDERS or while(true)  // to keep the thread running
			addOrdersProcessed();

			order = orderQueue.pullOrder(); // use this method to remove(process) and order from queue // get next available order

			System.out.println("\t\t\t\t" + this.getName() + " processed " + order);

			// the sleep method here is not necessary, it just gives the other threads a chance to execute. 
			try {
				Thread.sleep((long) (Math.random()*3000)); //delay upto 3 sec just to simulate real world
			} catch (InterruptedException e) { } //ignore interruptions
		}
		System.out.println("\t\t\t\t" + this.getName() + " is shutting down");
		return;
	}

	private synchronized void addOrdersProcessed() {  //synchronized to ensure proper addition // so only 1 thread can add to static field at a time
		ordersProcessed++;
	}
}
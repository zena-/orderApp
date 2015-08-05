public class OrderTaker extends Thread {
	private static int ordersTaken; // keep a count of the orders taken
	private OrderQueue orderQueue;

	public OrderTaker(String name, OrderQueue q){
		super(name); // It passes the name of the thread to the super (Thread) constructor
		orderQueue = q; // get a reference to the one and only queue
	}

	public void run(){
		Order order;

		while (ordersTaken < OrderApp.MAX_ORDERS) { // or while(true)  // to keep the thread running
			addOrdersTaken();
			String order_num = "000" + ordersTaken; // gernerate a order number
			order = new Order(order_num); //create a new order
			orderQueue.pushOrder(order); // add(take) order to the queue

			System.out.println(this.getName() + " created " + order);

			// the sleep method here is not necessary, it just gives the other threads a chance to execute. 
			try {
				Thread.sleep((long) (Math.random()*3000)); //delay upto 3 sec just to simulate real world
			} catch (InterruptedException e) { } //ignore interruptions
		}
	}

	private synchronized void addOrdersTaken() {  //synchronized so only 1 thread can add to static field at a time
		ordersTaken++;
	}
}
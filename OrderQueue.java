import java.util.LinkedList;

public class OrderQueue {
	private LinkedList<Order> orderQueue = new LinkedList<Order>(); //<generics> to identify the type of objects that will be stored in the linked list.  In this case it is objects of class <Order>

	public synchronized void pushOrder(Order order) {  //synchronized method to take order
		orderQueue.addLast(order); //add an order to the end of the queue
		notifyAll(); //notify all waiting threads that an order has been added
	}

	public synchronized Order pullOrder(){ // method to pull(process) an order it creaded
		while (orderQueue.size() == 0) { //if there are no orders in the queue,,, it waits
			try {
				System.out.println(Thread.currentThread().getName() + " is waiting");
				wait(); //wait until notified
			} catch (InterruptedException e) {} //do nothing
		}
		Order ord = orderQueue.removeFirst();  //pulls/remove an order from the queue
		return (ord);
	}
}
// this class that uses a linkedList object to create an order queue
//	When an order is taken, the queue notifies the handler threads – using  notifyAll( )
//	When the queue is empty, the handler threads go into waiting state – using wait( )
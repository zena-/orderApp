public class OrderApp {

	static int MAX_ORDERS = 15; // number of orders to process // if none is supplied 10 is used
	static int TAKER_THREADS = 2; // number of ordertaker threads is 2 if none is given as args[1]
	static int PROCESSOR_THREADS = 2; // a queue to simulate order processing 
	static OrderQueue q;

	public static void main(String[] args) {
		if(args.length > 0) MAX_ORDERS = Integer.parseInt(args[0]);
		if(args.length > 1) TAKER_THREADS = Integer.parseInt(args[1]);
		if(args.length > 2) PROCESSOR_THREADS = Integer.parseInt(args[2]);

		q = new OrderQueue(); 

		System.out.println(Thread.currentThread().getName() + " is creating: ");
		System.out.println("-An order queue with maximum orders " + MAX_ORDERS);
		System.out.println("-Starting " + TAKER_THREADS + " order taker threads.");
		System.out.println("-Starting " + PROCESSOR_THREADS + " order process threads.");
		System.out.println(" OrderTaker threads \t\t OrderProcessor threads \n" + "\t");

		for (int i=1; i<=TAKER_THREADS; i++) { // the orderTaker threads
			String name = "Taker- " + i; // derive a name
			OrderTaker t = new OrderTaker(name, q);  // create a thread and pass it the queue
			t.start();
		}

		for (int i=1; i<=PROCESSOR_THREADS; i++) { // the OrderProcess threads
			String name = "Processor- " + i; // derive a name
			OrderProcess p = new OrderProcess(name, q);  // create a thread and pass it the queue
			p.start();
		}
	}
}
// the class that encapsulates an order
public class Order {
	private String orderNum; //the order number
	private String etc; // other data

	public Order(String orderNum){
		this.orderNum = orderNum;
	}	
	public String toStrint(){
		return ("order # " + orderNum);
	}
}
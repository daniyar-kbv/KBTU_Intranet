import java.util.Vector;

public class Executor extends Employee {
	
	private Vector<Order> orders = new Vector<Order>();

	Executor(String firstName, String lastName, String email, String password, int salary) {
		super(firstName, lastName, email, password, salary);
	}
	
	public void viewOrders() {
		for(Order o : orders)
		{
			System.out.println(o);
		}
	}
	public void viewOrdersOf(Employee e) {
		for(Order o : orders)
		{
			if(e.equals(o.getEmployee()))
			{
				System.out.println(o);
			}
		}
	}
	public void viewAcceptedOrders() {
		for(Order o : orders)
		{
			if(o.getIsAccepted())
			{
				System.out.println(o);
			}
		}
	}
	public void acceptOrder(Order o) {
		o.setAccepted();
	}
	public Vector<Order> getOrders() {
		return orders;
	}

	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

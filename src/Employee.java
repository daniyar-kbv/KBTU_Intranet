
public abstract class Employee extends User {

	private int salary;
	
	Employee(String firstName, String lastName, String email, String password, int salary) {
		super(firstName, lastName, email, password);
		this.salary = salary;
	}
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String toString() {
		return super.toString() + "Salary: " + salary + " KZT\n";
	}
	public void sendOrderTo(Executor executor, String description) {
		executor.getOrders().add(new Order(description, this));
	}
}

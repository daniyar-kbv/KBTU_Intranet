import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
	
	private long id;
	private static long nextId;
	private boolean isAccepted;
	private String description;
	private Date date;
	private Employee employee;
	
	Order(String description, Employee employee)
	{
		id = nextId++;
		isAccepted = false;
		date = new Date();
		this.description = description;
		this.employee = employee;
	}
	public boolean getIsAccepted() {
		return isAccepted;
	}
	public void setAccepted() {
		isAccepted = true;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Employee getEmployee()
	{
		return employee;
	}
	public long getId() {
		return id;
	}
	public String toString() {
		return "Order ID: " + id + "\nSended by: " + employee.getFirstName() + " " + employee.getLastName() + " ID: " + employee.getId() + 
			   "\nDate: " + date + "\nDescription: " + description + 
		       "\nStatus: " + isAccepted + "\n";
	}
	public void setNextId(long nextId) {
		this.nextId = nextId;
	}
}

import java.util.Vector;

public class Admin extends Employee {
	
	private Vector<User> users = new Vector<User>();
	private long courseId;
	private long userId;
	private long orderId;
	
	Admin(String firstName, String lastName, String email, String password, int salary) {
		super(firstName, lastName, email, password, salary);
		setUserId(0);
		setCourseId(0);
		setOrderId(0);
	}
	
	public Vector<User> getUsers()
	{
		return users;
	}
	public void viewUsers() 
	{
		for(User u: users) 
		{
			System.out.println(u);
		}
	}
	public void addUser(User u)
	{
		userId++;
		users.add(u);
	}
	public void removeUser(User u)
	{
		users.remove(u);
	}
	public String viewInfoAbout(User u)
	{
		return u.toString();
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public void incOrderId() {
		orderId++;
	}
	public void incCourseId() {
		courseId++;
	}
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}

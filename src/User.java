
import java.io.Serializable;

public abstract class User implements Serializable, Comparable<User> {
	
	private long id;
	private static long nextId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	User(String firstName, String lastName, String email, String password) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		id = nextId++;
	}
	public long getId()
	{
		return id;
	}
	public long getStaticId() {
		return nextId;
	}
	public String getFirstName() 
	{
		return firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public String getEmail() 
	{
		return email;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public void setNextId(long nextId) {
		this.nextId = nextId;
	}
	public String toString() 
	{
		return "ID: " + id + "\nFirst name: " + firstName + "\nLast name: " + lastName + "\nE-mail: " + email + "\nPassword: " + password +"\n";
	}
	public boolean equals(Object o) 
	{
		return this.getId() == ((User) o).getId();
	}
}

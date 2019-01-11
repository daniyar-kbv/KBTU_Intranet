import java.io.Serializable;

public class CourseFile implements Serializable {
	
	private int numOfCredits;
	private String courseTitle;
	private Department department;
	
	CourseFile(int numOfCredits, String courseTitle, Department department)
	{
		this.numOfCredits = numOfCredits;
		this.courseTitle = courseTitle;
		this.department = department;
	}
	
	public int getNumOfCredits()
	{
		return numOfCredits;
	}
	public void setNumOfCredits(int numOfCredits)
	{
		this.numOfCredits = numOfCredits;
	}
	public String getCourseTitle()
	{
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle)
	{
		this.courseTitle = courseTitle;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	public String toString() {
		return "Course title: " + courseTitle + " number of credits: " + numOfCredits + " department: " + department + "\n";
	}
}

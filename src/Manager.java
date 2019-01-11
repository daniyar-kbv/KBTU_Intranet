import java.util.TreeSet;
import java.util.Vector;

public class Manager extends Employee {
	
	private Vector<Course> courses = new Vector<Course>();
	private Vector<CourseFile> courseFiles = new Vector<CourseFile>();
	private Department department;
	
	Manager(String firstName, String lastName, String email, String password, int salary, Department department) {
		super(firstName, lastName, email, password, salary);
		this.department = department;
	}
	public void viewCourses() {
		for(Course c: courses) {
			System.out.println(c);
		}
	}
	public void viewCourseFiles() {
		for(CourseFile cf: courseFiles) {
			System.out.println(cf);
		}
	}
	public void openCourse(CourseFile courseFile) {
		courses.add(new Course(courseFile));
	}
	public void createCourseFile(int numOfCredits, String courseTitle) {
		courseFiles.add(new CourseFile(numOfCredits, courseTitle, department));
	}
	public void assignCourseTo(Teacher teacher, Course course) {
		teacher.getTeacherCourses().put(course, new TreeSet<Student>());
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Vector<Course> getCourses() {
		return courses;
	}
	public Vector<CourseFile> getCourseFiles() {
		return courseFiles;
	}
	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

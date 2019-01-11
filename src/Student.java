import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

public class Student extends User{
	
	private HashMap<Course, Mark> currentCourses = new HashMap<Course, Mark>();
	private HashMap<Course, Mark> passedCourses = new HashMap<Course, Mark>();
	private HashMap<Course, Integer> failedCourses = new HashMap<Course, Integer>(); // Integer here shows how many times student failed a specific course
	private HashMap<Course, Integer> attendance = new HashMap<Course, Integer>();
	private int yearOfStudy;
	private int numOfCredits; // must be less than 22
	private Department department;
	
	Student(String firstName, String lastName, String email, String password, Department department) {
		super(firstName, lastName, email, password);
		this.department = department;
		yearOfStudy = 0;
		numOfCredits = 0;
	}
	public double getGpa() {
		return calculateGpa();
	}
	public double calculateGpa() {
		int totalCredits = 0;
		double totalNum = 0.0;
		
		for(Entry<Course, Mark> entry : passedCourses.entrySet()) {
			totalCredits += entry.getKey().getCourseFile().getNumOfCredits();
			totalNum += entry.getKey().getCourseFile().getNumOfCredits() * entry.getValue().getNumber();
		}
		return totalNum / totalCredits;
	}
	public int getYearOfStudy() {
		return yearOfStudy;
	}
	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}
	public Department getDepartment() {
		return department;
	}
	public void viewCoursesOf(Manager m) {
		m.viewCourses();
	}
	public void viewCourseInfo(Course course) {
		System.out.println(course);
	}
	public void viewInfoAbout(Teacher teacher) {
		System.out.println(teacher);
	}
	public void viewMarks() {
		for(Entry<Course, Mark> entry : currentCourses.entrySet()) {
			System.out.println(entry.getKey() + ":	" + entry.getValue());
		}
	}
	public void viewTranscript() {
		for(Entry<Course, Mark> entry : passedCourses.entrySet()) {
			System.out.println(entry.getKey() + ":	" + entry.getValue());
		}
	}
	public void registerTo(Teacher teacher, Course course) {
		for(Entry<Course, TreeSet<Student>> entry : teacher.getTeacherCourses().entrySet()) {
			if(entry.getKey().equals(course)) {
				entry.getValue().add(this);
				currentCourses.put(course, new Mark());
				attendance.put(course, 0);
				numOfCredits += entry.getKey().getCourseFile().getNumOfCredits();
			}
		}
	}
	public int getNumOfCredits() {
		return numOfCredits;
	}
	public void setNumOfCredits(int numOfCredits) {
		this.numOfCredits = numOfCredits;
	}
	public HashMap<Course, Mark> getCurrentCourses() {
		return currentCourses;
	}
	public void setCurrentCourses(HashMap<Course, Mark> currentCourses) {
		this.currentCourses = currentCourses;
	}
	public HashMap<Course, Mark> getPassedCourses() {
		return passedCourses;
	}
	public void setPassedCourses(HashMap<Course, Mark> passedCourses) {
		this.passedCourses = passedCourses;
	}
	public HashMap<Course, Integer> getFailedCourses() {
		return failedCourses;
	}
	public void setFailedCourses(HashMap<Course, Integer> failedCourses) {
		this.failedCourses = failedCourses;
	}
	public HashMap<Course, Integer> getAttendance() {
		return attendance;
	}
	public void setAttendance(HashMap<Course, Integer> attendance) {
		this.attendance = attendance;
	}
	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}

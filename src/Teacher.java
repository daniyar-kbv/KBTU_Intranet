import java.util.HashMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Teacher extends Employee {
	
	private HashMap<Course, TreeSet<Student>> teacherCourses = new HashMap<Course, TreeSet<Student>>();
	private Department department;
	
	Teacher(String firstName, String lastName, String email, String password, int salary, Department department) {
		super(firstName, lastName, email, password, salary);
		this.setDepartment(department);
	}
	public void viewCourses() {
		for(Entry<Course, TreeSet<Student>> entry : teacherCourses.entrySet()) {
			System.out.println(entry.getKey());
		}
	}
	public void viewStudents() {
		for(Entry<Course, TreeSet<Student>> entry : teacherCourses.entrySet()) {
			System.out.println(entry.getKey() + "\n");
			for(Student student : entry.getValue()) {
				System.out.println(student);
			}
		}
	}
	public void viewInfoAbout(Student student) {
		System.out.println(student);
	}
	public void putMark(Course course, Student student, double mark) {
		for(Entry<Course, TreeSet<Student>> entry : teacherCourses.entrySet()) {
			if(course.equals(entry.getKey())) {
				for(Student s : entry.getValue()) {
					if(s.equals(student)) {
						s.getCurrentCourses().get(course).setFirstAtt(mark);
					}
				}
			}
		}
	}
	public void putAbsent(Course course, Student student) {
		for(Entry<Course, TreeSet<Student>> entry : teacherCourses.entrySet()) {
			if(course.equals(entry.getKey())) {
				for(Student s : entry.getValue()) {
					if(s.equals(student)) {
						s.getAttendance().put(course, s.getAttendance().get(course) + 1);
					}
				}
			}
		}
	}
	public void putPassed(Student student, Course course, Mark mark) {
		for(Entry<Course, TreeSet<Student>> entry : teacherCourses.entrySet()) {
			if(course.equals(entry.getKey())) {
				for(Student s : entry.getValue()) {
					if(s.equals(student)) {
						s.getPassedCourses().put(course, mark);
					}
				}
			}
		}
	}
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public HashMap<Course, TreeSet<Student>> getTeacherCourses() {
		return teacherCourses;
	}

	public void setTeacherCourses(HashMap<Course, TreeSet<Student>> teacherCourses) {
		this.teacherCourses = teacherCourses;
	}
	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

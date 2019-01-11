import java.io.Serializable;

public class Course implements Serializable {
	
	private CourseFile courseFile;
	private long id;
	private static long nextId;
	
	Course(CourseFile courseFile) {
		this.courseFile = courseFile;
		id = nextId++;
	}
	
	public CourseFile getCourseFile() {
		return courseFile;
	}
	public long getCourseId() {
		return id;
	}
	public String toString() {
		return "ID: " + id + " " + courseFile.toString();
	}
	public boolean equals(Object obj) {
		return this.getCourseId() == ((Course)obj).getCourseId();
	}
	public void setNextId(long nextId) {
		this.nextId = nextId;
	}
}

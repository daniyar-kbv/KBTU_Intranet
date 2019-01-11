
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Driver {
	
	static Admin admin;
	static User user;
	static String email = "";
	static String password = "";
	static int option = 0;
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) { // COMPLETED
		deserialize();
		showUserMenu();	
	}
	public static void authorize() { // COMPLETED
		System.out.println("Enter your e-mail: ");
		email = getStringInput();
		System.out.println("Enter your password: ");
		password = getStringInput();
		
		if(findUser()) {	
			determineUserType();
		}
		else {
			System.out.println("Incorrect e-mail or password. Try again.\n");
			authorize();
		}
	}
	public static boolean findUser() { // COMPLETED
		try {
			for(User u: admin.getUsers()) {
				if(email.compareTo(u.getEmail()) == 0) {
					if(password.compareTo(u.getPassword()) == 0) {
						user = u;
						return true;
					}
				}
			}
			if(email.compareTo(admin.getEmail()) == 0 || (password.compareTo(admin.getPassword()) == 0)) {
				user = admin;
				return true;
			}
		}
		catch(NullPointerException e) {
			System.out.println("Database consists of no users.\n"
					         + "Fill it with data.\n"
					         + "Program will shut down automatically.");
			System.exit(0);
		}
		return false;
	}
	public static User findUserById(int id) { // COMPLETED
		for(User u : admin.getUsers()) {
			if(id == u.getId()) {
				return u;
			}
		}
		return null;
	}
	public static Course findCourseById(int id) { // COMPLETED
		for(User u: admin.getUsers()) {
			if(u instanceof Manager) {
				for(Course c: ((Manager)u).getCourses()) {
					if(c.getCourseId() == id) {
						return c;
					}
				}
			}
		}
		return null;
	}
	public static CourseFile findCourseFile(String title) { // COMPLETED
		for(CourseFile cf: ((Manager)user).getCourseFiles()) {
			if(cf.getCourseTitle().compareTo(title) == 0) {
				return cf;
			}
		}
		return null;
	}
	public static Manager findManager(int id) { // COMPLETED
		for(User u: admin.getUsers()) {
			if(u instanceof Manager) {
				if(((Manager)u).getDepartment() == ((Student)user).getDepartment()) {
					return (Manager)u;
				}
			}
		}
		return null;
	}
	public static void determineUserType() { // COMPLETED
		if(user instanceof Student) { showStudentMenu(); }
		else if(user instanceof Teacher) { showTeacherMenu(); }
		else if(user instanceof Manager) { showManagerMenu(); }
		else if(user instanceof Executor) { showExecutorMenu(); }
		else if(user instanceof Admin) { showAdminMenu(); }
	}
	public static void determineCreate() { // COMPLETED
		if(option == 0) {
			createStudent();
		}
		else if(option == 1) {
			createTeacher();
		}
		else if(option == 2) {
			createManager();
		}
		else if(option == 3) {
			createExe();
		}
		else {
			showErrorMess();
			adminAddNew();
		}
	}
	public static Department determineDep() { // COMPLETED
		System.out.println("Choose the department: \n"
				+ "[0] - BASE \n[1] - FIT \n[2] - BS \n"
				+ "[3] - FOGI \n[4] - ISE");
		
		if(enterOption()) {
			if(option == 0) {
				return Department.BASE;
			}
			else if(option == 1) {
				return Department.FIT;
			}
			else if(option == 2) {
				return Department.BS;
			}
			else if(option == 3) {
				return Department.FOGI;
			}
			else if(option == 4) {
				return Department.ISE;
			}
			else {
				return determineDep();
			}
		}
		return determineDep();
	}
	public static void showUserMenu() { // COMPLETED
		System.out.println("[0] - authorize \n[1] - Exit");
		if(enterOption()) {
			doUserOption();
		}	
	}
	public static void showStudentMenu() { // COMPLETED
		System.out.println("[0] - view available courses \n[1] - view info about specific course \n"
				+ "[2] - view info about specific teacher \n[3] - view current attestation marks"
				+ "\n[4] - view Transcript \n[5] - register to course \n[6] - Exit");
		
		if(enterOption()) {
			doStudentOption();
		}	
	}
	public static void showTeacherMenu() { // COMPLETED
		System.out.println("[0] - view your courses \n[1] - view your students \n"
				+ "[2] - view info about specific student \n[3] - put mark to student"
				+ "\n[4] - put absent to student \n[5] - Exit");
		
		if(enterOption()) {
			doTeacherOption();
		}	
	}
	public static void showManagerMenu() { // COMPLETED
		System.out.println("[0] - open a new course \n[1] - create new course file "
				+ "\n[2] - view all courses \n[3] - view all course files"
				+ "\n[4] - assign course to teacher \n[5] - send order"
				+ "\n[6] - Exit");	
		if(enterOption()) {
			doManagerOption();
		}
	}
	public static void showExecutorMenu() { // COMPLETED
		System.out.println("[0] - view all orders \n[1] - view only accepted orders "
				+ "\n[2] - view orders of a specific employee \n[3] - accept order"
				+ "\n[4] - Exit");	
		if(enterOption()) {
			doExecutorOption();
		}
	}
	public static void showAdminMenu() { // COMPLETED
		System.out.println("[0] - view all users \n[1] - view info about specific user "
				+ "\n[2] - add new user \n[3] - remove user \n[4] - Exit");	
		if(enterOption()) {
			doAdminOption();
		}
	}
	public static void showErrorMess() { // COMPLETED
		System.out.println("Invalid input.");
	}
	public static void doUserOption() { // COMPLETED
		if(option == 0) {
			authorize();
		}
		else if(option == 1) {
			serialize();
			System.exit(0);
		}
		else {
			showErrorMess();
			showUserMenu();
		}
	}
	public static void doStudentOption() { // COMPLETED
		if(option == 0) { studentViewCourses(); }
		else if(option == 1) { studentViewCourse(); }
		else if(option == 2) { studentViewTeacher(); }
		else if(option == 3) { studentViewMarks(); }
		else if(option == 4) { studentViewTranscript(); }
		else if(option == 5) { studentRegister(); }
		else if(option == 6) { showUserMenu(); }
		else { studentError(); }
	}
	public static void doTeacherOption() { // COMPLETED
		if(option == 0) { teacherViewCourses(); }
		else if(option == 1) { teacherViewStudents(); }
		else if(option == 2) { teacherViewAbout(); }
		else if(option == 3) { teacherPutMark(); }
		else if(option == 4) { teacherPutAbsent(); }
		else if(option == 5) { showUserMenu(); }
		else { teacherError(); }
	}
	public static void doManagerOption() { // COMPLETED
		if(option == 0) { managerOpenCourse(); }
		else if(option == 1) { managerCreateCourseFile(); }
		else if(option == 2) { managerViewCourses(); }
		else if(option == 3) { managerViewCourseFiles(); }
		else if(option == 4) { managerAssignTo(); }
		else if(option == 5) { sendOrder(); showManagerMenu(); }
		else if(option == 6) { showUserMenu(); }
		else { managerError(); }
	}
	public static void doExecutorOption() { // COMPLETED
		if(option == 0) { exeViewAll(); }
		else if(option == 1) { exeViewAccepted(); }
		else if(option == 2) { exeViewOf(); }
		else if(option == 3) { exeAccept(); }
		else if(option == 4) { showUserMenu(); }
		else { exeError(); }
	}
	public static void doAdminOption() { // COMPLETED
		if(option == 0) { adminViewAll(); }
		else if(option == 1) { adminViewAbout(); }
		else if(option == 2) { adminAddNew(); }
		else if(option == 3) { adminRemove(); }
		else if(option == 4) { showUserMenu(); }
		else { adminError(); }
	}
	public static void studentViewCourses() { // COMPLETED : NOT TESTED
		System.out.println("Enter the ID of manager whose courses you wish to view: ");
		Manager m = findManager(getIntInput());
		
		if(m != null) {
			((Student)user).viewCoursesOf(m);
		}
		else {
			System.out.println("Manager with entered ID was not found.\n");
		}
		showStudentMenu();
	}
	public static void studentViewCourse() { // COMPLETED : NOT TESTED
		System.out.println("Enter the ID of course: ");
		Course c = findCourseById(getIntInput());
		
		if(c != null) {
			((Student)user).viewCourseInfo(c);
		}
		else {
			System.out.println("Course with entered ID does not exist.\n");
		}
		showStudentMenu();
	}
	public static void studentViewTeacher() { // COMPLETED : NOT TESTED
		System.out.println("Enter the ID of teacher: ");
		Teacher t = (Teacher)findUserById(getIntInput());
		
		if(t != null) {
			((Student)user).viewInfoAbout(t);
		}
		else {
			System.out.println("Teacher with entered ID does not exist.\n");
		}
		showStudentMenu();
	}
	public static void studentViewMarks() { // COMPLETED : NOT TESTED
		if(((Student)user).getCurrentCourses().isEmpty()) {
			System.out.println("You have not registered for courses. \n");
		}
		else {
			((Student)user).viewMarks();
		}
		showStudentMenu();
	}
	public static void studentViewTranscript() { // COMPLETED : NOT TESTED
		if(((Student)user).getPassedCourses().isEmpty()) {
			System.out.println("You have not passed a single course. \n");
		}
		else {
			System.out.println("Total GPA: " + ((Student)user).getGpa() + "\n");
			((Student)user).viewTranscript();
		}
		showStudentMenu();
	}
	public static void studentRegister() { // COMPLETED
		System.out.println("Enter the ID of teacher: ");
		User t = findUserById(getIntInput());
		
		if(t != null && t instanceof Teacher) {
			System.out.println("Enter the ID of course: ");
			Course c = findCourseById(getIntInput());
			
			if(c != null) {
				((Student)user).registerTo((Teacher)t, c);
				System.out.println("Registered successfully");
			}
			else {
				System.out.println("Course with entered ID does not exist.\n");
			}
		}
		else {
			System.out.println("Teacher with entered ID does not exist.\n");
		}
		showStudentMenu();
	}
	public static void studentError() { // COMPLETED
		showErrorMess();
		showStudentMenu();
	}
	public static void teacherViewCourses() { // COMPLETED
		if(((Teacher)user).getTeacherCourses().isEmpty()) {
			System.out.println("You have no courses.\n");	
		}
		else {
			((Teacher)user).viewCourses();
		}
		showTeacherMenu();
	}
	public static void teacherViewStudents() { // CHANGE
		((Teacher)user).viewStudents();
		showTeacherMenu();
	}
	public static void teacherViewAbout() { // COMPLETED : NOT TESTED
		System.out.println("Enter the ID of student: ");
		Student s = (Student)findUserById(getIntInput());
		
		if(s != null) {
			((Teacher)user).viewInfoAbout(s);
		}
		else {
			System.out.println("Student with entered ID does not exist.\n");
		}
		showTeacherMenu();
	}
	public static void teacherPutMark() { // COMPLETED
		double d;
		System.out.println("Enter the ID of student: ");
		User s = findUserById(getIntInput());
		if(s != null && s instanceof Student) {
			System.out.println("Enter the ID of course: ");
			Course c = findCourseById(getIntInput());
			if(c != null) {
				System.out.println("Enter mark: ");
				d = getDoubleInput();
				((Teacher)user).putMark(c, (Student)s, d);
				System.out.println("Operation succeed. \n");
			}
			else {
				System.out.println("Course with entered ID does not exist.\n");
			}
		}
		else {
			System.out.println("Student with entered ID does not exist.\n");
		}
		showTeacherMenu();
	}
	public static void teacherPutAbsent() { // COMPLETED
		System.out.println("Enter the ID of student: ");
		User s = findUserById(getIntInput());
		
		if(s != null && s instanceof Student) {
			System.out.println("Enter the ID of course: ");
			Course c = findCourseById(getIntInput());
			if(c != null) {
				((Teacher)user).putAbsent(c, (Student)s);
				System.out.println("Operation succeed. \n");
			}
			else {
				System.out.println("Course with entered ID does not exist.\n");
			}
		}
		else {
			System.out.println("Student with entered ID does not exist.\n");
		}
		showTeacherMenu();
	}
	public static void teacherError() { // COMPLETED
		showErrorMess();
		showTeacherMenu();
	}
	public static void managerOpenCourse() { // COMPLETED
		String s;
		System.out.println("Enter the course title: ");
		s = getStringInput();
		CourseFile cf = findCourseFile(s);
		
		if(cf != null) {
			((Manager)user).openCourse(cf);
			admin.incCourseId();
			System.out.println("Course was successfully added.\n");
		}
		else {
			System.out.println("Course file with entered title does not exist.\n");
		}
		showManagerMenu();
	}
	public static void managerCreateCourseFile() { // COMPLETED
		String s;
		int num;
		System.out.println("Enter the course title: ");
		s = getStringInput();
		System.out.println("Enter the number of credits: ");
		num = getIntInput();
		((Manager)user).createCourseFile(num, s);
		System.out.println("Course File was successfully added.\n");
		showManagerMenu();
	}
	public static void managerViewCourses() { // COMPLETED
		if(!((Manager)user).getCourses().isEmpty()) {
			((Manager)user).viewCourses();
		}
		else {
			System.out.println("You have no courses.\n");
		}
		showManagerMenu();
	}
	public static void managerViewCourseFiles() { // COMPLETED
		if(!((Manager)user).getCourseFiles().isEmpty()) {
			((Manager)user).viewCourseFiles();
		}
		else {
			System.out.println("You have no course files.\n");
		}
		showManagerMenu();
	}
	public static void managerAssignTo() { // COMPLETED
		System.out.println("Enter the ID of course: ");
		Course c = findCourseById(getIntInput());
		
		if(c != null) {
			System.out.println("Enter the ID of teacher: ");
			User t = findUserById(getIntInput());
			if(t != null && t instanceof Teacher) {
				((Manager)user).assignCourseTo((Teacher)t, c);
				System.out.println("Assigned successfully. \n");
			}
			else {
				System.out.println("Teacher with entered ID does not exist. \n");
			}
		}
		else {
			System.out.println("Course with entered ID does not exist.\n");
		}
		showManagerMenu();
	}
	public static void managerError() { // COMPLETED
		showErrorMess();
		showManagerMenu();
	}
	public static void exeViewAll() { // COMPLETED 
		if(!((Executor)user).getOrders().isEmpty()) {
			System.out.println("No orders were found.\n");
		}
		else {
			((Executor)user).viewOrders();
		}
		showExecutorMenu();
	}
	public static void exeViewAccepted() { // COMPLETED 
		if(!((Executor)user).getOrders().isEmpty()) {
			System.out.println("No orders were found.\n");
		}
		else {
			((Executor)user).viewAcceptedOrders();
		}
		showExecutorMenu();
	}
	public static void exeViewOf() { // COMPLETED 
		System.out.println("Enter the ID of employee: ");
		int inp = getIntInput();
		for(Order o: ((Executor)user).getOrders()) {
			if(inp == o.getEmployee().getId()) {
				System.out.println(o);
			}
		}
		showExecutorMenu();
	}
	public static void exeAccept() { // COMPLETED 
		System.out.println("Enter the id of order: ");
		int inp = getIntInput();
		for(Order o: ((Executor)user).getOrders()) {
			if(inp == o.getId()) {
				o.setAccepted();
				System.out.println("The order " + o.getId() + " was successfully accepted.");
			}
		}
		showExecutorMenu();
	}
	public static void exeError() { // COMPLETED
		showErrorMess();
		showExecutorMenu();
	}
	public static void adminViewAll() { // COMPLETED
		admin.viewUsers();
		showAdminMenu();
	}
	public static void adminViewAbout() { // COMPLETED
		System.out.println("Enter the ID: ");
		User u = findUserById(getIntInput());
		
		if(u != null) {
			System.out.println(admin.viewInfoAbout(u));
		}
		else {
			System.out.println("User with entered ID does not exist.\n");
		}
		showAdminMenu();
	}
	public static void adminAddNew() { // COMPLETED
		System.out.println("Choose the user title: \n"
				+ "[0] - Student \n[1] - Teacher \n[2] - Manager \n"
				+ "[3] - Executor");
		
		if(enterOption()) {
			determineCreate();
		}
		showAdminMenu();
	}
	public static void adminRemove() { // COMPLETED
		System.out.println("Enter the ID: ");
		User u = findUserById(getIntInput());
		
		if(u != null) {
			admin.removeUser(u);
			System.out.println("User was successfully removed.\n");
		}
		else {
			System.out.println("User with entered ID does not exist.\n");
		}
		showAdminMenu();
	}
	public static void adminError() { // COMPLETED
		showErrorMess();
		showAdminMenu();
	}
	public static boolean enterOption() { // COMPLETED
		try {
		    option = Integer.parseInt(getStringInput());
		} 
		catch (NumberFormatException e) {
			showErrorMess();
		    enterOption();
		}
		return true;
	}
	public static void createStudent() { // COMPLETED
		String firstName, lastName, log, pass;
		
		System.out.println("Enter the first name: ");
		firstName = getStringInput();
		System.out.println("Enter the last name: ");
		lastName = getStringInput();
		System.out.println("Enter the e-mail: ");
		log = getStringInput();
		System.out.println("Enter the password: ");
		pass = getStringInput();
		
		admin.addUser(new Student(firstName, lastName, log, pass, determineDep()));
	}
	public static void createExe() { // COMPLETED
		String firstName, lastName, log, pass;
		int salary;
		
		System.out.println("Enter the first name: ");
		firstName = getStringInput();
		System.out.println("Enter the last name: ");
		lastName = getStringInput();
		System.out.println("Enter the e-mail: ");
		log = getStringInput();
		System.out.println("Enter the password: ");
		pass = getStringInput();
		System.out.println("Enter the salary: ");
		salary = getIntInput();
		
		admin.addUser(new Executor(firstName, lastName, log, pass, salary));
	}
	public static void createTeacher() { // COMPLETED
		String firstName, lastName, log, pass;
		int salary;
		
		System.out.println("Enter the first name: ");
		firstName = getStringInput();
		System.out.println("Enter the last name: ");
		lastName = getStringInput();
		System.out.println("Enter the e-mail: ");
		log = getStringInput();
		System.out.println("Enter the password: ");
		pass = getStringInput();
		System.out.println("Enter the salary: ");
		salary = getIntInput();		
		admin.addUser(new Teacher(firstName, lastName, log, pass, salary, determineDep()));
	}
	public static void createManager() { // COMPLETED
		String firstName, lastName, log, pass;
		int salary;
		
		System.out.println("Enter the first name: ");
		firstName = getStringInput();
		System.out.println("Enter the last name: ");
		lastName = getStringInput();
		System.out.println("Enter the e-mail: ");
		log = getStringInput();
		System.out.println("Enter the password: ");
		pass = getStringInput();
		System.out.println("Enter the salary: ");
		salary = getIntInput();		
		admin.addUser(new Manager(firstName, lastName, log, pass, salary, determineDep()));
	}
	public static void sendOrder() { // COMPLETED
		String description;
		System.out.println("Enter the description of your order: ");
		description = getStringInput();
		System.out.println("Enter the ID of the executor: ");
		User e = findUserById(getIntInput());
		if(e != null && e instanceof Executor) {
			((Employee)user).sendOrderTo((Executor)e, description);
			admin.incOrderId();
			System.out.println("\nThe order was successfully sended.\n");
		}
		else {
			System.out.println("Executor with entered ID does not exist. \n");
			sendOrder();
		}
	}
	public static int getIntInput() { // COMPLETED
		int inp;
		try {
			inp = Integer.parseInt(input.readLine());
			return inp;
		} catch (NumberFormatException e) {
			showErrorMess();
			System.out.println("\nEnter again: ");
		} catch (IOException e) {
			showErrorMess();
			System.out.println("\nEnter again: ");
		}
		return getIntInput();
	}
	public static double getDoubleInput() { // COMPLETED
		double d;
		try {
			d = Double.parseDouble(input.readLine());
			return d;
		} catch (NumberFormatException e) {
			showErrorMess();
			System.out.println("\nEnter again: ");
		} catch (IOException e) {
			showErrorMess();
			System.out.println("\nEnter again: ");
		}
		return getDoubleInput();
	}
	public static String getStringInput() {
		String s = null;
		try {
			s = input.readLine();
			return s;
		} catch (IOException e) {
			showErrorMess();
			System.out.println("\nEnter again: ");
		}
		return getStringInput();
	}
	public static void setIds() { // COMPLETED
		Course course = new Course(null);
		Order order = new Order(null, null);
		admin.setNextId(admin.getUserId());
		course.setNextId(admin.getCourseId());
		order.setNextId(admin.getOrderId());
	}
	public static void deserialize() { // COMPLETED
		try {
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream("admin.out"));
			try {
				admin = (Admin)oin.readObject();
				oin.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void serialize() { // COMPLETED
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("admin.out"));
			oos.writeObject(admin);
			oos.flush();
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

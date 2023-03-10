package jpa.schoolmanager;



import static java.lang.System.out;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentCourseService;
import jpa.service.StudentService;

/**1
 * 
 * @author Uchenna Ofodile
 *
 */
public class SMSRunner {

	private Scanner sin;
	private StringBuilder sb;

	private CourseService courseService;
	private StudentService studentService;
	private Student currentStudent;

	public SMSRunner() {
		sin = new Scanner(System.in);
		sb = new StringBuilder();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SMSRunner sms = new SMSRunner();
		sms.run();
	}

	private void run() {
		// Runs until a valid input is selected unless the user ends 
		boolean validInput=false;
		while(!validInput) {
		switch (menu1()) {
		case 1:
			if (studentLogin()) {
				registerMenu();
			}
			break;
		case 2:
			//Ends program
			out.println("Goodbye!");
			return;

		default: out.println("Invalid Selection. Please pick 1 or 2");
		
		break;
		}
		}
	}

	private int menu1() {
		sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		return sin.nextInt();
	}

	private boolean studentLogin() {
		boolean retValue = false;
		out.print("Enter your email address: ");
		String email = sin.next();
		out.print("Enter your password: ");
		String password = sin.next();

		List<Student> students = studentService.getStudentByEmail(email);
		if (students != null) {
			currentStudent = students.get(0);
		}

		if (currentStudent != null & currentStudent.getsPass().equals(password)) {
			List<Course> courses = studentService.getStudentCourses(email);
			out.println("MyClasses");
			for (Course course : courses) {
				out.println(course);
			}
			retValue = true;
		} else {
			out.println("User Validation failed. GoodBye!");
		}
		return retValue;
	}

	private void registerMenu() {
		sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
		out.print(sb.toString());
		sb.delete(0, sb.length());

		switch (sin.nextInt()) {
		case 1:
	
		     List<Course> sCourses = studentService.getStudentCourses(currentStudent.getsEmail());
             out.println("MyClasses");
             for (Course course : sCourses) {
                 out.println(course);}
			
			List<Course> allCourses = courseService.getAllCourses();
			List<Course> studentCourses = studentService.getStudentCourses(currentStudent.getsEmail());
			allCourses.removeAll(studentCourses);
			out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
			for (Course course : allCourses) {
				out.println(course);
			}
			out.println();
			out.print("Enter Course Number: ");
			try {//Verifies correct input and valid course number
			int number = sin.nextInt();
			Course newCourse = courseService.GetCourseById(number).get(0);

			if (newCourse != null) {
				boolean result = studentService.registerStudentToCourse(currentStudent.getsEmail(), newCourse);
				
			if(result==false) {
					System.out.println("You are already registered in "+ newCourse.getcName() +". Please register for a new course.");
					registerMenu();
			}
				Student temp = studentService.getStudentByEmail(currentStudent.getsEmail()).get(0);
				
				StudentCourseService scService = new StudentCourseService();
				
				sCourses = scService.getAllStudentCourses(temp.getsEmail());
				
				
				out.println("MyClasses");
				for (Course course : sCourses) {
					out.println(course);
				}
			}
			}
			catch (InputMismatchException e) {//Handles invalid input and invalid course numbers
	            out.println("Invalid input. Please enter a number.");
	        } catch (IndexOutOfBoundsException e) {
	            out.println("Invalid course number. Please enter a valid course number.");
	        }
			break;
		case 2:
		default:
			out.println("Goodbye!");
			System.exit(0);//Closes program
		}
	}
}

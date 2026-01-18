import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentManager studentManager = new StudentManager();
        CourseCatalog courseCatalog = new CourseCatalog();
        EnrollmentMap enrollmentMap = new EnrollmentMap();
        GradeBook gradeBook = new GradeBook();

        // one waitlist per course
        HashMap<String, CourseWaitlist> waitlists = new HashMap<>();

        try {

            int choice = -1;

            while (choice != 0) {

                System.out.println("\n ___ UNIVERSITY SYSTEM MENU ___");
                System.out.println("1.  Add Student");
                System.out.println("2.  Remove Student");
                System.out.println("3.  Add Course");
                System.out.println("4.  Remove Course");
                System.out.println("5.  Enroll Student in Course");
                System.out.println("6.  View Students in Course");
                System.out.println("7.  Assign Grade");
                System.out.println("8.  View Transcript");
                System.out.println("9.  Show GPA");
                System.out.println("10. Add Student to Waitlist");
                System.out.println("11. Process Course Waitlist");
                System.out.println("12. Display All Students");
                System.out.println("13. Display All Courses");
                System.out.println("14. Display All Enrollments");
                System.out.println("15. Display Full System Report");
                System.out.println("0.  Exit");
                System.out.print("Enter choice: ");

                // exception handling to menu checking
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1: // add student to the list
                        try {
                            System.out.print("Enter Student ID: ");
                            String sid = sc.nextLine();

                            System.out.print("Enter Name: ");
                            String name = sc.nextLine();

                            System.out.print("Enter Email: ");
                            String email = sc.nextLine();

                            System.out.print("Enter Year: ");
                            int year = Integer.parseInt(sc.nextLine());

                            Student s = new Student(sid, name, email, year);
                            studentManager.addStudent(s);

                            System.out.println("Student added successfully.");

                        } catch (Exception e) {
                            System.out.println("Error adding student: " + e.getMessage());
                        }
                        break;

                    // REMOVE STUDENT
                    case 2:
                        System.out.print("Enter Student ID to remove: ");
                        String removeId = sc.nextLine();
                        boolean removed = studentManager.removeStudent(removeId);
                        if (removed)
                            System.out.println("Student removed.");
                        break;

                    // ADD COURSE
                    case 3:
                        try {
                            System.out.print("Enter Course Code: ");
                            String ccode = sc.nextLine();

                            System.out.print("Enter Course Name: ");
                            String cname = sc.nextLine();

                            System.out.print("Enter Credits: ");
                            int credits = Integer.parseInt(sc.nextLine());

                            System.out.print("Enter Capacity: ");
                            int cap = Integer.parseInt(sc.nextLine());

                            Course c = new Course(ccode, cname, credits, cap);
                            courseCatalog.addCourseAtEnd(c);

                            // create a waitlist for this course
                            waitlists.putIfAbsent(ccode, new CourseWaitlist());

                            System.out.println("Course added.");

                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number input for credits/capacity.");
                        }
                        break;
                    // remove course
                    case 4:
                        System.out.print("Enter Course Code to remove: ");
                        String rcode = sc.nextLine();
                        courseCatalog.removeCourse(rcode);
                        waitlists.remove(rcode);
                        break;
                    // enroll student to the course
                    case 5:
                        try {
                            System.out.print("Student ID: ");
                            String sid = sc.nextLine();

                            System.out.print("Course Code: ");
                            String cid = sc.nextLine();

                            Student st = studentManager.getStudent(sid);
                            Course course = courseCatalog.searchCourse(cid);

                            if (st == null) {
                                System.out.println("Student not found.");
                                break;
                            }
                            if (course == null) {
                                System.out.println("Course not found.");
                                break;
                            }

                            int enrolledCount = enrollmentMap.getEnrollmentCountForCourse(course);

                            if (enrolledCount >= course.getCapacity()) {
                                System.out.println("Course is full. Student added to waitlist.");
                                System.out.print("Enter waitlist priority: ");

                                int priority;
                                try {
                                    priority = Integer.parseInt(sc.nextLine());
                                } catch (NumberFormatException e) {
                                    priority = 100; // default low priority
                                }

                                waitlists.get(cid).addToWaitlist(st, priority);
                                break;
                            }

                            enrollmentMap.enrollStudentInCourse(st, course);
                            System.out.println("Enrollment successful!");

                        } catch (Exception e) {
                            System.out.println("Enrollment error: " + e.getMessage());
                        }
                        break;

                    // vIEW STUDENTS IN A COURSE
                    case 6:
                        System.out.print("Enter Course Code: ");
                        String code = sc.nextLine();
                        Course cr = courseCatalog.searchCourse(code);
                        if (cr == null) {
                            System.out.println("Course not found.");
                            break;
                        }

                        List<Student> list = enrollmentMap.getStudentsInCourse(cr);
                        System.out.println("___ Students in " + code + " ___");
                        for (Student ss : list)
                            System.out.println(ss);
                        break;
                    // assign a grade
                    case 7:
                        try {
                            System.out.print("Enter Student ID: ");
                            String sid = sc.nextLine();

                            System.out.print("Enter Course Code: ");
                            String cc = sc.nextLine();

                            System.out.print("Enter Grade: ");
                            double grade = Double.parseDouble(sc.nextLine());

                            gradeBook.assignGrade(sid, cc, grade);
                            System.out.println("Grade assigned.");
                        } catch (NumberFormatException e) {
                            System.out.println("Grade must be a number.");
                        }
                        break;
                    // view transcript of student
                    case 8:
                        System.out.print("Enter Student ID: ");
                        String sid2 = sc.nextLine();
                        List<Map.Entry<StudentCourseKey, Double>> grades = gradeBook.getAllGradesForStudent(sid2);
                        if (grades.isEmpty()) {
                            System.out.println("No grade records found.");
                        } else {
                            System.out.println("___ Transcript ___");
                            for (var g : grades)
                                System.out.println(g.getKey().getCourseCode() + ": " + g.getValue());
                        }
                        break;

                    // show gpa of student
                    case 9:
                        System.out.print("Enter Student ID: ");
                        String gpaId = sc.nextLine();
                        double gpa = gradeBook.getStudentGPA(gpaId);
                        if (gpa < 0)
                            System.out.println("No GPA records available.");
                        else
                            System.out.println("GPA = " + gpa);
                        break;
                    // add student to waitlist
                    case 10:
                        try {
                            System.out.print("Student ID: ");
                            String sidW = sc.nextLine();

                            System.out.print("Course Code: ");
                            String cidW = sc.nextLine();

                            Student sw = studentManager.getStudent(sidW);
                            Course cw = courseCatalog.searchCourse(cidW);

                            if (sw == null || cw == null) {
                                System.out.println("Student or course not found.");
                                break;
                            }

                            System.out.print("Priority: ");
                            int p = Integer.parseInt(sc.nextLine());

                            waitlists.get(cidW).addToWaitlist(sw, p);

                            System.out.println("Added to waitlist.");

                        } catch (Exception e) {
                            System.out.println("Invalid waitlist input.");
                        }
                        break;

                    // process waitlist of students
                    case 11:
                        System.out.print("Enter Course Code: ");
                        String cl = sc.nextLine();

                        Course ccc = courseCatalog.searchCourse(cl);
                        if (ccc == null) {
                            System.out.println("Course not found.");
                            break;
                        }

                        CourseWaitlist w = waitlists.get(cl);
                        if (w == null || w.getWaitlistSize() == 0) {
                            System.out.println("Waitlist is empty.");
                            break;
                        }

                        WaitlistEntry entry = w.processNextStudent();

                        int enrolledNow = enrollmentMap.getEnrollmentCountForCourse(ccc);
                        if (enrolledNow < ccc.getCapacity()) {
                            enrollmentMap.enrollStudentInCourse(entry.getStudent(), ccc);
                            System.out.println("Waitlisted student enrolled: " + entry.getStudent().getName());
                        } else {
                            System.out.println("Still full. Cannot enroll waitlisted student.");
                        }

                        break;
                    // show all students
                    case 12:
                        studentManager.displayAllStudents();
                        break;
                    // show all courses
                    case 13:
                        courseCatalog.getAllCourses();
                        break;
                    // show all enrollments
                    case 14:
                        enrollmentMap.displayAllEnrollments();
                        break;

                    case 15:
                        studentManager.displayAllStudents();
                        courseCatalog.getAllCourses();
                        enrollmentMap.displayAllEnrollments();
                        for (String courseCode : waitlists.keySet()) {
                            CourseWaitlist wl = waitlists.get(courseCode);
                            System.out.println("\nWaitlist for Course: " + courseCode);
                            wl.displayWaitlist();
                        }
                        break;

                    case 0:
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid menu option.");
                }
            }

        } finally {
            sc.close();
            System.out.println("Program closed.");
        }
    }
}

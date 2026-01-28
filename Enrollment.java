import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Enrollment {
    HashMap<Student, List<Course>> enrollmentMap = new HashMap<>();

    // enroll student in a course
    public void enrollStudentInCourse(Student s, Course c) {
        // if student not in map, create new list
        if (!enrollmentMap.containsKey(s)) {
            enrollmentMap.put(s, new ArrayList<Course>());
        }

        // add the course
        enrollmentMap.get(s).add(c);
    }

    // get list of courses for a student
    public List<Course> getStudentCourses(Student s) {
        return enrollmentMap.getOrDefault(s, new ArrayList<>());
    }

    // drop a specific course
    public void dropCourse(Student s, Course c) {
        if (enrollmentMap.containsKey(s)) {
            enrollmentMap.get(s).remove(c);
        }
    }

    // total number of enrollments
    public int getTotalEnrollments() {
        int total = 0;

        for (List<Course> list : enrollmentMap.values()) {
            total += list.size();
        }
        return total;
    }

    // display all enrollments
    public void displayAllEnrollments() {
        for (Map.Entry<Student, List<Course>> entry : enrollmentMap.entrySet()) {

            Student s = entry.getKey();
            List<Course> courses = entry.getValue();

            System.out.println(s.getName() + " (" + s.getStudentId() + ") enrolled in:");

            for (Course c : courses) {
                System.out.println("  - " + c.getCourseCode() + " " + c.getCourseName());
            }

            System.out.println();
        }
    }

    // get all students enrolled in a specific course
    public List<Student> getStudentsInCourse(Course c) {
        List<Student> enrolledStudents = new ArrayList<>();

        for (Map.Entry<Student, List<Course>> entry : enrollmentMap.entrySet()) {
            if (entry.getValue().contains(c)) {
                enrolledStudents.add(entry.getKey());
            }
        }
        return enrolledStudents;
    }
}

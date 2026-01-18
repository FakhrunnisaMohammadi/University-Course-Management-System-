import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrollmentMap {
    Map<Student, List<Course>> enrollmentMap = new HashMap<>();

    // enroll student in a course
    public void enrollStudentInCourse(Student s, Course c) {
        if (s == null || c == null)
            return;
        List<Course> list = enrollmentMap.get(s);
        if (list == null) {
            list = new ArrayList<>();
            enrollmentMap.put(s, list);
        }
        list.add(c);
        // System.out.println("Enrolled " + s.getStudentId() + " in " +
        // c.getCourseCode());
    }

    // get list of courses for a student
    public List<Course> getStudentCourses(Student s) {
        List<Course> list = enrollmentMap.get(s);
        if (list == null)
            return Collections.emptyList();
        return new ArrayList<>(list);
    }

    // drop a specific course
    public boolean dropCourse(Student s, Course c) {
        List<Course> list = enrollmentMap.get(s);
        if (list == null)
            return false;
        boolean removed = list.remove(c);
        if (removed)
            System.out.println("Dropped " + c.getCourseCode() + " for " + s.getStudentId());
        return removed;
    }

    // total number of enrollments
    public int getTotalEnrollments() {
        int total = 0;
        for (List<Course> list : enrollmentMap.values())
            total += list.size();
        return total;
    }

    // show all enrollments
    public void displayAllEnrollments() {
        System.out.println("=== Enrollment Map ===");
        for (Map.Entry<Student, List<Course>> entry : enrollmentMap.entrySet()) {
            System.out.println("Student: " + entry.getKey().getName());
            System.out.print("  Courses: ");
            List<Course> courses = entry.getValue();
            for (int i = 0; i < courses.size(); i++) {
                System.out.print(courses.get(i).getCourseCode());
                if (i < courses.size() - 1)
                    System.out.print(", ");
            }
            System.out.println();
        }
    }

    // get all students enrolled in aspecific course
    public List<Student> getStudentsInCourse(Course c) {
        List<Student> res = new ArrayList<>();
        for (Map.Entry<Student, List<Course>> entry : enrollmentMap.entrySet()) {
            if (entry.getValue().contains(c))
                res.add(entry.getKey());
        }
        return res;
    }

    // helper to check if a course is full for a specific course code
    public int getEnrollmentCountForCourse(Course c) {
        int count = 0;
        for (List<Course> list : enrollmentMap.values()) {
            for (Course cc : list) {
                if (cc.equals(c))
                    count++;
            }
        }
        return count;
    }

}

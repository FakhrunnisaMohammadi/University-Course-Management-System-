import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class StudentManager {
    ArrayList<Student> studentList = new ArrayList<>();

    // add student here
    public void addStudent(Student s) {
        if (s == null) {
            return;
        }
        studentList.add(s);
        // System.out.println("Added: " + s.getStudentId());
    }

    // remove methode for removing student
    public boolean removeStudent(String studentId) {
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getStudentId().equals(studentId)) {
                it.remove();
                return true;
            }
        }
        System.out.println("Student not found: " + studentId);
        return false;
    }

    // finding student by student id
    public Student findStudentById(String studentId) {
        for (Student s : studentList) {
            if (s.getStudentId().equals(studentId))
                return s;
        }
        return null;
    }

    // display all students in this method
    public void displayAllStudents() {
        System.out.println("=== Student List ===");
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // get student by year in this method
    public ArrayList<Student> getStudentsByYear(int year) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if (s.getYear() == year)
                result.add(s);
        }
        return result;
    }

    public void sortStudentsByName() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.getName().compareToIgnoreCase(b.getName());
            }
        });
    }

    // get student method
    public Student getStudent(String studentId) {
        for (Student s : studentList) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null; // not found
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList);
    }
}

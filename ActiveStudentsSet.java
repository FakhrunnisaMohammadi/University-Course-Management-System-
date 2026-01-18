import java.util.HashSet;

public class ActiveStudentsSet {
    HashSet<Student> activeStudents = new HashSet<>();

    // Enroll student to the course
    public boolean enrollStudent(Student s) {
        boolean added = activeStudents.add(s);
        if (added)
            System.out.println("Enrolled: " + s.getStudentId());
        else
            System.out.println("Already active: " + s.getStudentId());
        return added;
    }

    // withdraw the student form course
    public boolean withdrawStudent(Student s) {
        boolean removed = activeStudents.remove(s);
        if (removed)
            System.out.println("Withdrawn: " + s.getStudentId());
        else
            System.out.println("Not found in active set: " + s.getStudentId());
        return removed;
    }

    // check if the student is active or not
    public boolean isStudentActive(String studentId) {
        for (Student s : activeStudents) {
            if (s.getStudentId().equals(studentId))
                return true;
        }
        return false;
    }

    // get the number of active students
    public int getActiveStudentCount() {
        return activeStudents.size();
    }

    // clear all enrollments
    public void clearAllEnrollments() {
        activeStudents.clear();
    }

    // convert hashset to array
    public Student[] convertToArray() {
        return activeStudents.toArray(new Student[0]);
    }
}

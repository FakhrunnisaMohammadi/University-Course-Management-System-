import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GradeBook {
    TreeMap<StudentCourseKey, Double> gradeBook = new TreeMap<>();

    // assing a grade to a student
    public void assignGrade(String studentId, String courseCode, double grade) {
        StudentCourseKey key = new StudentCourseKey(studentId, courseCode);
        gradeBook.put(key, grade);
    }

    // get grade for a specific student in a specific course
    public double getGrade(String studentId, String courseCode) {
        Double d = gradeBook.get(new StudentCourseKey(studentId, courseCode));
        return d == null ? -1 : d;
    }

    // get the student gpa using student id
    public double getStudentGPA(String studentId) {
        double sum = 0;
        int count = 0;
        for (Map.Entry<StudentCourseKey, Double> e : gradeBook.entrySet()) {
            if (e.getKey().getStudentId().equals(studentId)) {
                sum += e.getValue();
                count++;
            }
        }
        return count == 0 ? -1 : (sum / count); // -1 means no records for student
    }

    public List<Map.Entry<StudentCourseKey, Double>> getAllGradesForStudent(String studentId) {
        List<Map.Entry<StudentCourseKey, Double>> result = new ArrayList<>();
        for (Map.Entry<StudentCourseKey, Double> e : gradeBook.entrySet()) {
            if (e.getKey().getStudentId().equals(studentId))
                result.add(e);
        }
        return result;
    }

    // get the highest grad between students
    public double getHighestGrade() {
        double highest = -1;
        for (Double grade : gradeBook.values()) {
            if (grade > highest)
                highest = grade;
        }
        return highest;
    }

    // display grades sorted way
    public void displayGradesSorted() {
        for (Map.Entry<StudentCourseKey, Double> entry : gradeBook.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

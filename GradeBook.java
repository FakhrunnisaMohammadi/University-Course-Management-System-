import java.util.Map;
import java.util.TreeMap;

public class GradeBook {
    // Inner Class:StudentCourseKey
    class StudentCourseKey implements Comparable<StudentCourseKey> {
        String studentId;
        String courseCode;

        public StudentCourseKey(String studentId, String courseCode) {
            this.studentId = studentId;
            this.courseCode = courseCode;
        }

        // Compare first by studentId and after that by courseCode
        @Override
        public int compareTo(StudentCourseKey other) {
            int idCompare = this.studentId.compareTo(other.studentId);

            // if studentIds are equal then compare course codes
            if (idCompare == 0) {
                return this.courseCode.compareTo(other.courseCode);
            }
            return idCompare;
        }
    }

    TreeMap<StudentCourseKey, Double> gradeBook = new TreeMap<>();

    // assign the Grade
    public void assignGrade(String studentId, String courseCode, double grade) {
        StudentCourseKey key = new StudentCourseKey(studentId, courseCode);
        gradeBook.put(key, grade);
    }

    // get the Grade
    public double getGrade(String studentId, String courseCode) {
        StudentCourseKey key = new StudentCourseKey(studentId, courseCode);

        if (gradeBook.containsKey(key)) {
            return gradeBook.get(key);
        }
        return -1; // grade not found
    }

    // get Student GPA
    public double getStudentGPA(String studentId) {
        double sum = 0;
        int count = 0;

        for (Map.Entry<StudentCourseKey, Double> entry : gradeBook.entrySet()) {
            if (entry.getKey().studentId.equals(studentId)) {
                sum += entry.getValue();
                count++;
            }
        }

        if (count == 0)
            return -1; // no records for student
        return sum / count;
    }

    // get all grades for a student
    public void getAllGradesForStudent(String studentId) {
        System.out.println("Grades for Student: " + studentId);

        for (Map.Entry<StudentCourseKey, Double> entry : gradeBook.entrySet()) {
            if (entry.getKey().studentId.equals(studentId)) {
                System.out.println("Course: " + entry.getKey().courseCode +
                        "Grade: " + entry.getValue());
            }
        }
    }

    // get highest grade
    public double getHighestGrade() {
        double highest = -1;

        for (double grade : gradeBook.values()) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    // display grades sorted
    public void displayGradesSorted() {
        System.out.println("=== Sorted Grades ===");

        for (Map.Entry<StudentCourseKey, Double> entry : gradeBook.entrySet()) {
            System.out.println("Student: " + entry.getKey().studentId +
                    ", Course: " + entry.getKey().courseCode +
                    "Grade: " + entry.getValue());
        }
    }
}

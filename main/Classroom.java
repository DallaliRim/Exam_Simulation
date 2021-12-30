package main;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class creates a Classroom type that hold a List of students
 */

public class Classroom {
    private ArrayList<Student> students = new ArrayList<Student>();
    /**
     * This method searches for a student in the Classroom by taking its id as an input
     * return a student type in case the student exists  
     * @param student_id 
     * @return Student object
     */
    public Student searchStudent(String student_id) {
        for (Student student : this.students) {
            if (student.getStudentId().equals(student_id)) {
                return student;
            }
        }
        return null;
    }
    /**
     * This method generate an exam for a student by taking student id,number of questions and
     * the file name as an input by calling searchStudent method in this class and calling generate Exam 
     * that is in Student class 
     * @param student_id 
     * @param question_num
     * @param filename
     * @throws IOException
     */
    public void generateStudentExam(String student_id, int question_num, String filename) throws IOException { 
        searchStudent(student_id).generateExam(question_num, filename);
    }

    public void addStudent(String tfStudentId, String tfSampleExamFile, int tfNumberOfQuestions) {
        this.students.add(new Student(tfStudentId, tfNumberOfQuestions));
        
    }
    /**
     * adds a student object to an Arraylist of Student objects (this.students)
     * @param student student object
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }
}
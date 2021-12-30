package main;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Student { 
    private int question_num;
    private String student_id;
    private double average_grade;
    private int exam_id = -1;   //this is done for aesthetic purposes so that it starts with 0 instead of 1
    private List<Exam> studentExams = new ArrayList<Exam>();

    public Student(String tfStudentId, int tfQuestion_num){
        this.student_id = tfStudentId;
        this.average_grade = 0;   
        this.question_num = tfQuestion_num; 
    }

    //This constructor is used for the unit tests
    public Student(String tfStudentId, String tfExamFile, int tfQuestion_num){
        this.student_id = tfStudentId;
        this.average_grade = 0;   
        this.question_num = tfQuestion_num; 
    }

    /**
     * @return int representing the amount of exams -1. 
     */
    public int getExamId() {
        return this.exam_id;
    }

    public void upgradeExamCount() {
        this.exam_id++;
    }

    /**
     * @return String representing the student id
     */
    public String getStudentId(){
        return this.student_id;
    }

    /**
     * @param examID
     * @return Exam Object associated with the input examID
     */
    public Exam getStudentExam(int examID) {
        return this.studentExams.get(examID);
    }

    /**
     * searches for the exam associated with the input exam_id
     * @param exam_id
     * @return Exam Object
     */
    public Exam searchExam(int exam_id) {
        for (Exam exam : this.studentExams) {
            if (exam.getExamId() == exam_id) {
                return exam;
            }
        }
        return null;
    }

    /**
     * add a new exam to a list of Exam that represent a student exams 
     * and creates two methods to create exam file and an empty file where the user puts his answers 
     * @param questionNum number of question that the exam has
     * @param testFile name of file that has all the questions data 
     * @throws IOException if the file name doesn't exists
     */
    public void generateExam(int questionNum, String testFile) throws IOException {
        try {
            this.studentExams.add(new Exam(questionNum, testFile, this.exam_id));
        } catch (IllegalArgumentException e) {
            this.studentExams.add(new Exam(1, testFile, this.exam_id));
        }
        //increment the number of exams that a student has
        upgradeExamCount(); 
        this.createQuestionFile();
    }

    /**
     * Writes the exam questions to the created exam file
     */
    public void writeToQuestionFile() {
        try {
            FileWriter examWriter = new FileWriter("Exam_S" + this.getStudentId() + "_E" + this.exam_id + ".txt");
            this.studentExams.get(this.exam_id).upgradeExamId();
            examWriter.write(this.studentExams.get(this.exam_id).toString());
            examWriter.close();
            this.createAnswersFile();
        } catch (IOException e) {
            System.out.println("An error occurred with the file name.");
        } catch (IndexOutOfBoundsException ie) {
            System.out.println("Previous Files remaining, delete previously generated files by the program and try again");
        }
    }

    /**
     * Creates an empty Exam File for the student
     */
    public void createQuestionFile() { 
        try {
            File exam = new File("Exam_S" + this.getStudentId() + "_E" + this.exam_id + ".txt");
            if (exam.createNewFile()) {
                System.out.println("Questions File created: " + exam.getName());
            } else {
                this.exam_id++;
                this.studentExams.get(this.exam_id).upgradeExamId();
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            System.out.println("Previous Files remaining, delete previously generated files by the program and try again");
        }
        this.writeToQuestionFile();
    }

    /**
     * writes a string that represent an exam into a text file
    */
    public void writeToAnswersFile() {
        try {
            FileWriter ansFileWriter = new FileWriter("Exam_Answers_S" + this.student_id + "_E" + this.studentExams.get(this.exam_id).getExamId() + ".txt");
            for (int i = 0; i <= this.question_num;i++){
                ansFileWriter.write("-");
                ansFileWriter.write("\n");
            }
                ansFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        } 
    }

    /**
     * creates an empty file where the user puts his answers
     */
    public void createAnswersFile() {
       try {
            File studentAnswer = new File("Exam_Answers_S" + this.student_id + "_E" + this.studentExams.get(this.exam_id).getExamId() + ".txt");
            if(studentAnswer.createNewFile()){
                System.out.println("Answers file created" +  studentAnswer.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        } 
        this.writeToAnswersFile();
    }

    public String toString() { 
        return "ID : " + this.student_id + "\nAverage Grade : " + this.average_grade; 
    }
}

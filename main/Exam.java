package main;

import java.util.List;
import java.util.Random;
import Answer.*;
import Question.*;
import Result.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Exam  {
    private int exam_id;
    private int grade;
    private final int SAMPLETOTAL = 4;//total amount of question in the sample exam
    private int question_num;//amount of question the user wants to generate
    private List<Question> questions;
    private List<Answer> userAnswers;
    private List<Answer> correctAnswers;
    private Result result;
    private String filename;
    private ExamLoader loadExam;
    Random rand = new Random();

    /**
     * created Exam object
     * @param question_num number of questions included in the Exam
     * @param filename filename where it takes the data from
     * @param exam_id assing an exam id
     * @throws IOException throws if filename not exists
     */
    public Exam(int question_num, String filename, int exam_id) throws IOException { //make sure it is needed
        if (question_num > 0 && question_num <= SAMPLETOTAL) {
            this.question_num = question_num;
        }
        else {
            throw new IllegalArgumentException("The amount of questions is not between 1 and the total number of questions from the sample");
        }
        this.exam_id = exam_id;
        this.loadExam = new ExamLoader();
        this.grade = 0;
        this.filename = filename;
        this.questions = loadExam.loadQuestionFromFile(this.filename);
        this.correctAnswers = loadExam.loadCorrectAnswersFromFile(this.filename);
        setIds();
    }

    public void upgradeExamId() {
        this.exam_id++;
    }
    public int getExamId() {
        return this.exam_id;
    }

    /**
     * randomly generates questions for the exam equals to question_num field 
     */
    public void setIds() {
        for (int i = 0; i < (this.SAMPLETOTAL - this.question_num); i++) {
            int random = rand.nextInt(this.question_num);
            this.questions.remove(random);
            this.correctAnswers.remove(random);
        }
        int question_count = 1;
        for (Question question : questions) {
            question.setQuestionID("Q" + question_count);
            question_count++;
        }
    }
    /**
     * saving user Answers in a Array List and compare each 
     * answer in it to The Answers in correct Answer List and set the point gained based on
     * whether the answer in right or wrong and computing the final grade
     * @param testFile  correct answers file name
     * @param studentAnswersFile user answers file name
     * @throws IOException throws if whether of the filenames doesn't exists
     */
    public void gradeExam(String testFile, String studentAnswersFile) throws IOException {
        this.grade = 0;
        this.userAnswers = loadExam.loadUserAnswersFromFile(studentAnswersFile);
        for (int i = 0 ; i<userAnswers.size() -1 ; i++) {
            boolean goodAnswer = gradeQuestion(correctAnswers.get(i),this.userAnswers.get(i));
            if (goodAnswer) {
                this.userAnswers.get(i).setPointsGained(this.questions.get(i).getTOTALPOINTS());
            }
        }       
        computeExamGrade();
    }
    /**
     * computing the full mark
     * @return the total of full mark of each question type
     */
    public int getExam_total_points() {
        int total = 0;
        for (int i = 0; i < questions.size() -1 ; i++) {
            total += this.questions.get(i).getTOTALPOINTS();
        }
        return total;
    }
    /**
     * comparing student Answers with correct answers
     * @param correctAnswer
     * @param studentAnswer
     * @return a boolean whether the answers identical or no
     */
    public static boolean gradeQuestion(Answer correctAnswer, Answer studentAnswer){
        if(correctAnswer.equals(studentAnswer)){
            return true;
        } else {
            return false;
        }
    }
    /**
     * compute the final grade depending on the type of grade the user chose
     * letter of pass fail or percentage 
     */
    public void computeExamGrade() {
        for (Answer answer : this.userAnswers) {
            this.grade += answer.getPointsGained();
        }
        if (userAnswers.get(userAnswers.size()-1).getAnswer().toLowerCase().equals("letter")) {
            this.result = new LetterGrade(this.grade, this.getExam_total_points());
        }
        else if(userAnswers.get(userAnswers.size()-1).getAnswer().toLowerCase().equals("passfail")){
            this.result = new PassFail(this.grade, this.getExam_total_points());
        }
        else {
            this.result = new PercentageGrade(this.grade, this.getExam_total_points());
        }
    }

    public Result getResult() {
        return this.result;
    } 

    /**
     * displaying the correct answer and the user answer as a string 
     * calls the createReviewFile method with the input informations
     * @param studentID
     */
    public void displayExamReview(String studentID){
        String builder = "\n\nExam id : " + this.exam_id;
        for (int i = 0; i < this.correctAnswers.size(); i++) {
            builder += "\n " + this.questions.get(i);
            builder += "\n " + this.userAnswers.get(i);
            builder += "\n " + this.correctAnswers.get(i);
        }
        builder += "\nGrade : " + this.result.getGrade();
        createReviewFile(builder, studentID, this.exam_id);
    }

    /**
     * Create the review file containing the questions, user answers, correct answers
     * @param review
     * @param studentID
     * @param exam_id
     */
    public static void createReviewFile(String review, String studentID, int exam_id) {
        File exam = new File("Exam_Review_S" + studentID + "_E" + exam_id + ".txt");
        System.out.println("Questions File created: " + exam.getName());
        try {
            FileWriter examWriter = new FileWriter("Exam_Review_S" + studentID + "_E" + exam_id + ".txt");
            examWriter.write(review);
            examWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
 
    public String toString() {
        String builder = "\nExam id : " + this.exam_id;
        for (Question question : questions) {
            builder += "\n " + question;
        }
        return builder;
    } 
}

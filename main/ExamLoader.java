package main;
/**
 * this class to load the questions that inside the testfile.txt and the answers in the user answers file
 */
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.*; 

import Question.*;
import Answer.*;

public class ExamLoader implements FilesLoader{
    /**
     * Loads answers from user answers List by extracting the data from a file and assign it to a list of String 
     * then extract User Answers from this list and save them in a List of Answer
     * @param fileName user Answers file name
     * @return list of answers
     * @throws IOException if the file name doesn't exists
     */
    public List<Answer> loadUserAnswersFromFile(String fileName) throws IOException {
        Path p = Paths.get(fileName);
        List<String> user = Files.readAllLines(p);
        List<Answer> answers = loadUserAnswers(user);
        return answers;
    }
    /**
     * Loads answers from user answers List
     * @param UserAnswersFile list for user Answers
     * @return list of answers
     */
    public List<Answer> loadUserAnswers(List<String> UserAnswersFile){
        List<Answer> answers = new ArrayList<Answer>();
            for(String line : UserAnswersFile){
                String[] answer = line.split("-");
                Answer studentAnswer = new UserAnswer(answer[1]);
                answers.add(studentAnswer);
            }
            return answers;
    }
    /**
     * Load correct answers from a file by extracting the data from a file and assign it to a List of String
     * then get this list and extract the correct answers from it and save them in a list of Answer
     * @param fileName correct answers file name
     * @return list of correct answers
     * @throws IOException if file name doesn't exists
     */
    public List<Answer> loadCorrectAnswersFromFile(String fileName) throws IOException {
        Path p = Paths.get(fileName);
        List<String> user = Files.readAllLines(p);
        List<Answer> answers = loadCorrectAnswers(user);
        return answers;
    }

    /**
     * load correct answers from a List of String 
     * @param CorrectAnswerList
     * @return Answer list that holds the correct answers
     * @throws IllegalArgumentException if first index of the String[] has invalid value
     */
    public List<Answer> loadCorrectAnswers(List<String> CorrectAnswerList) throws IllegalArgumentException{
        List<Answer> answers = new ArrayList<Answer>();
            for(String line : CorrectAnswerList){
                String[] answer = line.split(";");
                //checking if the first index of answer has a valid question type
                if(answer[0].equals("TF") || answer[0].equals("SA") || answer[0].equals("MC")){
                    Answer correctAnswer = new CorrectAnswer(answer[2]);
                    answers.add(correctAnswer);
                }
                else { throw new IllegalArgumentException("invalid question type"); }
            }
        return answers;
    }

    /**
     * loads questions for a file by calling two methods
     * loadQuestionListFromfile that takes a file name as an input and returns a List of String
     * ans loadQuestions that takes the String list and return a list of Question
     * @param fileName file name that holds the questions information
     * @return list of Question objects
     * @throws IOException
     */
    public List<Question> loadQuestionFromFile(String fileName) throws IOException {
        List<String> examList =loadQuestionListFromFile(fileName);
        List<Question> questions = loadQuestions(examList);
        return questions;
    }

    /**
     * loads data from a file and assing it to a list of String 
     * @param fileName filename that holds the data
     * @return a list of String
     * @throws IOException if file name doesn't exists
     */
    public List<String> loadQuestionListFromFile(String fileName) throws IOException {
        Path p = Paths.get(fileName);
        List<String> examList = Files.readAllLines(p);
        return examList;
    }

    /**
     * extract questons info from a list of String and  calls a method that
     * creates a question and add it to a list of Question objects
     * @param QuestionsList list of Stirng 
     * @return list of Question object
     */
    public List<Question> loadQuestions(List<String> QuestionsList){
        List<Question> questions = new ArrayList<Question>();
        for (String line : QuestionsList) {
            String[] question = line.split(";");
            if (question[0].equals("SA")) { createSAquestion(question, questions); }
            else if(question[0].equals("MC")) { createMCquestion(question, questions); }
            else if(question[0].equals("TF")) { createTFquestion(question, questions); }
            else { throw new IllegalArgumentException("Wrong sample exam filformat."); }
        }        
        return questions;
    }

    /**
     * creates a question object of type Multiple Choice and add it to a list of Question object
     * @param question a string array that holds the question info
     * @param questions a List of question where the question object saved
     */
    public void createMCquestion(String[] question, List<Question> questions) {
        String[] choices = new String[] {question[3], question[4], question[5]};
        CorrectAnswer MCanswer = new CorrectAnswer(question[2]);
        MultipleChoice MCquestion = new MultipleChoice(question[1], question[0], MCanswer, choices);
        questions.add(MCquestion);
    }
    /**
     * creates a question object of type True False and add it to a list of Question object
     * @param question a string array that holds the question info
     * @param questions a List of question where the question object saved
     */
    public void createTFquestion(String[] question, List<Question> questions) {
        String[] choices = new String[]{"True", "False"};
        CorrectAnswer TFanswer = new CorrectAnswer(question[2]);
        TrueFalse TFquestion = new TrueFalse(question[1], question[0], TFanswer, choices);
        questions.add(TFquestion);
    }
    /**
     * creates a question object of type Short Answer and add it to a list of Question object 
     * @param question a string array that holds the question info
     * @param questions a list of question where the question object saved
     */
    public void createSAquestion(String[] question, List<Question> questions) {
        CorrectAnswer SAanswer = new CorrectAnswer(question[2]);
        ShortAnswer SAquestion = new ShortAnswer(question[1], question[0], SAanswer);
        questions.add(SAquestion);
    }
}
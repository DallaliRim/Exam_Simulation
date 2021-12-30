package Question;
import Answer.*;
/**
 * creates a question type
*/
public  abstract class Question {
    String category;
    String question_id;
    String questionText;
    CorrectAnswer correctAnswer;
    UserAnswer userAnswer;
    
    public Question(String questionText, String category, CorrectAnswer correctAnswer) {
        this.questionText = questionText;
        this.category = category;
        this.correctAnswer = correctAnswer;
    }
    public abstract int getTOTALPOINTS();
       
    public void setUserAnswer(UserAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Answer getUserAnswer() {
        return this.userAnswer;
    }
    public void setQuestionID(String question_id) {
        this.question_id = question_id;
    }

    public Answer getCorrectAnswer() {
        return this.correctAnswer;
    }
    public String getQuestionText(){
        return this.questionText;
    }
    
    @Override
    public boolean equals(Object question){
        if(!( question instanceof  Question)){
            return false;
        }
        Question  question1 = (Question)  question;
        return this.category.equals(question1.category) && this.questionText.equals(question1.questionText) && this.correctAnswer.equals(question1.correctAnswer);
    }
}
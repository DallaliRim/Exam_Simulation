package Question;
import Answer.*;
/**
 * creates a question of type true false
 */
public class TrueFalse extends Question {
    private final int TOTALPOINTS = 2; //the maximum grade of true false question
    private int pointsWorth;
    private String[] choices;

    public TrueFalse(String question, String category, CorrectAnswer answer, String[] choices) {
        super(question, category, answer);
        this.choices = new String[]{"True","False"};
    }

    public int getTOTALPOINTS(){
        return this.TOTALPOINTS;
    }
    
    public int getPointsWorth() {
        return this.pointsWorth;
    }
    public void setPointsWorth(int pointsWorth) {
        this.pointsWorth = pointsWorth;
    }

    @Override
    public String toString(){
        String strbuilder = "\n" + this.question_id + ". " +this.category + ". " + this.questionText;
        for (String string : choices) {
            strbuilder += "\n     - " + string;
        }
        return strbuilder;
    }
    @Override
    public boolean equals(Object tfQuestion){
        if(!(tfQuestion instanceof TrueFalse)){
            return false;
        }

        TrueFalse question1 = (TrueFalse) tfQuestion;
        this.choices.equals(question1.choices);
        boolean choicesEqual = false;
        for(int i=0;i<this.choices.length;i++){
            choicesEqual =this.choices[i].equals(question1.choices[i]);
        }
        return this.category.equals(question1.category) && this.questionText.equals(question1.questionText) && this.correctAnswer.equals(question1.correctAnswer) & choicesEqual;
    }

}

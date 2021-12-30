package Question;
/**
 * creates a question of type Multiple Choice
 */
import Answer.*;


public class MultipleChoice extends Question{
    private final int TOTALPOINTS = 3; //the maximum grade of multiple choice question
    private int pointsWorth;
    protected String[] choices;

    public MultipleChoice(String question, String category, CorrectAnswer answer, String[] choices) {
        super(question, category, answer);
        this.choices = choices;
    }
    
    public int getTOTALPOINTS() {
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
}

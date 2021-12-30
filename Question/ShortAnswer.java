package Question;
/**
 * created a question of type short answers
 */
import Answer.*;
public class ShortAnswer extends Question {
    private final int TOTALPOINTS = 5; //the maximum grade of short answer question
    private int pointsWorth;

    public ShortAnswer(String question, String category, CorrectAnswer answer) {
        super(question, category, answer);
    }

    public int getPointsWorth() {
        return this.pointsWorth;
    }
    public void setPointsWorth(int pointsWorth) {
        this.pointsWorth = pointsWorth;
    }
    public int getTOTALPOINTS() {
        return this.TOTALPOINTS;
    }

    @Override
    public String toString(){
        String strbuilder = "\n" + this.question_id + ". " +this.category + ". " + this.questionText + "\n" + "-";
        return strbuilder;
    }
}

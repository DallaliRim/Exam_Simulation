package Answer;
/**
 * This class Extends Answer Class and creates a UserAnswer type
 */

public class UserAnswer extends Answer {
    public UserAnswer(String answerText) {
        super(answerText);
    }

    public String toString(){
        return "\nUser Answer: " + this.getAnswer() + " points gained: " + this.getPointsGained();
    }
} 

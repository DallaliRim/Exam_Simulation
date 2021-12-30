package Answer;
/**
 * This class extends Answer class and creates an CorrectAnswer Type
 */
public class CorrectAnswer extends Answer {
    public CorrectAnswer(String answerText) {
        super(answerText);
    }

    public String toString(){
        return "\nCorrect Answer : " + this.answerText;
    } 
}

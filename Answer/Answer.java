package Answer;
/**
 * This class creates an Answer type
 */
public class Answer {
    protected String answerText;
    protected int pointsGained;
    
    public Answer(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswer() {
        return this.answerText;
    }
    public int getPointsGained() {
        return this.pointsGained;
    }
    public void setPointsGained(int points) {
        this.pointsGained = points;
    }

    @Override
    public boolean equals(Object answer){
        if(!(answer instanceof Answer)){
            return false;
        }
        Answer answer1 = (Answer) answer;
        return this.answerText.toLowerCase().equals(answer1.answerText.toLowerCase());
    }
}

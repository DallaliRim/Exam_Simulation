package UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import Answer.*;
import Question.ShortAnswer;

public class SATest{

    @Test
    public void SAEqualTest(){
        CorrectAnswer answer = new CorrectAnswer("blue");
        ShortAnswer question1 = new ShortAnswer("What color is the sky?","SA",answer);
        ShortAnswer question2 = new ShortAnswer("What color is the sky?","SA",answer);
        assertEquals(question2, question1);
    }

    @Test
    public void getUserAnswerTest() {
        UserAnswer uanswer = new UserAnswer("pink");
        CorrectAnswer canswer = new CorrectAnswer("blue");
        ShortAnswer question1 = new ShortAnswer("What color is the sky?","SA",canswer);
        question1.setUserAnswer(uanswer);
        assertEquals(uanswer, question1.getUserAnswer());
    }

    @Test
    public void getCorrectAnswerTest(){
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        ShortAnswer question1 = new ShortAnswer("What is Canada's capital?","SA",answer1);
        assertEquals(answer1, question1.getCorrectAnswer());
    }

    @Test
    public void getTOTALPOINTSTest(){
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        ShortAnswer question1 = new ShortAnswer("What is Canada's capital?","SA",answer1);
        assertEquals(5,question1.getTOTALPOINTS());
    }

    @Test
    public void getPointsWorthTest() {
        CorrectAnswer answer = new CorrectAnswer("blue");
        ShortAnswer question1 = new ShortAnswer("What is the sky?","SA",answer);
        question1.setPointsWorth(5);
        assertEquals(5, question1.getPointsWorth());
    }

    @Test
    public void getQuestionTextTest(){
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        ShortAnswer question1 = new ShortAnswer("What is Canada's capital?","SA",answer1);
        assertEquals("What is Canada's capital?", question1.getQuestionText());
    }

}
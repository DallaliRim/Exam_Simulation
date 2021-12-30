package UnitTests;

//import org.junit.jupiter.api.Assertions.*;
//need to add it to make the tests run
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import Answer.*;
import Question.TrueFalse;

public class TrueFalseTest {
    
    @Test
    public void TFEqualTest(){  
        String[] choices = {"True", "False"};
        CorrectAnswer answer = new CorrectAnswer("blue");
        CorrectAnswer answer1 = new CorrectAnswer("blue");
        TrueFalse question1 = new TrueFalse("What color is the sky?","TF",answer, choices);
        TrueFalse question2 = new TrueFalse("What color is the sky?","TF",answer1, choices);
        assertEquals(question2, question1);
    }

    @Test
    public void getUserAnswerTest() {
        String[] choices = {"True", "False"};
        UserAnswer uanswer = new UserAnswer("pink");
        CorrectAnswer canswer = new CorrectAnswer("blue");
        TrueFalse question1 = new TrueFalse("What color is the sky?","TF",canswer,choices);
        question1.setUserAnswer(uanswer);
        assertEquals(uanswer, question1.getUserAnswer());
    }

    @Test
    public void getCorrectAnswerTest(){
        String[] choices = {"True", "False"};
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        TrueFalse question1 = new TrueFalse("What is Canada's capital?","TF",answer1,choices);
        assertEquals(answer1, question1.getCorrectAnswer());
    }

    @Test
    public void getTOTALPOINTSTest(){
        String[] choices = {"True", "False"};
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        TrueFalse question1 = new TrueFalse("What is Canada's capital?","TF",answer1,choices);
        assertEquals(2,question1.getTOTALPOINTS());
    }

    @Test
    public void getPointsWorthTest() {
        String[] choices = {"True", "False"};
        CorrectAnswer answer = new CorrectAnswer("blue");
        TrueFalse question1 = new TrueFalse("What color is the sky?","TF",answer,choices);
        question1.setPointsWorth(5);
        assertEquals(5, question1.getPointsWorth());
    }

    @Test
    public void getQuestionTextTest(){
        String[] choices = {"True", "False"};
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        TrueFalse question1 = new TrueFalse("What is Canada's capital?","TF",answer1,choices);
        assertEquals("What is Canada's capital?", question1.getQuestionText());
    }

}

package UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import Answer.*;
import Question.MultipleChoice;

public class MultipleChoiceTest {

    @Test
    public void MCEqualTest(){
        String[] choices = {"green","pink","blue"};
        CorrectAnswer answer = new CorrectAnswer("blue");
        MultipleChoice question1 = new MultipleChoice("What color is the sky?","MC",answer,choices);
        MultipleChoice question2 = new MultipleChoice("What color is the sky?","MC",answer,choices);
        assertEquals(question2, question1);
    }

    @Test
    public void getUserAnswerTest() {
        String[] choices = {"green","pink","blue"};
        UserAnswer uanswer = new UserAnswer("pink");
        CorrectAnswer canswer = new CorrectAnswer("blue");
        MultipleChoice question1 = new MultipleChoice("What color is the sky?","MC",canswer,choices);
        question1.setUserAnswer(uanswer);
        assertEquals(uanswer, question1.getUserAnswer());
    }

    @Test
    public void getCorrectAnswerTest(){
        String[] choices = {"ottawa","montreal","toronto"};
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        MultipleChoice question1 = new MultipleChoice("What is Canada's capital?","MC",answer1,choices);
        assertEquals(answer1, question1.getCorrectAnswer());
    }

    @Test
    public void getTOTALPOINTSTest(){
        String[] choices = {"ottawa","montreal","toronto"};
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        MultipleChoice question1 = new MultipleChoice("What is Canada's capital?","MC",answer1,choices);
        assertEquals(3,question1.getTOTALPOINTS());
    }

    @Test
    public void getPointsWorthTest() {
        String[] choices = {"green","pink","blue"};
        CorrectAnswer answer = new CorrectAnswer("blue");
        MultipleChoice question1 = new MultipleChoice("What is the sky?","MC",answer,choices);
        question1.setPointsWorth(5);
        assertEquals(5, question1.getPointsWorth());
    }

    @Test
    public void getQuestionTextTest(){
        String[] choices = {"ottawa","montreal","toronto"};
        CorrectAnswer answer1 = new CorrectAnswer("ottawa");
        MultipleChoice question1 = new MultipleChoice("What is Canada's capital?","MC",answer1,choices);
        assertEquals("What is Canada's capital?", question1.getQuestionText());
    }


}

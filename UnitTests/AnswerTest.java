package UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import Answer.Answer;

public class AnswerTest {

    @Test
    public void testAnswersEquals(){
        Answer answer1 = new Answer("Blue Ocean");
        Answer answer2 = new Answer("Blue Ocean");
        assertEquals(answer1, answer2);
    }

    @Test
    public void testGetAnswer(){
        Answer answer1 = new Answer("Blue Ocean");
        assertEquals("Blue Ocean", answer1.getAnswer());

    }   
    
    @Test
    public void testGetPointsGained(){
        Answer answer = new Answer("javaproject");
        answer.setPointsGained(3);
        assertEquals(3, answer.getPointsGained());
    }

}

package UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import Answer.CorrectAnswer;

public class CorrectAnswerTest {

    @Test
    public void testEquals(){
        CorrectAnswer answer1 = new CorrectAnswer("red sea");
        CorrectAnswer answer2 = new CorrectAnswer("red sea");
        assertEquals(answer1, answer2);
    }

    @Test
    public void testGetAnswer(){
        CorrectAnswer answer1 = new CorrectAnswer("Blues");
        assertEquals("Blues", answer1.getAnswer());

    }   

    @Test
    public void testGetPointsGained(){
        CorrectAnswer answer = new CorrectAnswer("project");
        answer.setPointsGained(3);
        assertEquals(3, answer.getPointsGained());
    }

    
}

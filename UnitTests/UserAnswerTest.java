package UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import Answer.UserAnswer;

public class UserAnswerTest {

    @Test
    public void testEquals(){
        UserAnswer answer1 = new UserAnswer("Blue Ocean");
        UserAnswer answer2 = new UserAnswer("Blue Ocean");
        assertEquals(answer1, answer2);
    }

    @Test
    public void testGetAnswer(){
        UserAnswer answer1 = new UserAnswer("Blue Ocean");
        assertEquals("Blue Ocean", answer1.getAnswer());
    }   

    @Test
    public void testGetPointsGained(){
        UserAnswer answer = new UserAnswer("javaproject");
        answer.setPointsGained(3);
        assertEquals(3, answer.getPointsGained());
    }
    
}

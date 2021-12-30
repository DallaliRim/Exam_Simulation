package UnitTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import Result.*;

public class ResultTest {

    @Test
    public void getTestGradeLetter(){
        LetterGrade r1 = new LetterGrade(90,100);
        assertEquals("A", r1.getGrade());
    }

    @Test
    public void getTestGradeTestPF() {
        PassFail r1 = new PassFail(38, 70);
        assertEquals("YOU FAILED THE EXAM", r1.getGrade());
    }

    @Test
    public void getTestGradeTestP() {
        PercentageGrade r1 = new PercentageGrade(90,100);
        assertEquals("90%", r1.getGrade());
    }

}

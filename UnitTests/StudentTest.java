package UnitTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import main.Student;

public class StudentTest {
    @Test
    public void getCountTest(){
        Student dany = new Student("1", "testfile.txt", 4);
        dany.upgradeExamCount();
        assertEquals(0, dany.getExamId());
    }

    @Test
    public void getStudentIdTest(){
        Student dany = new Student("1", "testfile.txt", 4);
        assertEquals("1", dany.getStudentId());
    }
}

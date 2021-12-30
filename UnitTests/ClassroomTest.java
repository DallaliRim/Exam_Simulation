package UnitTests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import main.Classroom;
import main.Student;

public class ClassroomTest {

    @Test
    public void searchStudentTest(){
        Student student1 = new Student("1", "Dany", 3);
        Student student2 = new Student("2", "Rim", 4);
        Classroom classroom = new Classroom();
        classroom.addStudent(student1);
        classroom.addStudent(student2);
        assertEquals(student1, classroom.searchStudent("1"));
    }
    
}

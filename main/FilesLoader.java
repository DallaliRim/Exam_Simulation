package main;
/**
 * an inteface to load correct answers and user answers and questions from a file
 */
import java.io.IOException;
import java.util.List;
import Answer.*;
import Question.*;;

public interface FilesLoader{
   List<Question> loadQuestionFromFile(String fileName) throws IOException;
   List<Answer> loadUserAnswersFromFile(String fileName) throws IOException;
}
package UnitTests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import Answer.Answer;
import Answer.CorrectAnswer;
import Answer.UserAnswer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Question.*;
import main.*;

public class ExamLoaderTest {
    @Test
    public void loadUserAnswersTest() throws IOException{
        List<Answer> answers = new ArrayList<Answer>();
        Answer answer1 = new UserAnswer("blue");
        Answer answer2 = new UserAnswer("GREEN");
        Answer answer3 = new UserAnswer("false");
        Answer answer4 = new UserAnswer("BLABLA");
        Answer answer5 = new UserAnswer("percentage");
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);
        List<String> answersString = new ArrayList<String>();
        answersString.add("-blue");
        answersString.add("-GREEN");
        answersString.add("-false");
        answersString.add("-BLABLA");
        answersString.add("-percentage");
        ExamLoader exam = new ExamLoader();
        assertEquals(answers, exam.loadUserAnswers(answersString));
    }

    @Test

    public void loadCorrectAnswersTest() throws IOException{
        List<Answer> answers = new ArrayList<Answer>();
        Answer answer1 = new CorrectAnswer("blue");
        Answer answer2 = new CorrectAnswer("green");
        Answer answer3 = new CorrectAnswer("true");
        Answer answer4 = new CorrectAnswer("Ottawa");
        Answer answer5 = new CorrectAnswer("percentage");
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);
        answers.add(answer5);
        List<String> answersString = new ArrayList<String>();
        answersString.add("MC;What color is the sky?;blue");
        answersString.add("SA;What color is the grass?;green");
        answersString.add("TF;The door is 2m tall?;true");
        answersString.add("SA;What is Canada's capital?;Ottawa");
        answersString.add("MC;Choose a format;percentage");
        ExamLoader exam = new ExamLoader();
        assertEquals(answers, exam.loadCorrectAnswers(answersString));
    }
    
    @Test
    public void loadQuestionTest() throws IOException{
        CorrectAnswer answer1 = new CorrectAnswer("blue");
        CorrectAnswer answer2 = new CorrectAnswer("green");
        CorrectAnswer answer3 = new CorrectAnswer("true");
        List<String> questionsStrings= new ArrayList<String>();
        String string1 = "MC;What color is the sky?;blue;green;pink;blue";
        String string2 = "SA;What color is the grass?;green";
        String string3 = "TF;The door is 2m tall?;true";
        questionsStrings.add(string1);
        questionsStrings.add(string2);
        questionsStrings.add(string3);
        String[] choices1 = {"green","pink","blue"};
        String[] choices3 = {"True","False"};
        List<Question> questions = new ArrayList<Question>();
        MultipleChoice question1 = new MultipleChoice("What color is the sky?","MC",answer1,choices1);
        questions.add(question1);
        ShortAnswer question2 = new ShortAnswer("What color is the grass?", "SA", answer2);
        questions.add(question2);
        TrueFalse question3 = new TrueFalse("The door is 2m tall?","TF", answer3, choices3);
        questions.add(question3);
        ExamLoader exam = new ExamLoader();
        assertEquals(questions.get(2), exam.loadQuestions(questionsStrings).get(2));
    }
    
}

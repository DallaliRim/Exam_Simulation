//Rim Dallali 1937259

package JavaFX;
import main.Classroom; 
import main.Student;

import java.io.IOException;

import javafx.application.*;
import javafx.stage.*; 
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class ExamApplication extends Application {
    Classroom classroom = new Classroom();

    @Override
    public void start(Stage stage) throws NumberFormatException, Exception { //separating into helper methods
        // container and layout
        Group root = new Group();
        HBox hMainContainer = new HBox();

        //creating elements
        VBox vExamSection = new VBox();
        VBox vStudentSection = new VBox();
        VBox vStudentId = new VBox();
        VBox vGenerateOption = new VBox();
        VBox vSubmitOption = new VBox();

        HBox hStudentInfo = new HBox();
        HBox hActions = new HBox();
    
        Label lbStudentId = new Label("Student ID");
        Label lbClassroomList = new Label("Student List");
        Label lbQuestionAmount = new Label ("number of questions to generate");
        Label lbExamFile = new Label ("Sample exam File name");
        Label lbUserFile = new Label ("File name containing your answers");

        Button btnSubmitStudentInfo = new Button("Create student");
        Button btnGenerateExam = new Button("Generate Exam");
        Button btnDisplayReview = new Button("Display Exam Review");
        Button btnSubmitAnswers = new Button("Submit Answers");

        TextArea taOutputArea = new TextArea();
        TextArea taStudentList = new TextArea();
        TextArea taStudentMessage = new TextArea("Please enter your student informations");
        TextField tfStudentId = new TextField("sampleID");
        TextField tfSampleExamFile = new TextField("testfile.txt");
        TextField tfUserAnswerFile = new TextField("Exam_Answers_S00_E0.txt");
        TextField tfNumberOfQuestions = new TextField("4");

        //appending elements 
        root.getChildren().addAll(hMainContainer);
        hMainContainer.getChildren().addAll(vExamSection, vStudentSection);
        vStudentSection.getChildren().addAll(hStudentInfo, btnSubmitStudentInfo, lbClassroomList, taStudentList);
        vExamSection.getChildren().addAll(taStudentMessage, hActions, taOutputArea, btnDisplayReview);
        hStudentInfo.getChildren().addAll(vStudentId); 
        vStudentId.getChildren().addAll(lbStudentId, tfStudentId);

        hActions.getChildren().addAll(vGenerateOption, vSubmitOption);
        vGenerateOption.getChildren().addAll(lbExamFile, tfSampleExamFile, lbQuestionAmount, tfNumberOfQuestions, btnGenerateExam);
        vSubmitOption.getChildren().addAll(lbUserFile, tfUserAnswerFile, btnSubmitAnswers);

        //setting styles
        setStyles(vStudentSection, btnSubmitStudentInfo, taStudentList, taStudentMessage, tfNumberOfQuestions);

        //setting actions        
        generateStudentExam(btnGenerateExam, tfStudentId, tfNumberOfQuestions, tfSampleExamFile, taOutputArea, taStudentMessage);
        submitStudentInfo(btnSubmitStudentInfo, tfStudentId, tfSampleExamFile, tfNumberOfQuestions, taStudentMessage, taStudentList);
        submitStudentAnswers(btnSubmitAnswers, tfStudentId, tfUserAnswerFile, tfSampleExamFile, taOutputArea);
        displayExamReview(btnDisplayReview, tfUserAnswerFile, tfStudentId, taOutputArea);

        // scene is associated with container, dimensions
        Scene scene = new Scene(root, 800, 570);
        scene.setFill(Color.LIGHTSTEELBLUE);

        // associate scene to stage and show
        stage.setTitle("Exam Simulation");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * sets the styles for the GUI components
     * @param vStudentSection
     * @param btnSubmitStudentInfo
     * @param taStudentList
     * @param taStudentMessage
     * @param tfNumberOfQuestions
     */
    public void setStyles(VBox vStudentSection, Button btnSubmitStudentInfo, TextArea taStudentList, TextArea taStudentMessage, TextField tfNumberOfQuestions) {
        vStudentSection.setPrefWidth(300);
        btnSubmitStudentInfo.setPrefWidth(150);
        taStudentList.setPrefHeight(450);
        taStudentMessage.setPrefHeight(90);
        tfNumberOfQuestions.setPrefWidth(50); 
    }

    /**
     * sets the action event for the display student review button
     * @param btnDisplayReview
     * @param tfUserAnswerFile
     * @param tfStudentId
     * @param taOutputArea
     */
    public void displayExamReview(Button btnDisplayReview, TextField tfUserAnswerFile, TextField tfStudentId, TextArea taOutputArea) {
        btnDisplayReview.setOnAction(evt -> {
            try {
                int exam_id = Integer.parseInt(tfUserAnswerFile.getText().charAt(tfUserAnswerFile.getText().length()-5)+"");
                classroom.searchStudent(tfStudentId.getText()).searchExam(exam_id).displayExamReview(tfStudentId.getText());
                taOutputArea.appendText("\nReview for student of ID : " + tfStudentId.getText() + " has been created as Exam_Review_S" + tfStudentId.getText() + "_E" + classroom.searchStudent(tfStudentId.getText()).searchExam(exam_id).getExamId()+ ".txt");
            } catch (NullPointerException e) {
                taOutputArea.setText("Cannot generate an exam review for the student, make sure you have submitted your answers");  
            }
        }); 
    }

    /**
     * sets the action event for the generate exam button
     * @param btnGenerateExam
     * @param tfStudentId
     * @param tfNumberOfQuestions
     * @param tfSampleExamFile
     * @param taOutputArea
     * @param taStudentMessage
     */
    public void generateStudentExam(Button btnGenerateExam, TextField tfStudentId, TextField tfNumberOfQuestions, TextField tfSampleExamFile, TextArea taOutputArea, TextArea taStudentMessage) {
        btnGenerateExam.setOnAction(evt -> {
            try {
                classroom.generateStudentExam(tfStudentId.getText(), Integer.parseInt(tfNumberOfQuestions.getText()), tfSampleExamFile.getText());
                taOutputArea.appendText("\nExam for student of ID : " + tfStudentId.getText() + "has been created as Exam_S" + tfStudentId.getText() + "_E" + classroom.searchStudent(tfStudentId.getText()).getExamId() + ".txt");
            } catch (IOException | IllegalArgumentException e) { 
                taOutputArea.setText("\nInvalid Sample File");
            } catch (NullPointerException e) {
                taStudentMessage.setText("Cannot generate the exam, Make sure to create the student before generating an exam");
            }
        });
    }

    /**
     * sets the action event for the submit student answers button
     * @param btnSubmitAnswers
     * @param tfStudentId
     * @param tfUserAnswerFile
     * @param tfSampleExamFile
     * @param taOutputArea
     */
    public void submitStudentAnswers(Button btnSubmitAnswers, TextField tfStudentId, TextField tfUserAnswerFile, TextField tfSampleExamFile, TextArea taOutputArea) {
        btnSubmitAnswers.setOnAction(evt -> {
            Student student = classroom.searchStudent(tfStudentId.getText());
            try {
                int exam_id = Integer.parseInt(tfUserAnswerFile.getText().charAt(tfUserAnswerFile.getText().length()-5)+"");//ghetto solution
                student.searchExam(exam_id).gradeExam(tfSampleExamFile.getText(), tfUserAnswerFile.getText()); 
                taOutputArea.setText("Grade for " + tfStudentId.getText() + " exam : E" + exam_id + " is " + student.searchExam(exam_id).getResult().getGrade());  
            } catch (NullPointerException e) {
                taOutputArea.setText("Generate an Exam in order to have access to this function"); 
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException | NumberFormatException | IOException e) {
                taOutputArea.setText("Answer format not respected, make sure you entered the correct file");
            }
        });
    }

    /**
     * sets the action event for the submit student info button
     * @param btnSubmitStudentInfo
     * @param tfStudentId
     * @param tfSampleExamFile
     * @param tfNumberOfQuestions
     * @param taStudentMessage
     * @param taStudentList
     */
    public void submitStudentInfo(Button btnSubmitStudentInfo, TextField tfStudentId, TextField tfSampleExamFile, TextField tfNumberOfQuestions, TextArea taStudentMessage, TextArea taStudentList) {
        btnSubmitStudentInfo.setOnAction(evt -> {
            if (tfStudentId.getText().equals("sampleID")) {
                taStudentMessage.setText("Enter your ID and name in their respective fields.");
            } else {
                if (classroom.searchStudent(tfStudentId.getText()) == null) {
                    classroom.addStudent(tfStudentId.getText(), tfSampleExamFile.getText(), Integer.parseInt(tfNumberOfQuestions.getText()));
                    taStudentList.appendText("\nStudent ID : " + tfStudentId.getText());
                } else {
                    taStudentMessage.appendText("\nStudent Already Exists, please generate an exam");
                }
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
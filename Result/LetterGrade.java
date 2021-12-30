package Result;
/**
 * creates a result of type letterGrade
 */
public class LetterGrade implements Result{
    protected double testGrade;
    private String letterGrade;
 
    public LetterGrade(int testGrade, int examTotal) {
        this.testGrade = ((double)testGrade/(double)examTotal)*100;
        setGrade();
    }
    /**
     * set a letter grade based of the grade range
     */
    public void setGrade() {
        if (this.testGrade <= 100 && this.testGrade >=90) {
            this.letterGrade = "A";
        }
        else if (this.testGrade <= 89 && this.testGrade >=80) {
            this.letterGrade = "B";
        }
        else if (this.testGrade <= 79 && this.testGrade >=70) {
            this.letterGrade = "C";
        }
        else if (this.testGrade <= 69 && this.testGrade >= 60) {
            this.letterGrade = "D";
        }
        else {
            this.letterGrade = "F";
        }
    }

    public String getGrade() {
        return this.letterGrade;
    }
}

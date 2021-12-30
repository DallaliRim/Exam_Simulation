package Result;
/**
 * creates a result of type PassFail
 */
public class PassFail implements Result{
    private String passFailGrade;
    protected double testGrade;
    
    public PassFail(int testGrade, int examTotal) {
        this.testGrade = ((double)testGrade/(double)examTotal)*100;
        setGrade();
    }
    /**
     * set the grade to fail if grade less that 60 and pass of grade more than 60
     */
    public void setGrade() {
        if(this.testGrade >= 60) {
            this.passFailGrade = "PASS";
        }
        else {
            this.passFailGrade = "FAIL";
        }
    }

    public String getGrade() { 
        return "YOU " + this.passFailGrade + "ED THE EXAM";
    }
}

package Result;
/**
 * creates a result of type percentage
 */
public class PercentageGrade implements Result {
    private double percentageGrade;
   
    public PercentageGrade(int testGrade, int examTotal) { 
        this.percentageGrade = ((double)testGrade/(double)examTotal)*100;
    }
    
    public String getGrade() {
        return (int)this.percentageGrade + "%";
    }
}
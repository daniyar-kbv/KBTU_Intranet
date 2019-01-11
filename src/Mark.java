import java.io.Serializable;

public class Mark implements Serializable{
	
	private double firstAtt;
	private double secondAtt;
	private double finalExam;
	private MarkSign markSign;
	
	Mark()
	{
		firstAtt = 0;
		secondAtt = 0;
		finalExam = 0;
	}
	
	public double getFirstAtt()
	{
		return firstAtt;
	}
	public double getSecondAtt()
	{
		return secondAtt;
	}
	public double getFinalExam()
	{
		return finalExam;
	}
	public double getTotalScore()
	{
		return (firstAtt + secondAtt + finalExam);
	}
	public MarkSign getMarkSign() {
		identifyMark(getTotalScore());
		return markSign;
	}
	public void setFirstAtt(double firstAtt) {
		this.firstAtt = firstAtt;
	}
	public void setSecondAtt(double secondAtt) {
		this.secondAtt = secondAtt;
	}
	public void setFinalExam(double finalExam) {
		this.finalExam = finalExam;
	}
	public void identifyMark(double totalScore) {
		if(totalScore < 50.0) {
			markSign = MarkSign.F;
		}
		else if(totalScore >= 50.0 && totalScore < 60.0) {
			markSign = MarkSign.D;
		}
		else if(totalScore >= 60.0 && totalScore < 75.0) {
			markSign = MarkSign.C;
		}
		else if(totalScore >= 75.0 && totalScore < 90.0) {
			markSign = MarkSign.B;
		}
		else if(totalScore >= 90) {
			markSign = MarkSign.A;
		}
	}
	public double getNumber() {
		double totalScore = getTotalScore();
		
		if(totalScore >= 50.0 && totalScore < 55.0) {
			return 1.0;
		}
		else if(totalScore >= 55.0 && totalScore < 60.0) {
			return 1.33;
		}
		else if(totalScore >= 60.0 && totalScore < 65.0) {
			return 1.67;
		}
		else if(totalScore >= 65.0 && totalScore < 70.0) {
			return 2.0;
		}
		else if(totalScore >= 70.0 && totalScore < 75.0) {
			return 2.33;
		}
		else if(totalScore >= 75.0 && totalScore < 80.0) {
			return 2.67;
		}
		else if(totalScore >= 80.0 && totalScore < 85.0) {
			return 3.0;
		}
		else if(totalScore >= 85.0 && totalScore < 90.0) {
			return 3.33;
		}
		else if(totalScore >= 90.0 && totalScore < 95.0) {
			return 3.67;
		}
		else if(totalScore >= 95.0) {
			return 4.0;
		}
		return 0.0;
	}
	public String toString() {
		return "Score: " + getTotalScore() + " sign: " + getMarkSign();
	}
}

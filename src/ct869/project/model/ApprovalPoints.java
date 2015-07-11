package ct869.project.model;

public class ApprovalPoints {
	private int approvalPoints;
	private int positiveFeedbackCount;
	private int negativeFeedbackCount;
	private int neutralFeedbackCount;
	private int cumulativeFeedbackCount;
	
	public ApprovalPoints() {
		positiveFeedbackCount = 0;
		negativeFeedbackCount = 0;
		neutralFeedbackCount = 0;
		cumulativeFeedbackCount = 0;
	}
	public void setPositiveFeedbackCount(int positiveAP) {
		positiveFeedbackCount = positiveAP;
	}
	public int getPositiveFeedbackCount() {
		return positiveFeedbackCount;
	}
	public void setNeutralFeedbackCount(int neutralAP) {
		neutralFeedbackCount = neutralAP;
	}
	public int getNeutralFeedbackCount() {
		return neutralFeedbackCount;
	}
	public void setNegativeFeedbackCount(int negativeAP) {
		negativeFeedbackCount = negativeAP;
	}
	public int getNegativeFeedbackCount() {
		return negativeFeedbackCount;
	}
	public int getApprovalPoints() {
		return approvalPoints;
	}
	public void setApprovalPoints(int points) {
		approvalPoints = points;
	}
	public int incrementApprovalPoints() {
		approvalPoints += 1;
		positiveFeedbackCount++;
		return approvalPoints;
	}
	public int decrementApprovalPoints() {
		approvalPoints -= 1;
		negativeFeedbackCount++;
		return approvalPoints;
	}
	public int giveNeutralFeedback() {
		approvalPoints += 0;
		neutralFeedbackCount++;
		return approvalPoints;
	}
	public int getCumulativeFeedbackCount() {
		cumulativeFeedbackCount = positiveFeedbackCount + 
				negativeFeedbackCount + neutralFeedbackCount;
		return cumulativeFeedbackCount;
	}
}

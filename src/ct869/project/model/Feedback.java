package ct869.project.model;

import java.util.Date;
import java.util.List;

public class Feedback {
	
	private Date date;
	private String author;
	private String feedbackValue;
	private String feedbackComment;
	private List<Feedback> feedbacks;
	
	public void setDate(Date feedbackDate) {
		date = feedbackDate;
	}
	public Date getDate() {
		return date;
	}
	public void setAuthor(String username) {
		author = username;
	}
	public String getAuthor() {
		return author;
	}
	public void setFeedbackValue(String feedback) {
		feedbackValue = feedback;
	}
	public String getFeedbackValue() {
		return feedbackValue;
	}
	public void setFeedbackComment(String comment) {
		feedbackComment = comment;
	}
	public String getFeedbackComment() {
		return feedbackComment;
	}
	public void setFeedbacksList(List<Feedback> feedback) {
		feedbacks = feedback;
	}
	public List<Feedback> getFeedbackList() {
		return feedbacks;
	}
}

package ct869.project.model;

import java.util.List;

public class Listing {
	
	private int listingID;
	private String listingDescription;
	private String listingKeywords;
	private String listingState;
	private String offererUsername;
	private String bidderUsername;
	private int feedbackQuantity;
	private String startTime;
	private List<Listing> listings;
	
	public void setListingID(int aListingID) {
		listingID = aListingID;
	}
	public int getListingID() {
		return listingID;
	}
	public void setListingDescription(String listingDesc) {
		listingDescription = listingDesc;
	}
	public String getListingDescription() {
		return listingDescription;
	}
	public void setListingKeywords(String listingKeyWords) {
		listingKeywords = listingKeyWords;
	}
	public String getListingKeywords() {
		return listingKeywords;
	}
	public void setListingState(String aListingState) {
		listingState = aListingState;	
	}
	public String getListingState() {
		return listingState;
	}
	public void setOffererUsername(String offererUname) {
		offererUsername = offererUname;
	}
	public String getOffererUsername() {
		return offererUsername;
	}
	public void setBidderUsername(String bidderUname) {
		bidderUsername = bidderUname;
	}
	public String getBidderUsername() {
		return bidderUsername;
	}
	public void setFeedbackQuantity(int feedbackQuant) {
		feedbackQuantity = feedbackQuant;
	}
	public int getFeedbackQuantity() {
		return feedbackQuantity;
	}
	public void setListingsList(List<Listing> listingsList) {
		listings = listingsList;
	}
	public List<Listing> getListingsList() {
		return listings;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}

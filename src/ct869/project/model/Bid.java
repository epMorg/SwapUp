package ct869.project.model;

public class Bid {

	private int bidID;
	private String bidText;
	private String userName;
	private String dateOfBid;
	public int associatedListingID;
	
	
	public int getBidID() {
		return bidID;
	}
	public void setBidID(int bidID) {
		this.bidID = bidID;
	}
	public String getBidText() {
		return bidText;
	}
	public void setBidText(String bidText) {
		this.bidText = bidText;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDateOfBid() {
		return dateOfBid;
	}
	public void setDateOfBid(String dateOfBid) {
		this.dateOfBid = dateOfBid;
	}
	public int getAssociatedListingID() {
		return associatedListingID;
	}
	public void setAssociatedListingID(int listingID) {
		this.associatedListingID = listingID;
	}
	
	
	
}

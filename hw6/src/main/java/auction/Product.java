package auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Product implements IProduct {
	private int id;
	private String title;
	private String thumb;
	private String description;
	private int quantity; // How many items the seller has
	private LocalDateTime auctionEndTime;
	private int watchers;
	private BigDecimal minimalPrice;     // Don't sell unless the bid is more than min price
	private BigDecimal reservedPrice;   // If a bidder offers reserved price, the auction is closed

	public Product(int id, String title, String thumb, String description, int quantity, LocalDateTime auctionEndTime, int watchers, BigDecimal minimalPrice, BigDecimal reservedPrice) {
		this.id = id;
		this.title = title;
		this.thumb = thumb;
		this.description = description;
		this.quantity = quantity;
		this.auctionEndTime = auctionEndTime;
		this.watchers = watchers;
		this.minimalPrice = minimalPrice;
		this.reservedPrice = reservedPrice;
	}

	@Override
	public String toString() {
		return "Product: \"" + title + "\"";
	}

	/**
	 * Obtain the name of the product with a minimum and maximum price
	 */
	@Override
	public String getMinMax(){
		return "Product: \"" + getTitle() + "\" (" +
			"minimalPrice: \"" + String.format("%.2f", getMinimalPrice()) +
			"\"; reservedPrice: \"" + String.format("%.2f", getReservedPrice()) + "\")";
	}

	@Override
	public void setAuctionIsOver(IUser user, IBid bid){
		this.auctionEndTime = LocalDateTime.now();
		bid.setWinning(true);
		System.out.println("Auction is over for this " + getMinMax() + ". Won " + user + " with amount: " + String.format("%.2f", bid.getAmount()));
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public LocalDateTime getAuctionEndTime() {
		return auctionEndTime;
	}

	@Override
	public BigDecimal getMinimalPrice() {
		return minimalPrice;
	}

	@Override
	public BigDecimal getReservedPrice() {
		return reservedPrice;
	}

	@Override
	public String getTitle() {
		return title;
	}

}

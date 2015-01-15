package auction;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Bid implements IBid {
	private int id;
	private IProduct product;
	private BigDecimal amount;
	private int desiredQuantity; // How many items the user wants
	private IUser user;
	private LocalDateTime bidTime;
	private boolean Winning;

	public Bid(int id, IProduct product, BigDecimal amount, int desiredQuantity, IUser user, LocalDateTime bidTime, boolean Winning) {
		this.id = id;
		this.product = product;
		this.amount = amount;
		this.desiredQuantity = desiredQuantity;
		this.user = user;
		this.bidTime = bidTime;
		this.Winning = Winning;
	}

	@Override
	public void addBid(List<IBid> bids, List<IUser> users){
		if (getAmount().compareTo(getProduct().getReservedPrice()) >= 0){ // bid.amount >= product.reservedPrice
			addToList("You won the auction with amount: "+ getAmountStr() +" for the " + getProduct().getMinMax(), bids, users);
			product.setAuctionIsOver(user, this); // Auction is over for this product
		} else if (amount.compareTo(product.getMinimalPrice()) < 0){ // bid.amount < product.minimalPrice
			System.out.println(""); // Beauty
			user.sendEmail("You did a very low amount. Your bid "+
				getAmountStr() +" for the "+ product.getMinMax() +" is not accepted");
		} else {
			addToList("Users Bid "+ user +" adopted amount: "+ getAmountStr() +" for " + product.getMinMax(), bids, users);
		}
	}

	@Override
	public String getAmountStr() {
		return String.format("%.2f", amount);
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setWinning(boolean winning) {
		Winning = winning;
	}

	@Override
	public IProduct getProduct() {
		return product;
	}

	@Override
	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public boolean isWinning() {
		return Winning;
	}

	@Override
	public String toString() {
		String winning = (isWinning())?"Winning! ":"";
		return  winning + user + "; amount: " + String.format("%.2f", amount) + "; " + product;
	}

	private void addToList(String mes, List<IBid> bids, List<IUser> users){
		bids.add(this); // Add bid
		System.out.println("\r\nAdd Bid: " + this);
		user.sendEmail(mes);
		user.sendOverbidNotifications(this, bids, users);
	}

}

package auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class User implements IUser {

    private int id;
	private String name;
	private String email;
    private boolean overbidNotifications;

    public User(int id, String name, String email, boolean overbidNotifications) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.overbidNotifications = overbidNotifications;
    }

	@Override
	public void addBids(List<IProduct> products, List<IBid> bids, List<IUser> users) {
        // Filter the products for which the auction was ended
        List<IProduct> productList = products.stream()
            .filter(product -> product.getAuctionEndTime().isAfter(LocalDateTime.now()))
            .collect(Collectors.toList());

        // Bet random value
        productList.stream()
            .forEach(product -> {
                BigDecimal amount = getRandomBigDecimal(100.00, 900.00);
                int desiredQuantity = new Random().nextInt(10);
                IBid newBid = new Bid(getNextBidId(bids), product, amount, desiredQuantity, this,
                    LocalDateTime.now(), false);
                newBid.addBid(bids, users);
            });

    }

    @Override
    public String sendEmail(String mes) {
        String mesStr = "Send email to " + name + ": " + mes;
        System.out.println(mesStr);
        return mesStr;
    }

	/**
	 * If necessary, sends emails to users who have set up overbidNotifications
	 */
	@Override
	public void sendOverbidNotifications(IBid newBid, List<IBid> bids, List<IUser> users) {
		users.forEach(user -> bids.stream()
			.filter(bid -> bid.getUser().isOverbidNotifications() && newBid.getProduct().getId() == bid.getProduct().getId()
				&& user.getId() == bid.getUser().getId() && !bid.isWinning()
				&& newBid.getAmount().compareTo(bid.getAmount()) > 0)
			.max(Comparator.comparing(IBid::getAmount))
			.ifPresent(bid -> user.sendEmail(
				"Your Bid amount: " + bid.getAmountStr() + " by " + newBid.getProduct()
					+ " is blocked by " + newBid
			)));
	}

	@Override
    public String toString() {
        return "User: \"" + name + "\"";
    }

    private BigDecimal getRandomBigDecimal(Double min, Double max) {
        DoubleStream doubleStream = new Random().doubles(min, max);
        double aDouble = doubleStream.findAny().getAsDouble();
        return new BigDecimal(aDouble);
    }

	@Override
	public int getId() {
		return id;
	}

	@Override
	public boolean isOverbidNotifications() {
		return overbidNotifications;
	}


	private int getNextBidId(List<IBid> bids){
		int bidsSize = bids.size();
		if (bidsSize > 0) {
			return bids.get(bidsSize - 1).getId();
		} else {
			return 1;
		}

	}

	@Override
	public String getName() {
		return name;
	}
}

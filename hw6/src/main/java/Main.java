import auction.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static final int DURATION_AUCTION_IN_SEC = 5; // Auction duration in seconds

	public static final List<IUser> users = new ArrayList<IUser>() {{ // Load users
		add(new User(1, "Ashot", "ashot@gmail.com", true));
		add(new User(2, "Dato", "dato@gmail.com", true));
		add(new User(3, "Gedevan", "gedevan@gmail.com", true));
		add(new User(4, "Samvel", "samvel@gmail.com", false));
		add(new User(5, "Karen", "karen@gmail.com", true));
		add(new User(6, "Givi", "givi@gmail.com", false));
	}};

	public static final List<IProduct> products = new ArrayList<IProduct>() {{ // Load products
		LocalDateTime auctionEndDateTime = LocalDateTime.now().plusWeeks(1); // Give a week to buy these products
		add(new Product(1, "iPhone 5s", "images/01.jpg", "iPhone 5s description", 56, auctionEndDateTime, 14, new BigDecimal(650.00), new BigDecimal(850.00)));
		add(new Product(2, "iPhone 4s", "images/02.jpg", "iPhone 4s description", 34, auctionEndDateTime, 4, new BigDecimal(250.00), new BigDecimal(450)));
		add(new Product(3, "iPhone 5c", "images/03.jpg", "iPhone 5c description", 23, auctionEndDateTime, 7, new BigDecimal(550.00), new BigDecimal(650.00)));
		add(new Product(4, "iPad mini", "images/04.jpg", "iPad mini description", 12, auctionEndDateTime, 20, new BigDecimal(400.00), new BigDecimal(700.00)));
		add(new Product(5, "iPad Air", "images/05.jpg", "iPad Air description", 3, auctionEndDateTime, 30, new BigDecimal(500.00), new BigDecimal(800.00)));
	}};

	public static List<IBid> bids = new ArrayList<>();

	public static void main(String[] args) {
		LocalDateTime endAuction = LocalDateTime.now().plusSeconds(DURATION_AUCTION_IN_SEC); // Auction end time

		Runnable runnable = () -> {
			users.forEach(user -> user.addBids(products, bids, users)); // Each user add Bid on each product

			List<IBid> bidList = getWinnerBidList(bids); // Get winning bids

			// If winning bids equal to the number of products or was the end of the auction
			if (bidList.size() == products.size() || LocalDateTime.now().isAfter(endAuction)){
				setWinnerByMaxAmount(products);
				OutBidList(bids);
				System.out.println("Auction has ended!");
				System.exit(0);
			}else{
				OutBidList(bidList);
			}

		};

		MyTimer myTimer = new MyTimer(); // Сreate a timer
		myTimer.repeat(runnable, 1000); // Repeat task every second
	}

	private static int OutBidList(List<IBid> bidList){
		System.out.println("\r\nAuction results:");

		bidList.stream() // Sort winning bids and show
			.filter(IBid::isWinning)
			.sorted(Comparator.comparing(IBid::getAmount))
			.forEach(System.out::println);
		return bidList.size();
	}

	public static List<IBid> getWinnerBidList(List<IBid> bids){
		return bids.stream() // Filter by winning bids
			.filter(IBid::isWinning)
			.collect(Collectors.toList());
	}

	public static void setWinnerByMaxAmount(List<IProduct> products){
		products.forEach(product ->
				bids.stream()
					.filter(bid -> product.getId() == bid.getProduct().getId())
					.max(Comparator.comparing(IBid::getAmount))
					.ifPresent(bid -> bid.setWinning(true))
		);
	}


}

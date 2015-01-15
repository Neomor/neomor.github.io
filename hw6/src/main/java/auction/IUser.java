package auction;

import java.util.List;

public interface IUser {

	void addBids(List<IProduct> products, List<IBid> bids, List<IUser> users);

	String sendEmail(String mes);

	void sendOverbidNotifications(IBid newBid, List<IBid> bids, List<IUser> users);

	boolean isOverbidNotifications();

	int getId();

	String getName();

}

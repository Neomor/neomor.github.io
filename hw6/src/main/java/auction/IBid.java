package auction;

import java.math.BigDecimal;
import java.util.List;

public interface IBid {

	void addBid(List<IBid> bids, List<IUser> users);

	int getId();

	boolean isWinning();

	void setWinning(boolean isWinning);

	BigDecimal getAmount();

	String getAmountStr();

	IUser getUser();

	IProduct getProduct();

}

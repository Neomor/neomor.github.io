package auction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IProduct {

	String getMinMax();

	void setAuctionIsOver(IUser user, IBid bid);

	LocalDateTime getAuctionEndTime();

	int getId();

	BigDecimal getReservedPrice();

	BigDecimal getMinimalPrice();

	String getTitle();

}
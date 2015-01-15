package auction

import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class IProductTest extends Specification {
    @Shared IUser user
    @Shared IProduct product
    @Shared IBid bid

    @Shared LocalDateTime auctionEndDateTime // Give a week to buy these products

    @Shared List<IProduct> products

    void setup() {
        user = new User(3, "Gedevan", "gedevan@gmail.com", true)
        auctionEndDateTime = LocalDateTime.now().plusWeeks(1) // Give a week to buy these products
        product = new Product(3, "iPhone 5c", "images/03.jpg", "iPhone 5c description", 23, auctionEndDateTime, 7, new BigDecimal(550.00), new BigDecimal(650.00))
        bid = new Bid(1, product, new BigDecimal(700.00), 5, user, LocalDateTime.now(), false)

        products = new ArrayList<IProduct>()
        products.add(new Product(1, "iPhone 5s", "images/01.jpg", "iPhone 5s description", 56, auctionEndDateTime, 14, new BigDecimal(650.00), new BigDecimal(850.00)))
        products.add(new Product(2, "iPhone 4s", "images/02.jpg", "iPhone 4s description", 34, auctionEndDateTime, 4, new BigDecimal(250.00), new BigDecimal(450)))
    }

    def "GetMinMax"() {
        expect:
        product.getMinMax() == "Product: \"" + product.getTitle() + "\" (" +
                "minimalPrice: \"" + String.format("%.2f", product.getMinimalPrice()) +
                "\"; reservedPrice: \"" + String.format("%.2f", product.getReservedPrice()) + "\")"
    }

    def "SetAuctionIsOver"() {
        when:
        product.setAuctionIsOver(user, bid)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        then:
        product.auctionEndTime.format(formatter) == LocalDateTime.now().format(formatter)
        bid.isWinning()
    }

    def "GetAuctionEndTime"() {
        expect:
        product.getAuctionEndTime().equals(auctionEndDateTime)
    }

    def "GetId"() {
        expect:
        product.getId() == 3
    }

    def "GetReservedPrice"() {
        expect:
        product.getReservedPrice().equals(new BigDecimal(650.00))
    }

    def "GetMinimalPrice"() {
        expect:
        product.getMinimalPrice().equals(new BigDecimal(550.00))
    }

    def "GetTitle"() {
        expect:
        product.getTitle() == "iPhone 5c"
    }
}

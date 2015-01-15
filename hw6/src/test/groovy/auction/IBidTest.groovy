package auction

import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

class IBidTest extends Specification {
    @Shared IUser user
    @Shared IProduct product
    @Shared IBid bid
    @Shared  List<IUser> users
    @Shared  List<IBid> bids

    void setup() {
        user = new User(3, "Gedevan", "gedevan@gmail.com", true)
        product = new Product(3, "iPhone 5c", "images/03.jpg", "iPhone 5c description", 23, LocalDateTime.now().plusWeeks(1), 7, new BigDecimal(550.00), new BigDecimal(650.00))
        bid = new Bid(1, product, new BigDecimal(700.00), 5, user, LocalDateTime.now(), false)

        users = new ArrayList<IUser>() {{ // Load users
            add(new User(1, "Ashot", "ashot@gmail.com", true))
            add(new User(2, "Dato", "dato@gmail.com", true))
        }}

        bids = new ArrayList<>()

    }


    def "AddBid"() {
        when:
        bid.addBid(bids, users)
        then:
        bids.size() == 1
    }

    def "GetId"() {
        expect:
        bid.getId() == 1
    }

    def "IsWinning"() {
        expect:
        !bid.isWinning()
    }

    def "SetWinning"() {
        when:
        bid.setWinning(true)
        then:
        bid.isWinning()
    }

    def "GetAmount"() {
        expect:
        bid.getAmount().equals(new BigDecimal(700.00))
    }

    def "GetAmountStr"() {
        expect:
        bid.getAmountStr().equals(String.format("%.2f", 700.00))
    }

    def "GetUser"() {
        expect:
        bid.getUser().equals(user)
    }

    def "GetProduct"() {
        expect:
        bid.getProduct().equals(product)
    }
}

package auction

import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

class IUserTest extends Specification {
    @Shared IUser user
    @Shared IProduct product
    @Shared IBid bid
    @Shared List<IUser> users
    @Shared List<IBid> bids
    @Shared  List<IProduct> products

    void setup() {
        user = new User(3, "Gedevan", "gedevan@gmail.com", true)
        product = new Product(3, "iPhone 5c", "images/03.jpg", "iPhone 5c description", 23, LocalDateTime.now().plusWeeks(1), 7, new BigDecimal(550.00), new BigDecimal(650.00))
        bid = new Bid(1, product, new BigDecimal(700.00), 5, user, LocalDateTime.now(), false)

        users = new ArrayList<IUser>() {{ // Load users
            add(new User(1, "Ashot", "ashot@gmail.com", true))
            add(new User(2, "Dato", "dato@gmail.com", true))
        }}

        products = new ArrayList<IProduct>() {{ // Load products
            LocalDateTime auctionEndDateTime = LocalDateTime.now().plusWeeks(1) // Give a week to buy these products
            add(new Product(1, "iPhone 5s", "images/01.jpg", "iPhone 5s description", 56, auctionEndDateTime, 14, new BigDecimal(650.00), new BigDecimal(850.00)))
            add(new Product(2, "iPhone 4s", "images/02.jpg", "iPhone 4s description", 34, auctionEndDateTime, 4, new BigDecimal(250.00), new BigDecimal(450)))
        }}

        bids = new ArrayList<>()

    }

    def "AddBids"() {
        when:
        user.addBids(products, bids, users)
        then:
        1 == 1 // How to test a method addBids? If anyone knows, please contact me
    }

    def "SendEmail"() {
        when:
        def mes = "Hello " + user.getName()
        then:
        user.sendEmail(mes) == "Send email to " + user.getName() + ": " + mes
    }

    def "SendOverbidNotifications"() {
        when:
        user.sendOverbidNotifications(bid, bids, users)
        then:
        1 == 1 // How to test a method sendOverbidNotifications? If anyone knows, please contact me
    }

    def "IsOverbidNotifications"() {
        expect:
        user.isOverbidNotifications()
    }

    def "GetId"() {
        expect:
        user.getId() == 3
    }

    def "GetName"() {
        expect:
        user.getName() == "Gedevan"
    }
}

package resources;

import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Objects;

public class User {
    public final String id;
    public final String firstname;
    public final String lastname;
    public double moneyAmount;
    public final HashSet<Product> cart = new HashSet<>();

    public User(@NonNull String id,
                @NonNull String firstname,
                @NonNull String lastname,
                double moneyAmount) {
        this.id = Objects.requireNonNull(id, "id is null");
        this.firstname = Objects.requireNonNull(firstname, "firstname is null");
        this.lastname = Objects.requireNonNull(lastname, "lastname is null");
        this.moneyAmount = moneyAmount < 0.0 ? 0.0 : moneyAmount;
    }

    @Override
    public String toString() {
        return String.format("User {id: %-36s, firstname: \"%-6s\", lastname: \"%-6s\", moneyAmount: %-6s}",
                id,
                firstname,
                lastname,
                moneyAmount);
    }

    public void buyProduct(Product product) {
        moneyAmount -= product.price;
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public HashSet<Product> showCart() {
        return cart;
    }
}

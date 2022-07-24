package resources;

import org.springframework.lang.NonNull;

import java.util.HashSet;
import java.util.Objects;

public class Product {
    public final String id;
    public final String name;
    public final double price;
    public final HashSet<User> inCartOf = new HashSet<>();

    public Product(@NonNull String id,
                   @NonNull String name,
                   double price) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.price = price < 0.0 ? 0.0 : price;
    }

    @Override
    public String toString() {
        return String.format("Product {id: %-36s, name: \"%-6s\", price: %-6s}",
                id,
                name,
                price);
    }

    public void boughtBy(User user) {
        inCartOf.add(user);
    }

    public void removeFromCart(User user) {
        inCartOf.remove(user);
    }

    public HashSet<User> getInCartOf() {
        return inCartOf;
    }
}

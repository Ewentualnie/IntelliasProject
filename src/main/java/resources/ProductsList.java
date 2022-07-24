package resources;

import config.Config;

import java.util.ArrayList;
import java.util.List;

public class ProductsList implements Config<Product> {
    public List<Product> products;

    public ProductsList() {
        products = new ArrayList<>();
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> list() {
        return products;
    }

    @Override
    public Product get(String id) {
        for (Product product : products) {
            if (product.id.equals(id))
                return product;
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Product toRemove = get(id);
        products.remove(toRemove);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            result.append(i < products.size() - 1 ? products.get(i) + "\n" : products.get(i) + "");
        }
        if (result.length() == 0) {
            result.append("No products there!");
        }
        return result.toString();
    }
}

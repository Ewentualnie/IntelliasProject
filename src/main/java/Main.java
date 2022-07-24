import resources.Product;
import resources.ProductsList;
import resources.User;
import resources.UsersList;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    UsersList users = new UsersList();
    ProductsList products = new ProductsList();

    public static void main(String[] args) {
        new Main().menu();
    }

    public void menu() {
        System.out.println("""
                MAIN MENU
                Enter line number to:
                1. Add users to application
                2. Add products to application
                3. Enter next menu
                4. Delete user by id
                5. Delete product by id
                6. Exit application""");
        Scanner scanner = new Scanner(System.in);
        int enter = scanner.nextInt();


        switch (enter) {
            case 1 -> addUser();
            case 2 -> addProduct();
            case 3 -> nextMenu();
            case 4 -> deleteUser();
            case 5 -> deleteProduct();
            case 6 -> System.out.close();
        }
    }

    private void deleteProduct() {
        System.out.println("Please, enter product id to delete it");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        Product product;
        try {
            product = products.get(id);
            products.delete(id);
            for (User user : users.list()) {
                if (user.cart.contains(product)) {
                    user.removeFromCart(product);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            menu();
        }
    }

    private void deleteUser() {
        System.out.println("Please, enter user id to delete it");
        Scanner scanner = new Scanner(System.in);
        String id = scanner.next();
        User user;
        try {
            user = users.get(id);
            users.delete(id);
            for (Product product : products.list()) {
                if (product.inCartOf.contains(user)) {
                    product.removeFromCart(user);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            menu();
        }
    }

    public void nextMenu() {
        System.out.println("""
                Enter line number:
                1. Display list of all users
                2. Display list of all products
                3. User wants to buy some products
                4. Display list of user products by user id
                5. Display list of users that bought product by product id
                6. Enter main menu
                7. Exit application""");
        Scanner scanner = new Scanner(System.in);
        int enter = scanner.nextInt();

        switch (enter) {
            case 1 -> {
                System.out.println(users + "\n");
                nextMenu();
            }
            case 2 -> {
                System.out.println(products + "\n");
                nextMenu();
            }
            case 3 -> makeDeal();
            case 4 -> showUserCart();
            case 5 -> showBoughtProducts();
            case 6 -> menu();
            case 7 -> System.out.close();
        }
    }

    private void showBoughtProducts() {
        System.out.println("Enter product id to show users who buy it");

        Product product = getCorrectProduct();
        System.out.println(product.getInCartOf() + "\n");

        nextMenu();
    }

    private void makeDeal() {
        System.out.println("Enter user id to try buy a product");
        User user = getCorrectUser();

        System.out.println("Enter product id");
        Product product = getCorrectProduct();

        if (user.moneyAmount >= product.price) {
            user.buyProduct(product);
            product.boughtBy(user);
            System.out.println(user.firstname + " " + user.lastname + " buy " + product.name);
        }
        try {
            System.out.println(user.showCart() + "\n");
        } catch (Exception e) {
            System.err.println(e.getMessage() + "\n");
        } finally {
            nextMenu();
        }
    }

    private Product getCorrectProduct() {
        Scanner scanner = new Scanner(System.in);
        String productId;
        Product product;
        do {
            productId = scanner.next();
            product = products.get(productId);
            if (product == null) {
                System.out.println("Please enter correct product id! :");
            }
        } while (product == null);
        return product;
    }

    private User getCorrectUser() {
        Scanner scanner = new Scanner(System.in);
        String userId;
        User user;
        do {
            userId = scanner.next();
            user = users.get(userId);
            if (user == null) {
                System.out.println("Please enter correct user id! :");
            }
        } while (user == null);
        return user;
    }

    private void showUserCart() {
        System.out.println("Enter user id to show his cart");
        User user = getCorrectUser();
        System.out.println(user.showCart() + "\n");

        nextMenu();
    }

    public void addUser() {
        System.out.println("Please, enter user name, lastname and amount of money he has");
        Scanner scanner = new Scanner(System.in);

        users.add(new User(idGenerator(), scanner.next(), scanner.next(), scanner.nextDouble()));

        System.out.println("Add another user? y / n");
        String answer = scanner.next();

        if (answer.equalsIgnoreCase("y")) {
            addUser();
        }
        menu();
    }

    public void addProduct() {
        System.out.println("Please, enter product name and it price");
        Scanner scanner = new Scanner(System.in);

        products.add(new Product(idGenerator(), scanner.next(), scanner.nextDouble()));

        System.out.println("Add another product? y / n");
        String answer = scanner.next();

        if (answer.equalsIgnoreCase("y")) {
            addProduct();
        }
        menu();
    }

    public String idGenerator() {
        return UUID.randomUUID().toString();
    }
}

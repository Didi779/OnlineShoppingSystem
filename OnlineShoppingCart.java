import java.util.*;

class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}

class ShoppingCart {
    private Map<Product, Integer> cartItems;

    public ShoppingCart() {
        cartItems = new HashMap<>();
    }

    public void addItem(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            cartItems.put(product, currentQuantity + quantity);
        } else {
            cartItems.put(product, quantity);
        }
    }

    public void removeItem(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            if (quantity >= currentQuantity) {
                cartItems.remove(product);
            } else {
                cartItems.put(product, currentQuantity - quantity);
            }
        }
    }

    public void displayCart() {
        System.out.println("Shopping Cart:");
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product + ", Added Quantity: " + quantity);
        }
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalCost += product.getPrice() * quantity;
        }
        return totalCost;
    }
}

public class OnlineShoppingCart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product1", 10.99, 50));
        products.add(new Product("Product2", 20.49, 30));
        products.add(new Product("Product3", 15.75, 20));

        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n===== Online Shopping Cart =====");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Calculate Total Cost");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");

            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Adding product to cart
                    System.out.println("Available Products:");
                    for (int i = 0; i < products.size(); i++) {
                        System.out.println((i + 1) + ". " + products.get(i));
                    }
                    System.out.print("Enter Product Number to Add: ");
                    int productNumber = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (productNumber > 0 && productNumber <= products.size()) {
                        Product selectedProduct = products.get(productNumber - 1);
                        if (selectedProduct.getQuantity() >= quantity) {
                            cart.addItem(selectedProduct, quantity);
                            System.out.println("Product added to cart.");
                        } else {
                            System.out.println("Not enough stock available.");
                        }
                    } else {
                        System.out.println("Invalid product number.");
                    }
                    break;

                case 2:
                    // Removing product from cart
                    cart.displayCart();
                    if (!cartItems.isEmpty()) {
                        System.out.print("Enter Product Name to Remove: ");
                        String productName = scanner.nextLine();
                        Product toRemove = null;
                        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                            if (entry.getKey().getName().equalsIgnoreCase(productName)) {
                                toRemove = entry.getKey();
                                break;
                            }
                        }
                        if (toRemove != null) {
                            System.out.print("Enter Quantity to Remove: ");
                            int removeQuantity = scanner.nextInt();
                            cart.removeItem(toRemove, removeQuantity);
                            System.out.println("Product removed from cart.");
                        } else {
                            System.out.println("Product not found in cart.");
                        }
                    } else {
                        System.out.println("Cart is empty.");
                    }
                    break;

                case 3:
                    // Displaying cart contents
                    cart.displayCart();
                    break;

                case 4:
                    // Calculating total cost
                    double totalCost = cart.calculateTotalCost();
                    System.out.println("Total Cost: $" + totalCost);
                    break;

                case 5:
                    // Checkout
                    double finalCost = cart.calculateTotalCost();
                    System.out.println("Total Cost: $" + finalCost);
                    System.out.println("Thank you for shopping with us! Checkout completed.");
                    cart = new ShoppingCart(); // Empty the cart after checkout
                    break;

                case 6:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }
    }
}

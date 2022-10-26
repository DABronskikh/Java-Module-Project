public class Product {
    String title;
    double price;

    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String toString() {
        return String.format("%s: %.2f", title, price);
    }
}
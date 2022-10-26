import java.util.Scanner;

class Main {

    public static boolean checkEnd(String input) {
        return "завершить".equals(input.trim().toLowerCase());
    }

    public static void main(String[] args) {
        InvoiceCalculator invoiceCalculator = new InvoiceCalculator();
        invoiceCalculator.requestCountUsers();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Добавьте новый товар или введите \"Завершить\"");
            String productTitle;
            double productPrice;
            String input;

            System.out.println("Введите название товара:");
            input = scanner.nextLine();
            if (checkEnd(input)) {
                break;
            }
            productTitle = input;

            System.out.println("Введите цену товара:");
            input = scanner.nextLine();
            if (checkEnd(input)) {
                break;
            }
            productPrice = Double.parseDouble(input);

            Product product = new Product(productTitle, productPrice);
            invoiceCalculator.addProduct(product);
        }

        invoiceCalculator.printProducts();
        invoiceCalculator.printResults();
    }
}
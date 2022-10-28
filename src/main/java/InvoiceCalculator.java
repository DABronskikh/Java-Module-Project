import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceCalculator {
    protected ArrayList<Product> products = new ArrayList<>();
    protected int countUsers;

    public void start() {
        this.requestCountUsers();
        this.addProducts();
        this.printProducts();
        this.printResults();
    }

    public void requestCountUsers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("На скольких человек необходимо разделить счёт?");

        String input;
        while (true) {
            input = scanner.nextLine();
            if (isInt(input) && Integer.parseInt(input) > 1) {
                break;
            } else {
                System.out.println("Кол-во должно быть больше 1");
                System.out.println("Повторите ввод:");
            }
        }

        this.countUsers = Integer.parseInt(input);
    }

    public void addProducts() {
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

            while (true) {
                System.out.println("Введите цену товара:");
                input = scanner.nextLine();
                if (isDouble(input)) {
                    productPrice = Double.parseDouble(input);
                    break;
                }
                System.out.println("Некорректное значение");
            }

            Product product = new Product(productTitle, productPrice);
            this.products.add(product);
        }
    }

    public void printProducts() {
        System.out.printf("%n -=-=-=-=-=-=-=- %n");

        if (this.products.size() == 0) {
            System.out.println("Список товаров пуст...");
        } else {
            double sum = 0;
            System.out.println("Добавленные товары:");
            for (int i = 0; i < this.products.size(); i++) {
                Product product = this.products.get(i);
                System.out.printf("%d) %s%n", i + 1, product);
                sum += product.price;
            }
            System.out.printf("Всего товаров: %s шт. на сумму %.2f%n", this.products.size(), sum);
        }
    }

    public double getAvgSum() {
        double sum = 0;
        if (this.products.size() == 0) {
            return 0;
        } else {
            for (int i = 0; i < this.products.size(); i++) {
                sum += this.products.get(i).price;
            }
            return sum / this.countUsers;
        }
    }

    public String getRubAddition(double sum) {
        String rubAddition = "рублей";
        int sumInt = (int) sum;
        int preLastDigit = sumInt % 100 / 10;
        if (preLastDigit == 1) {
            return rubAddition;
        }

        switch (sumInt % 10) {
            case 1:
                return "рубль";
            case 2:
            case 3:
            case 4:
                return "рубля";
            default:
                return rubAddition;
        }
    }

    public void printResults() {
        double avgSum = this.getAvgSum();
        String getRubAddition = this.getRubAddition(avgSum);
        System.out.printf("Итого: %.2f %s на человека%n", avgSum, getRubAddition);
    }

    public static boolean isInt(String input) throws NumberFormatException {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String input) throws NumberFormatException {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkEnd(String input) {
        return "завершить".equals(input.trim().toLowerCase());
    }

}

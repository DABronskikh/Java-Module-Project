import java.util.ArrayList;
import java.util.Scanner;

public class InvoiceCalculator {
    ArrayList<Product> products = new ArrayList<>();
    int countUsers;

    public void requestCountUsers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("На скольких человек необходимо разделить счёт?");

        int count;
        while (true) {
            count = scanner.nextInt();
            if (count > 1) {
                break;
            } else {
                System.out.println("Кол-во должно быть больше 1");
                System.out.println("Повторите ввод:");
            }
        }

        this.countUsers = count;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void printProducts() {
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

}
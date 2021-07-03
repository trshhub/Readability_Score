import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double x = scanner.nextDouble();

        System.out.println(Math.pow(x, 3) + Math.pow(x, 2) + Math.pow(x, 1) + Math.pow(x, 0));
    }
}
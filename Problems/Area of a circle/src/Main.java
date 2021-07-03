import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double radius = scanner.nextDouble();

        double circleSquare = Math.PI * Math.pow(radius, 2);
        System.out.println(circleSquare);

        double a = 8;
        double b = 5;
        double c = a / b + 1.1;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
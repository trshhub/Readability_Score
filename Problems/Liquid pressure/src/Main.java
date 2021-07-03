import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double dens = scanner.nextDouble();
        double height = scanner.nextDouble();
        double gravity = 9.8;

        double p = dens * gravity * height;
        System.out.println(p);
    }
}
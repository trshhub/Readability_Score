import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int sum = 0;
        int[] intArr = new int[size];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = scanner.nextInt();
        }

        int sumEls = Arrays.stream(intArr).sum();
        System.out.println(sumEls);
    }
}
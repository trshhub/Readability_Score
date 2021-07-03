import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();

        int[] intArr = new int[size];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = scanner.nextInt();
        }

        int minEl = Arrays.stream(intArr).min().getAsInt();

        System.out.println(minEl);
    }
}
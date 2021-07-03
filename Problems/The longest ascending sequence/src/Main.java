import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] intArr = new int[size];

        int longestStreak = 1;

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = scanner.nextInt();
        }
        int tmp_in = intArr[0];

        for (int i = 1; i < intArr.length; i++) {
            //System.out.println(intArr.length - i - 1);

            if (intArr[i] > tmp_in) {
                longestStreak++;
                tmp_in = intArr[i];
            }

            if (intArr[i] < tmp_in && longestStreak < intArr.length - i) {
                longestStreak = 1;
                tmp_in = intArr[i];
            }
        }

        System.out.println(longestStreak);
    }
}
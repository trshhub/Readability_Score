import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] intArr = new int[size];

        int cnt = 0;

        for (int i = 0; i < size; i++) {
            intArr[i] = scanner.nextInt();
        }

        int scanInt = scanner.nextInt();

        for (int number :
                intArr) {

            if (number == scanInt) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
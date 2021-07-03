import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        String pattern = ".....\\.";

        "a1b2c.".matches(pattern); // true
        "Wrong.".matches(pattern); // true
        "Hello!".matches(pattern); // false

        String backslash = ".....\\.";
        System.out.println(backslash);

        "a1b2c.".matches(pattern); // true
    }
}
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        // cosU = u * v / ( lenth U * length V )
        Scanner scanner = new Scanner(System.in);

        int u_i = scanner.nextInt();
        int u_j = scanner.nextInt();
        int v_i = scanner.nextInt();
        int v_j = scanner.nextInt();

        double uv = u_i * v_i + u_j * v_j;

        double lengthU = Math.sqrt(Math.pow(u_i, 2) + Math.pow(u_j, 2));
        double lengthV = Math.sqrt(Math.pow(v_i, 2) + Math.pow(v_j, 2));
        double lengthUV = lengthU * lengthV;

        //double cosine = Math.cos(Math.toRadians(uv / lengthUV));
        double cosineDegree = Math.toDegrees(Math.acos((uv / lengthUV)));


//        System.out.println(uv);
//        System.out.println(lengthUV);
//        System.out.println(uv / lengthUV);
//
//        System.out.println(Math.cos(Math.toRadians(45)));
        System.out.println(cosineDegree);

    }
}
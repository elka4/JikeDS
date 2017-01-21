package _1_Fundamentals._1_1_BasicProgModel;

public class Ex_1_1_06 {

    public static void main(String[] args) {

        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++)
        {
            StdLib.StdOut.println(f);
            f = f + g;
            g = f - g;
        }

    }

}

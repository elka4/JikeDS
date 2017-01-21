package _1_Fundamentals._1_2_DataAbstraction;

import StdLib.StdOut;

public class TestVisualAccumulator
{
    public static void main(String[] args)
    {
        int t = Integer.parseInt(args[0]);
        VisualAccumulator a = new VisualAccumulator(t, 1.0);
        for (int i = 0; i < t; i++)
            a.addDataValue(StdLib.StdRandom.random());
        StdOut.println(a);
    }
}

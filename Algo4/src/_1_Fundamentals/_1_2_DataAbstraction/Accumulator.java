package _1_Fundamentals._1_2_DataAbstraction;

import StdLib.StdOut;
import StdLib.StdRandom;

/*************************************************************************
 *  Compilation:  javac Accumulator.java
 *
 *  Mutable data type that calculates mean of data values.
 *
 *************************************************************************/


public class Accumulator {
    private double total;
    private int N;

    public void addDataValue(double val) {
        N++;
        total += val;
    }

    public double mean() {
        return total / N;
    }

    public String toString() {
        return "Mean (" + N + " values): " + String.format("%7.5f", mean());
    }

    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();
        for (int t = 0; t < T; t++) {
            a.addDataValue(StdRandom.random());
        }
        StdOut.println(a);
    }
}

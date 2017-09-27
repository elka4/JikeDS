package Classification.Bit_Manipulation_26;
import org.junit.Test;

import java.util.BitSet;

public class bitset {
    public static void main(String[] argv) throws Exception {
        // Create the bitset
        BitSet bits = new BitSet();

        // Set a bit on
        bits.set(2);

        // Retrieving the value of a bit
        boolean b = bits.get(0);

        b = bits.get(2);

        // Clear a bit
        bits.clear(1);

        // Setting a range of bits
        BitSet bits2 = new BitSet();
        bits2.set(1, 4);

        // And'ing two bitsets
        bits.and(bits2);

        // Xor'ing two bitsets
        bits.xor(bits2);

        // Flip all bits in the bitset
        bits.flip(0, bits.length());

        // Andnot'ing two bitsets
        bits.andNot(bits2);

        // Or'ing two bitsets
        bits.or(bits2);
    }

    @Test
    public void test01(){
        // Create the bitset
        BitSet bits = new BitSet();

        // Set a bit on
        bits.set(2);

        // Retrieving the value of a bit
        boolean b = bits.get(0);

        System.out.println(b);

        b = bits.get(2);
        System.out.println(b);

    }

    @Test
    public void test02(){
        // Create the bitset
        BitSet bits = new BitSet();

        // Set a bit on
        bits.set(2);

        // Retrieving the value of a bit
        boolean b = bits.get(0);

        b = bits.get(2);

        // Clear a bit
        bits.clear(1);

        // Setting a range of bits
        BitSet bits2 = new BitSet();
        bits2.set(1, 4);
        System.out.println(bits2.get(3));
    }
}

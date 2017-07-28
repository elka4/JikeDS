package Bit_Manipulation_26;
import java.util.BitSet;

public class BitArray {
    private BitSet bits;

    public BitArray(String bits)
    {
        this.bits = fromString(bits);
    }

    private BitSet getBitSet()
    {
        return bits;
    }

    private void setBitSet(BitSet bitSet )
    {
        bits = bitSet;
    }

    public BitArray and(BitArray bitarray)
    {
        BitSet bits1 = this.getBitSet();
        BitSet bits2 = bitarray.getBitSet();

        bits1.and(bits2);
        this.setBitSet(bits1);
        return this;
    }

    public BitArray or(BitArray bitarray)
    {
        BitSet bits1 = this.getBitSet();
        BitSet bits2 = bitarray.getBitSet();
        bits1.or(bits2);
        this.setBitSet(bits1);
        return this;
    }

    private BitSet fromString(String bit)
    {
        return BitSet.valueOf(new long[] { Long.parseLong(bit, 2) });
    }

    public String toString()
    {
        return Long.toString(bits.toLongArray()[0], 2);
    }

    public static void main (String...arg)
    {
        BitArray array1 = new BitArray("1010");
        BitArray array2 = new BitArray("1001");
        BitArray array3 = new BitArray("1100");

        System.out.println("The BitArray Are");
        System.out.println("First :" + array1);
        System.out.println("Second :" +array2);
        System.out.println("Third : " + array3);

        System.out.println("First AND Second");
        System.out.println(array1.and(array2));

        System.out.println("Second OR Third");
        System.out.println(array2.or(array3));
    }
}

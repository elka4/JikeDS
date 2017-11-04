public class Test {
    public static void main(String[] args) throws Exception {
        for (int n = 1 << 20; n > 0; n += n) {
            System.gc();
            long t0 = System.currentTimeMillis();
            int[] a = new int[n];
            long t = System.currentTimeMillis() - t0;
            System.out.println("new int[" + n + "]: " + t + " ms");
        }
    }
}

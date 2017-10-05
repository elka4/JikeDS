package July._2Sort;

public class _3SortColors_75 {
    public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int pl = 0;//have only 0 on its left
        int pr = a.length - 1;//have only 2 on its right
        int i = 0;//have both 0 and 1 on its left

        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else {
                swap(a, pr, i);
                pr--;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

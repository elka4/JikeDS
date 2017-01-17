package top100.Enhance._3Graph_PreClass;

public class _3_NumberOfIsland_UnionFind {
	public int numIslands(char[][] grid) {
		int m = grid.length;//added by zhu
		int n = grid[0].length;//added by zhu
        int[] groupTag = new int[m * n];
        for (int i = 0; i < m; i++) {
             for (int j = 0; j < n; j++) {
                 groupTag[i * n + j] = grid[i][j] == '1' ? i * n + j: -1;
             }
        }
        for (int i = 0; i < m; i++) {
             for (int j = 0; j < n; j++) {
                 if (grid[i][j] == '0') {
                     continue;
                 }
                 if (j + 1 < n && grid[i][j+1] == '1') {
                     union(i, j, i, j + 1, groupTag, n);
                 }
                 if (i + 1 < m && grid[i+1][j] == '1') {
                     union(i, j, i + 1, j, groupTag, n);
                 }
            }
        }
        int count = 0;
        for (int i = 0; i < m * n; i++) {
            if (groupTag[i] == i) {
                count++;
            }
        }
        return count;
}
private void find (int smIndex, int bgIndex, int[] tag) {
        while(tag[bgIndex] != bgIndex) {
            int tmp = tag[bgIndex];
            tag[bgIndex] = tag[smIndex];
            bgIndex = tmp;
        }

        tag[bgIndex] = tag[smIndex];
}
private void union(int i1, int j1, int i2, int j2, int[] tag, int n) {

        int index1 = i1 * n + j1;
        int index2 = i2 * n + j2;
        if (tag[index1] == tag[index2]) {
            return;
        }

        if (tag[index1] < tag[index2]) {
            if (tag[index2] != index2) {
                find(index1, index2, tag);
            }
            tag[index2] = tag[index1];
        } else {
            if (tag[index1] != index1) {
                find(index2, index1, tag);
            }
            tag[index1] = tag[index2];
        }
}
}

package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _096_Tree_Unique_Binary_Search_Trees_M {

    public int numTrees(int n) {
        int [] G = new int[n+1]; G[0] = G[1] = 1;
        for(int i=2; i<=n; ++i) { for(int j=1; j<=i; ++j) {
            G[i] += G[j-1] * G[i-j]; }
        }
        return G[n];
    }
    public int number(int n){ if(n==0)return 1;
        if(n==1)return 1;
        int result[]=new int [n+1]; result[0]=1;
        result[1]=1;
        result[2]=2;
        if(n<3){
            return result[n];
        }
        for(int i=3;i<=n;i++){
            for(int k=1;k<=i;k++){ result[i]=result[i]+result[k-1]*result[i-k];
            } }
        return result[n];
    }
}

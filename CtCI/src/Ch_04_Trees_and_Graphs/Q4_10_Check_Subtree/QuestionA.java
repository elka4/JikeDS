package Ch_04_Trees_and_Graphs.Q4_10_Check_Subtree;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;
import org.junit.Test;

public class QuestionA {
	
	public static boolean containsTree(TreeNode t1, TreeNode t2) {
		StringBuilder string1 = new StringBuilder();
		StringBuilder string2 = new StringBuilder();
		
		getOrderString(t1, string1);
		getOrderString(t2, string2);

		//return string1.indexOf(string2.toString()) != -1;
		return string1.indexOf(string2.toString()) != -1;
	}

    public String nodeToString(TreeNode t) {
        StringBuilder string1 = new StringBuilder();
        getOrderString(t, string1);
        return string1.toString();
    }
	
	public static void getOrderString(TreeNode node, StringBuilder sb) {
		if (node == null) {
			sb.append("#");             // Add null indicator
			return;
		}
		sb.append(node.data);           // Add root 
		getOrderString(node.left, sb);  // Add left
		getOrderString(node.right, sb); // Add right
	}

	public static void main(String[] args) {
		// t2 is a subtree of t1
		int[] array1 = {1, 2, 1, 3, 1, 1, 5};
		int[] array2 = {2, 3, 1};
		
		TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t2 = AssortedMethods.createTreeFromArray(array2);
		t1.print();
		t2.print();
		if (containsTree(t1, t2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}

		// t4 is not a subtree of t3
		int[] array3 = {1, 2, 3};
		TreeNode t3 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t4 = AssortedMethods.createTreeFromArray(array3);
        t3.print();
        t4.print();
		if (containsTree(t3, t4)) {
			System.out.println("t4 is a subtree of t3");
		} else {
			System.out.println("t4 is not a subtree of t3");
		}
	}

	/*
                           1
                          / \
                         /   \
                         2   1
                        / \ / \
                        3 1 1 5

                         2
                        / \
                        3 1

                        t2 is a subtree of t1
                           1
                          / \
                         /   \
                         2   1
                        / \ / \
                        3 1 1 5

                         1
                        / \
                        2 3

                        t4 is not a subtree of t3
	 */
    @Test
    public void test01(){
        // t2 is a subtree of t1
        int[] array1 = {5, 2, 1, 3, 4, 6, 7};
        int[] array2 = {2, 3, 1};

        TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
        TreeNode t2 = AssortedMethods.createTreeFromArray(array2);
        t1.print();
        t2.print();
        if (containsTree(t1, t2)) {
            System.out.println("t2 is a subtree of t1");
        } else {
            System.out.println("t2 is not a subtree of t1");
        }

        System.out.println(nodeToString(t1));
        System.out.println(nodeToString(t2));
    }
    /*
                               5
                              / \
                             /   \
                             2   1
                            / \ / \
                            3 4 6 7

                             2
                            / \
                            3 1

                            t2 is not a subtree of t1
                            523##4##16##7##
                            23##1##
     */

}

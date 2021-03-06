package Ch_04_Trees_and_Graphs.Q4_03_List_of_Depths;

import CtCILibrary.AssortedMethods;
import CtCILibrary.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class QuestionBFS {

	public static ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> result = new ArrayList<LinkedList<TreeNode>>();
		
		/* "Visit" the root */
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		if (root != null) {
			current.add(root);
		}
		
		while (current.size() > 0) {
			result.add(current); // Add previous level
//            System.out.println("current " + current);
			LinkedList<TreeNode> parents = current; // Go to next level
//            System.out.println("parents " + parents);

            current = new LinkedList<TreeNode>();
//            System.out.println("current2 " + current);
//            System.out.println("parents2 " + parents);

			for (TreeNode parent : parents) {
				/* Visit the children */
				if (parent.left != null) {
					current.add(parent.left);
				}
				if (parent.right != null) {
					current.add(parent.right);
				}
			}
		}

		return result;
	}
	
	public static void printResult(ArrayList<LinkedList<TreeNode>> result){
		int depth = 0;
		for(LinkedList<TreeNode> entry : result) {
			Iterator<TreeNode> i = entry.listIterator();
			System.out.print("Link list at depth " + depth + ":");
			while(i.hasNext()){
				System.out.print(" " + ((TreeNode)i.next()).data);
			}
			System.out.println();
			depth++;
		}
	}

    public static void visit(CtCILibrary.TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
        }
    }

    public static void inOrderTraversal(CtCILibrary.TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            visit(node);
            inOrderTraversal(node.right);
        }
    }

    public static void preOrderTraversal(CtCILibrary.TreeNode node) {
        if (node != null) {
            visit(node);
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
        }
    }

    public static void postOrderTraversal(CtCILibrary.TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
            visit(node);
        }
    }
    public static void main(String[] args) {
		int[] nodes_flattened = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
		root.print();
		ArrayList<LinkedList<TreeNode>> list = createLevelLinkedList(root);
		printResult(list);
        System.out.println(list);
    }
    /*
       1
      / \
     /   \
    /     \
   /       \
   2       3
  / \     / \
 /   \   /   \
 4   5   6   7
/ \ /
8 9 10

Link list at depth 0: 1
Link list at depth 1: 2 3
Link list at depth 2: 4 5 6 7
Link list at depth 3: 8 9 10

[[Node 1], [Node 2, Node 3], [Node 4, Node 5, Node 6, Node 7], [Node 8, Node 9, Node 10]]

     */

    @Test
    public void test01(){
        int[] nodes_flattened = {5,2,7,1,3,6,8,9};
        TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
        root.print();
        ArrayList<LinkedList<TreeNode>> list = createLevelLinkedList(root);
        printResult(list);
        preOrderTraversal(root);
    }

    /*
       5
      / \
     /   \
    /     \
   /       \
   2       7
  / \     / \
 /   \   /   \
 1   3   6   8
/
9

Link list at depth 0: 5
Link list at depth 1: 2 7
Link list at depth 2: 1 3 6 8
Link list at depth 3: 9

     */

}

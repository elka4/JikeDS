package _04_Tree._Tree;
import java.util.*;import lib.*;
import org.junit.Test;
public class _297_Tree_Serialize_and_Deserialize_Binary_Tree_M {
    public class Codec {
        private static final String spliter = ",";
        private static final String NN = "X";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NN).append(spliter);
            } else {
                sb.append(node.val).append(spliter);
                buildString(node.left, sb);
                buildString(node.right,sb);
            }
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> nodes = new LinkedList<>();
            nodes.addAll(Arrays.asList(data.split(spliter)));
            return buildTree(nodes);
        }

        private TreeNode buildTree(Deque<String> nodes) {
            String val = nodes.remove();
            if (val.equals(NN)) return null;
            else {
                TreeNode node = new TreeNode(Integer.valueOf(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }


    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helperS(root, sb);
        return sb.toString();
    }

    private void helperS(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append("null").append(",");
            return;
        }

        sb.append(node.val).append(",");

        helperS(node.left, sb);
        helperS(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] vals  = data.split("[,]");
        int[]    index = new int[]{0};
        return helperD(vals, index);
    }

    private TreeNode helperD(String[] vals, int[] index){
        if(index[0] == vals.length){
            return null;
        }

        String visiting = vals[index[0]++];
        if(visiting.equals("null")){
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(visiting));
        node.left     = helperD(vals, index);
        node.right    = helperD(vals, index);

        return node;
    }

}

package datastructs.tree;

public class BinaryTree {
    TreeNode root;
    public BinaryTree(int value){
        root = new TreeNode(value);
    }
    public BinaryTree(){
        root = null;
    }
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();

        /*create root*/
        tree.root = new TreeNode(1);

        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);

        tree.root.left.left = new TreeNode(4);
    }

}

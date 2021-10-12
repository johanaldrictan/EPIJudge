package datastructs.tree;

public class BinarySearchTree extends BinaryTree{
    public BinarySearchTree(){
        super();
    }
    public void insert(int val){
        root = insertRec(root, val);
    }
    public TreeNode insertRec(TreeNode parent, int val){
        if(parent == null){
            parent = new TreeNode(val);
            return parent;
        }
        if(val < parent.value)
            parent.left = insertRec(parent.left, val);
        else if(val > parent.value)
            parent.right = insertRec(parent.right, val);
        return parent;
    }
    public void inOrder(){
        inOrderRec(root);
    }
    public void inOrderRec(TreeNode parent){
        if(parent != null){
            inOrderRec(parent.left);
            System.out.println(parent.value);
            inOrderRec(parent.right);
        }
    }
    public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.inOrder();
    }
}

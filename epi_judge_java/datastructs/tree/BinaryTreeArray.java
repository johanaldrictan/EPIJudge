package datastructs.tree;

public class BinaryTreeArray {
    private int[] tree;
    private int size;
    private static final int INIT_CAP = 10;

    public int getRoot() { return tree[0]; }
    public void setRoot(int val) {
        tree[0] = val;
        if(size == 0) { size++; }
    }

    @SuppressWarnings("unchecked")
    public BinaryTreeArray(){
        size = 0;
        tree = new int[INIT_CAP];
    }

    public int getLeftChild(int pos){
        if((pos * 2) + 1 >= size)
            throw new IndexOutOfBoundsException();
        return tree[(pos * 2) + 1];
    }

    public int getRightChild(int pos){
        if((pos * 2) + 2 >= size)
            throw new IndexOutOfBoundsException();
        return tree[(pos * 2) + 2];
    }

    public void setLeftChild(int val, int pos){
        int i = (pos * 2) + 1;
        if(tree[pos] == 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        else{
            if(tree[i] == 0)
                size++;
            tree[i] = val;
        }
    }

    public void setRightChild(int val, int pos){
        int i = (pos * 2) + 2;
        if(tree[pos] == 0){
            throw new ArrayIndexOutOfBoundsException();
        }
        else{
            if(tree[i] == 0)
                size++;
            tree[i] = val;
        }
    }

    public void print_Tree()
    {

        // Iterating using for loop
        for (int i = 0; i < 10; i++) {
            if (tree[i] != 0)
                System.out.print(tree[i]);
            else
                System.out.print("-");
        }
    }
}

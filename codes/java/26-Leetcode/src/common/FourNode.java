package common;

public class FourNode {
    public boolean val;
    public boolean isLeaf;
    public FourNode topLeft;
    public FourNode topRight;
    public FourNode bottomLeft;
    public FourNode bottomRight;


    public FourNode() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public FourNode(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public FourNode(boolean val, boolean isLeaf, FourNode topLeft, FourNode topRight, FourNode bottomLeft, FourNode bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

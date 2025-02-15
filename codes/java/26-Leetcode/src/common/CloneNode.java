package common;

import java.util.ArrayList;
import java.util.List;

public class CloneNode {
    public int val;
    public List<CloneNode> neighbors;
    public CloneNode() {
        val = 0;
        neighbors = new ArrayList<CloneNode>();
    }
    public CloneNode(int _val) {
        val = _val;
        neighbors = new ArrayList<CloneNode>();
    }
    public CloneNode(int _val, ArrayList<CloneNode> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

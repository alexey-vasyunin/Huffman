public class Node implements Comparable<Node>{
    private Node parent;
    private Node childLeft;
    private Node childRight;
    private Character character;
    private Integer freq;
    private StringBuilder code;

    public String getCode() {
        return code.toString();
    }

    public void setChildLeft(Node childLeft) {
        this.childLeft = childLeft;
    }

    public void setChildRight(Node childRight) {
        this.childRight = childRight;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChildLeft() {
        return childLeft;
    }

    public Node getChildRight() {
        return childRight;
    }

    public Integer getFreq() {
        return freq;
    }

    public Node(Character character, Node parent, Node childLeft, Node childRight, Integer freq) {
        this.parent = parent;
        this.childLeft = childLeft;
        this.childRight = childRight;
        this.character = character;
        this.freq = freq;
        this.code = new StringBuilder();
    }

    public void incFreq(){
        freq++;
    }

    public int compareTo(Node o) {
        return  freq - o.freq;
    }

    public boolean hasLeft(){
        return childLeft != null;
    }

    public boolean hasRight(){
        return childRight != null;
    }

    public void computeCode(){
        if (parent == null) code.append("0");
        Node current = this;
        while (current.parent != null) {
            if (current == current.parent.getChildLeft()) code.insert(0,"0"); else code.insert(0,"1");
            current = current.parent;
        }
    }

    public char getChar(StringBuilder code){
        // If it's leaf
        if (!hasRight() && !hasLeft()) return character;
        if (code.charAt(0) == '0')
            return getChildLeft().getChar(code.deleteCharAt(0));
        else
            return getChildRight().getChar(code.deleteCharAt(0));
    }

}

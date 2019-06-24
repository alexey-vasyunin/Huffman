import java.util.*;
import java.util.stream.Collectors;

public class Huffman {
    public static void main(String[] args) {
        String in = "abacabad";
        HashMap<Character, Integer> vocab = new HashMap<>();
        PriorityQueue<Node> tree = new PriorityQueue<>();
        for (int i = 0; i < in.length(); i++) {
            Character current = in.charAt(i);
            System.out.print(current);
            if (vocab.containsKey(current)) {
                vocab.replace(current, vocab.get(current)+1);
            } else {
                vocab.put(current, 1);
            }
        }
        System.out.println();
        List codes = new ArrayList<>(vocab.entrySet());



        for(Object c : codes){
            Map.Entry<Character, Integer> code = (Map.Entry<Character, Integer>)c;
            tree.add(new Node(null, null, null, code.getKey(), code.getValue()));
        }

        System.out.println(tree);

        while (tree.size() > 1){
            Node node1 = tree.poll();
            Node node2 = tree.poll();
            System.out.println(node1);
            System.out.println(node2);
            Node parent = new Node(null, node1, node2, null, node1.getFreq() + node2.getFreq());
            node1.parent = parent;
            node2.parent = parent;
            tree.add(parent);
            System.out.println("TREE: " + tree);

        }

        for (int i = 0; i < in.length(); i++) {
            Character current = in.charAt(i);
            System.out.print(current);
            if (vocab.containsKey(current)) {
                vocab.replace(current, vocab.get(current)+1);
            } else {
                vocab.put(current, 1);
            }
        }

        System.out.println(tree);
    }


}

class Node implements Comparable<Node>{
    Node parent;
    Node childLeft;
    Node childRight;
    Character o;
    Integer freq;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getChildLeft() {
        return childLeft;
    }

    public void setChildLeft(Node childLeft) {
        this.childLeft = childLeft;
    }

    public Node getChildRight() {
        return childRight;
    }

    public void setChildRight(Node childRight) {
        this.childRight = childRight;
    }

    public Character getO() {
        return o;
    }

    public void setO(Character o) {
        this.o = o;
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }

    public Node(Node parent, Node childLeft, Node childRight, Character o, Integer freq) {
        this.parent = parent;
        this.childLeft = childLeft;
        this.childRight = childRight;
        this.o = o;
        this.freq = freq;
    }

    public Node() {
    }

    @Override
    public String toString() {
        return "<" + o + ':' + freq + ((getChildLeft() != null ) ?", Left: " + getChildLeft(): "") + ((getChildRight() != null) ?", Right: "+ getChildRight() : "" )  + '>';
    }

    public int compareTo(Node o) {
        return  freq - o.freq;
    }
}
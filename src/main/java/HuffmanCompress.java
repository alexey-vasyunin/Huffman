import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCompress {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String in = s.nextLine();

        HashMap<Character, Node> vocab = new HashMap<>();

        for (int i = 0; i < in.length(); i++) {
            Character current = in.charAt(i);
            if (vocab.containsKey(current)) {
                vocab.get(current).incFreq();
            } else {
                vocab.put(current, new Node(current, null, null, null, 1));
            }
        }

        PriorityQueue<Node> tree = new PriorityQueue<>(vocab.values());

        while (tree.size() > 1){
            Node node1 = tree.poll();
            Node node2 = tree.poll();
            Node parent = new Node(null, null, node1, node2, node1.getFreq() + node2.getFreq());
            node1.setParent(parent);
            node2.setParent(parent);
            tree.add(parent);
        }


        for (Node node : vocab.values()) node.computeCode();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < in.length(); i++) {
            Character current = in.charAt(i);
            result.append(vocab.get(current).getCode());
        }

        System.out.println(vocab.size() + " " + result.length());
        vocab.entrySet().stream().forEach(entry -> { System.out.println(entry.getKey() + ": " + entry.getValue().getCode()); });
        System.out.println(result);
    }


}

class Node implements Comparable<Node>{
    private Node parent;
    private Node childLeft;
    private Node childRight;
    private Character character;
    private Integer freq;
    private StringBuilder code;

    public String getCode() {
        return code.toString();
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

    public void computeCode(){
        if (parent == null) code.append("0");
        Node current = this;
        while (current.parent != null) {
            if (current == current.parent.getChildLeft()) code.insert(0,"0"); else code.insert(0,"1");
            current = current.parent;
        }
    }
}
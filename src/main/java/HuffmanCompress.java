import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCompress {
    public static void main(String[] args) {

        /*

        Sample Input:
            Loremipsumdolor

        Sample Output:
            11 50
            p: 0100
            r: 101
            s: 0111
            d: 0101
            e: 0110
            u: 1110
            i: 1111
            L: 1000
            l: 1001
            m: 110
            o: 00
            10000010101101101111010001111110110010100100100101
        * */

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
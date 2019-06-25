import java.util.Scanner;

public class HuffmanDecompress {
    public static void main(String[] args) {

        /*
        Sample Input:
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
        Sample Output:
            Loremipsumdolor
         */
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int length = in.nextInt();

        Node head = new Node(null, null, null, null, 0);

        for (int i = 0; i < count; i++) {
            String strChar = in.next();
            String code = in.next();

            Node current = head;

            for (int j = 0; j < code.length(); j++) {
                Character character = null;
                // If it's leaf
                if (j == code.length()-1) character = strChar.charAt(0);

                // Choose direction
                if (code.charAt(j) == '0'){
                    // if exists - next
                    if (current.hasLeft()){
                        current = current.getChildLeft();
                    } else {
                        Node newChild = new Node(character, current, null, null, null);
                        current.setChildLeft(newChild);
                        current = newChild;
                    }
                } else {
                    if (current.hasRight()){
                        current = current.getChildRight();
                    } else {
                        Node newChild = new Node(character, current, null, null, null);
                        current.setChildRight(newChild);
                        current = newChild;
                    }
                }
            }

        }


        StringBuilder sb = new StringBuilder(in.next());
        while (sb.length() > 0) System.out.print(head.getChar(sb));

    }
}



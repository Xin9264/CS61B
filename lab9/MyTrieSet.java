import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class MyTrieSet implements TrieSet61B{
    private int n;
    private static class Node{
        private boolean val;
        private char c;
        private Map<Character, Node> children ;
        public Node (char c, boolean val) {
            this.c = c;
            this.val = val;
            children = new HashMap<>();
        }
    }
    public MyTrieSet(){
        root = new Node(' ', false);
    }
    private Node root;
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1 || root == null){
            return false;
        }
        Node curr = root;
        for (int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if (!curr.children.containsKey(c)){
                return false;
            }
            curr = curr.children.get(c);
            if (curr == null){
                return false;
            }
        }
        return curr.val;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new Node(c, false));
            }
            curr = curr.children.get(c);
        }
        curr.val = true;
    }

    @Override
    /*
    return a list of all words that start with prefix
     */
    public List<String> keysWithPrefix(String prefix) {
        if (prefix == null || prefix.length() < 1 || root == null){
            throw new IllegalArgumentException();
        }
        List<String> result = new ArrayList<>();
        Node startNode = root;
        for (int i = 0; i < prefix.length(); i += 1){
            char c = prefix.charAt(i);
            startNode = startNode.children.get(c);
        }
        if (startNode.val){
            result.add(prefix);
        }
        for (Node curr : startNode.children.values()){
            if (curr != null){
                keysWithPrefix(result, prefix, curr);
            }
        }
        return result;
    }

    private void keysWithPrefix(List<String> result, String word, Node curr){
        if (curr.val){
            result.add(word + curr.c);
        }
        for (Node n : curr.children.values()){
            if (n != null){
                keysWithPrefix(result, word + curr.c, n);
            }
        }
    }
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args){
        String[] saStrings = new String[]{"same", "sam", "sad", "sap"};
        String[] otherStrings = new String[]{"a", "awls", "hello"};

        MyTrieSet t = new MyTrieSet();
        for (String s: saStrings) {
            t.add(s);
        }
        for (String s: otherStrings) {
            t.add(s);
        }

        List<String> keys = t.keysWithPrefix("sa");
//        for (String s: saStrings) {
//            System.out.println(keys.contains(s));
//        }
        System.out.println(keys.contains("same"));
    }
}

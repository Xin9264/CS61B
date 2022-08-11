import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node{
        private K key;
        private V val;
        private Node left, right;
        private int size;

        public Node (K key, V val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }
    private Node root;
    public BSTMap(){

    }
//    public boolean isEmpty(){
//        return size() == 0;
//    }
    int size = 0;

    public boolean contains(K key){
        if (key == null){
            throw new IllegalArgumentException("argument to contaims() is null");
        }
        return get(key) != null;
    }


    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key){
        if (key == null){
            throw new IllegalArgumentException();
        }
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        }
        else if (cmp > 0) {
            return get(x.right, key);
        }
        else  {
            return x.val;
        }
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x){
        if (x == null){
            return 0;
        }
        return x.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
    }
    private Node put(Node x, K key, V val){
        if (x == null){
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0){
            x.right = put(x.right, key, val);
        }
        else {
            x.val = val;
        }
        x.size = 1 + size(x.right) + size(x.left);
        return x;
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder(){
        for (int i = 0; i < size(); i++){
            System.out.println(select(i).key + " " + select(i).val);
        }
    }
    private Node select(int k){
        if (k < 0 || k >= size()){
            throw new IllegalArgumentException();
        }
        return select(root, k);
    }

    private Node select(Node x, int k){
        if (x == null){
            return null;
        }
        int t = size(x.left);
        if (t > k){
            return select(x.left, k);
        }
        else if (t < k){
            return select(x.right, k);
        }
        else{
            return x;
        }

    }
}

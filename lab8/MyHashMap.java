import javax.swing.*;
import java.nio.channels.IllegalChannelGroupException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private int threshold;
    hash_bucket[] buckets;
    public int size;
    public MyHashMap(){
        buckets = new hash_bucket[initialSize];
        threshold = (int) (initialSize * loadFactor);
        size = 0;
    }

    public MyHashMap(int initialSize){
        this.initialSize = initialSize;
        buckets = new hash_bucket[initialSize];
        threshold = (int) (initialSize * loadFactor);
        size = 0;
    }

    public MyHashMap(int initialSize, double loadFactor){
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        buckets = new hash_bucket[initialSize];
        threshold = (int) (initialSize * loadFactor);
        size = 0;
    }
    private class hash_bucket<K, V>{
        private K key;
        private V val;
        private hash_bucket next;
        private int hashcode;
        public hash_bucket(int hashcode, K key, V val, hash_bucket<K, V> next){
            this.hashcode = hashcode;
            this.key = key;
            this.val = val;
            this.next = next;
        }

        public int getHashCode(){
            return hashcode;
        }

        public void setHashcode(int hashcode){
            this.hashcode = hashcode;
        }

        public K getKey(){
            return key;
        }

        public void setKey(K key){
            this.key = key;
        }

        public V getVal(){
            return val;
        }

        public void setVal(V val){
            this.val = val;
        }

        public hash_bucket<K, V> getnext(){
            return next;
        }

        public void setNext(hash_bucket<K, V> next){
            this.next = next;
        }

    }
    @Override
    public void clear() {
        buckets = new hash_bucket[buckets.length];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    private int hash(K key, int length){
        if (key == null){
            throw new IllegalArgumentException();
        }
        return (key.hashCode() & 0x7fffffff) % length;
    }
    @Override
    public V get(K key) {
        if (key == null){
            throw new IllegalArgumentException();
        }
        int hashCode = hash(key, buckets.length);
        hash_bucket<K, V> entity = get(hashCode, key);
        return entity == null ? null : entity.getVal();
    }

    private hash_bucket<K, V> get(int hashCode, K key){
        hash_bucket<K, V> entity = buckets[hashCode];
        while (entity != null){
            if (entity.getHashCode() == hashCode && entity.getKey().equals(key)){
                return entity;
            }
            entity = entity.getnext();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int hashCode = hash(key, buckets.length);
        hash_bucket<K, V> entity = buckets[hashCode];
        while (entity != null){
            // 检查是否已经存在key, 若已经存在 则更新val
            if (entity.getHashCode() == hashCode && entity.getKey().equals(key)){
                entity.setVal(value);
                return;
            }
            entity = entity.getnext();
        }
        put(hashCode, key, value);
    }

    private void put(int hashCode, K key, V val){
        hash_bucket<K, V> entity = new hash_bucket<>(hashCode, key, val, buckets[hashCode]);
        buckets[hashCode] = entity;
        size += 1;
        if (size > threshold){
            resize(buckets.length * 2);
        }
    }

    private void resize(int length){
        hash_bucket<K, V>[] newBucket = new hash_bucket[length];
        for (int i = 0; i < buckets.length; i++){
            hash_bucket<K, V> entity = buckets[i];
            while (entity != null){
                hash_bucket<K, V> oldNext = entity.getnext();
                int newHashCode = hash(entity.getKey(), newBucket.length);
                entity.setNext(newBucket[newHashCode]);
                entity.setHashcode(newHashCode);
                newBucket[newHashCode] = entity;
                entity = oldNext;
            }
        }
        buckets = newBucket;
        threshold = (int)(buckets.length * loadFactor);
    }
    @Override
    public Set<K> keySet() {
        Set<K> allKeys = new HashSet<>();
        for (int i = 0; i < buckets.length; i++){
            hash_bucket<K, V> entity = buckets[i];
            while (entity != null){
                allKeys.add(entity.getKey());
                entity = entity.getnext();
            }
        }
        return allKeys;
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
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}

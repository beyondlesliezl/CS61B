package lab9;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if(p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        } else if (cmp > 0) {
            return getHelper(key, p.right);
        } else return p.value;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size++;
            return new Node(key,value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (cmp < 0){
            p.left = putHelper(key, value, p.left);
            }
        else p.value = value;
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keyHelper(root);
    }
    private Set<K> keyHelper(Node p){
       if (p == null) {
           return null;
       }
       Set<K> set = new HashSet<K>();
       set.add(p.key);
       Set<K> setRight = keyHelper(p.right);
       Set<K> setLeft = keyHelper(p.left);
       set.addAll(setRight);
       set.addAll(setLeft);
       return set;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node p) {
        if (p.left == null) {
            size--;
            return p.right;
        }
        p.left = deleteMin(p.left);
        return p;
    }

    @Override
    public V remove(K key) {
        V val = get(key);
        if (val == null) {
            return null;
        }
        root = removeHelper(root, key);
        return val;
    }

    public K min() {
        return min(root).key;
    }
    private Node min(Node p) {
        if (p.left == null) return p;
        return min(p.left);
    }
    private Node  removeHelper(Node p, K key) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = removeHelper(p.left, key);
        } else if (cmp > 0) {
            p.right = removeHelper(p.right, key);
        } else {
            if (p.right == null) return p.left;
            if (p.left == null) return p.right;
            Node t = p;
            p = min(t.right);
            p.right = deleteMin(t.right);
            p.left = t.left;
        }
        return p;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V target = get(key);
        if (target == null || target != value) return null;
        else {
            root = removeHelper(root, key);
            return target;
        }
    }


    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}

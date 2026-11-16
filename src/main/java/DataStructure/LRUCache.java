package DataStructure;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private class Node{
        int key;
        int val;
        Node next;
        Node prev;

        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private final int capacity;
    private final Node head = new Node(0,0);
    private final Node tail = new Node(0,0);

    private final Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity){
        if(capacity < 1){
            throw new IllegalArgumentException("capacity must be at least 1 but was " + capacity);
        }
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToEnd(Node node){
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        remove(node);
        addToEnd(node);
        return node.val;
    }


    public void put(int key, int value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            remove(node);
            addToEnd(node);
            return;
        }

        if(map.size() == capacity){
            Node lru = head.next;
            remove(lru);
            map.remove(lru.key);
        }

        Node node = new Node(key, value);
        addToEnd(node);
        map.put(key, node);
    }

    public static void main(String... s) {
        LRUCache cache = new LRUCache(3);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        System.out.println(cache.get(2));
        cache.put(4, 40);
        System.out.println(cache.get(5));
        cache.put(5, 50);
    }
}

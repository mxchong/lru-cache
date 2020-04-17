package com.mxc.lru.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author chenkaideng
 * @Date 2019/8/6
 **/
public class LruCache {

    private Map<Integer, Node> map = new HashMap<Integer, Node>();
    private Node head = null;
    private Node end = null;

    /**
     * 容量
     */
    private int capacity;

    public LruCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            remove(n);
            setHead(n);
            printNodes("get");
            return n.getValue();
        }
        printNodes("get");
        return -1;
    }

    public void remove(Node n) {
        if (n.getPreNode() != null) {
            n.getPreNode().setNextNode(n.getNextNode());
        } else {
            head = n.getNextNode();
        }

        if (n.getNextNode() != null) {
            n.getNextNode().setPreNode(n.getPreNode());
        } else {
            end = n.getPreNode();
        }

    }

    public void setHead(Node n) {
        n.setNextNode(head);
        n.setPreNode(null);

        if (head != null) {
            head.setPreNode(n);
        }

        head = n;

        if (end == null) {
            end = head;
        }
    }

    public void set(int key, int value) {
        if (map.containsKey(key)) {
            Node old = map.get(key);
            old.setValue(value);
            remove(old);
            setHead(old);
        } else {
            Node created = new Node(key, value);
            if (map.size() >= capacity) {
                map.remove(end.getKey());
                remove(end);
                setHead(created);
            } else {
                setHead(created);
            }
            map.put(key, created);
        }
        printNodes("set");
    }

    public void printNodes(String explain) {
        System.out.print(explain + ":" + head.toString());
        Node node = head.getNextNode();
        while (node != null) {
            System.out.print(node.toString());
            node = node.getNextNode();
        }
        System.out.println();
    }
}

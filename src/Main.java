import java.util.Random;
import java.util.LinkedList;

public class Main {

    static class Node {
        int key;
        Node left, right;
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    static Node insert(Node root, int key) {
        if (root == null) return new Node(key);
        if (key < root.key) root.left = insert(root.left, key);
        else if (key > root.key) root.right = insert(root.right, key);
        return root;
    }

    static Node search(Node root, int key) {
        if (root == null || root.key == key) return root;
        if (root.key > key) return search(root.left, key);
        return search(root.right, key);
    }

    static Node delete(Node root, int key) {
        if (root == null) return root;
        if (key < root.key) root.left = delete(root.left, key);
        else if (key > root.key) root.right = delete(root.right, key);
        else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            root.key = minValue(root.right);
            root.right = delete(root.right, root.key);
        }
        return root;
    }

    static int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    static class HashTable {
        int size;
        LinkedList<Integer>[] table;

        @SuppressWarnings("unchecked")
        public HashTable(int size) {
            this.size = size;
            table = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                table[i] = new LinkedList<>();
            }
        }

        int hash(int key) {
            return Math.abs(key) % size;
        }

        void insert(int key) {
            int index = hash(key);
            if (!table[index].contains(key)) table[index].add(key);
        }

        boolean search(int key) {
            int index = hash(key);
            return table[index].contains(key);
        }

        void delete(int key) {
            int index = hash(key);
            table[index].remove((Integer) key);
        }
    }

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000};
        Random rand = new Random();

        System.out.println("Test Results:");

        for (int size : sizes) {
            int[] data = new int[size];
            int[] searchKeys = new int[Math.min(size, 100)];
            int[] deleteKeys = new int[Math.min(size, 50)];

            for (int i = 0; i < size; i++) data[i] = rand.nextInt(100000);
            for (int i = 0; i < searchKeys.length; i++) searchKeys[i] = data[rand.nextInt(size)];
            for (int i = 0; i < deleteKeys.length; i++) deleteKeys[i] = data[rand.nextInt(size)];

            long startBst = System.nanoTime();
            Node root = null;
            for (int d : data) root = insert(root, d);
            for (int k : searchKeys) search(root, k);
            for (int k : deleteKeys) root = delete(root, k);
            long endBst = System.nanoTime();
            double bstTime = (endBst - startBst) / 1_000_000_000.0;

            long startHt = System.nanoTime();
            HashTable ht = new HashTable(size * 2);
            for (int d : data) ht.insert(d);
            for (int k : searchKeys) ht.search(k);
            for (int k : deleteKeys) ht.delete(k);
            long endHt = System.nanoTime();
            double htTime = (endHt - startHt) / 1_000_000_000.0;

            System.out.printf("N = %d: BST time = %.6f sec | Hash Table time = %.6f sec\n", size, bstTime, htTime);
        }
    }
}
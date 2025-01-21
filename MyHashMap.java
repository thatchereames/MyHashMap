/**
 * Name: Thatcher Eames
 * Email: theames@ucsd.edu
 * PID: A17284279
 * Sources Used: PA 5 Write-Up, CSE 12 Textbook
 * 
 * The purpose of this file is to create a Hash Map data structure.
 */

/**
 * This class is a Hash Map data type. It has the capability to construct 
 * instances of Hash Maps; find, add, and remove elements; as well as return the 
 * size of and clear the hash map.
 */
public class MyHashMap<K,V> {
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;

    Node[] hashTable;
    int size;

    /**
     * No-arg constructor of MyHashMap. Initializes the capacity to 5.
     */
    public MyHashMap() {
        hashTable = new Node[DEFAULT_CAPACITY];
    }
    /**
     * Constructor of MyHashMap. Initializes the capacity to the specified size.
     * Throws an Illegal Arguement Exception if the initialCapacity arguemnt is 
     * less 0 or negative.
     * @param initialCapacity Int representing the desired capacity
     */
    public MyHashMap(int initialCapacity) {
        if(initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        hashTable = new Node[initialCapacity];
    }

    /**
     * Returns the value of an element mapped to the specified key. Returns null
     * if the key is not in the map. Throws a Null Pointer Exception if key is 
     * null.
     * @param key Key of the desired value
     * @return Value of type V mapped to the specified key
     */
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Node currNode = getNode(key);
        if(currNode == null) {
            return null;
        } else {
            return (V) currNode.getValue();
        }
    }

    /**
     * Places the specified value into the hash table based on the key. Throws a
     * Null Pointer Exception if the key or value parameters are null.
     * @param key Key the value will be mapped to
     * @param value Value that is mapped at the key
     * @return Value previously mapped to the key
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new NullPointerException();
        } else if (hashTable.length <= capacityOccupation() * LOAD_FACTOR) {
            expandCapacity();
        }
        //Stores the node currently mapped to the key
        Node currNode = getNode(key);
        // Remaps the value if the key already exists and returns the old value
        if (currNode != null) {
            V oldData = (V) currNode.getValue();
            currNode.setValue(value);
            return oldData;
        }
        int keyHash = getHash(key, hashTable.length);
        Node newNode = new Node(key, value);
        //Adds the node to hashtable 
        if (hashTable[keyHash] != null) {
            Node lastNode = getLast(keyHash);
            lastNode.setNext(newNode);
        } else {
            hashTable[keyHash] = newNode;
        }
        size++;
        return null;
    }

    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Node currNode = getNode(key);
        int keyHash = getHash(key, hashTable.length);
        if (currNode == null) {
            return null;
        } else if (currNode == hashTable[keyHash]) {
            if (currNode.getNext() == null) {
                hashTable[keyHash] = null;
            } else {
                hashTable[keyHash] = currNode.getNext();
            }
            size--;
            return (V) currNode.getValue();
        }
        Node beforeCurrNode = hashTable[keyHash];
        while (beforeCurrNode.getNext() != currNode) {
            beforeCurrNode = beforeCurrNode.getNext();
        }
        if (currNode.getNext() == null) {
            beforeCurrNode.setNext(null);
        } else {
            beforeCurrNode.setNext(currNode.getNext());
        }
        size--;
        return (V) currNode.getValue();
    }

    /**
     * Returns the number of elements currently in the MyHashTable
     * @return The number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Returns the capacity of the MyHashMap
     * @return Capacity of MyHashMap
     */
    public int getCapacity() {
        return hashTable.length;
    }

    /**
     * Removes all the mapped elements from MyHashMap
     */
    public void clear() {
        for (int i = 0; i < hashTable.length ; i++) {
            hashTable[i] = null;
        }
        size = 0;
    }
    
    /**
     * Checks if 
     * @return
     */
    public boolean isEmpty() {
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     */
    public void expandCapacity() {
        Node[] oldMap = hashTable;
        hashTable = new Node[hashTable.length * EXPAND_CAPACITY_RATIO];
        size = 0;
        for (int i = 0; i < oldMap.length; i++) {
            if(oldMap[i] != null) {
                Node currNode = oldMap[i];
                boolean contin = true;
                while(contin) {
                    put((K) currNode.getKey(), (V) currNode.getValue());
                    if(currNode.getNext() == null) {
                        contin = false;
                    } else {
                        currNode = currNode.getNext();
                    }
                }
            }
        }
    }

    /**
     * Takes in the key and the capacity of the hashtable it will be stored in 
     * and returns the hash for the key in that table. Will throw an exception 
     * if the key is null or the capacity is 0 or negative.
     * @param key The key that is turned into a hash
     * @param capacity Capacity of the Hash Table the key will be stored in
     * @return The hash of the key
     */
    public int getHash(K key, int capacity) {
        if (key == null) {
            throw new NullPointerException();
        } else if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        return key.hashCode() % capacity;
    }

    /**
     * Helper method that finds and returns the node at a specified key in the
     * table. Returns null if there is no node with that key
     * 
     * @param nodeKey Key of the node that is being searched for
     * @return Node stored to the key
     */
    private Node getNode(K nodeKey) {
        int keyHash = getHash(nodeKey, hashTable.length);
        if (hashTable[keyHash] == null) {
            return null;
        }
        boolean contin = true;
        Node currNode = hashTable[keyHash];
        while (contin) {
            if (currNode.getKey().equals(nodeKey)) {
                return currNode;
            } else if (currNode.getNext() == null) {
                contin = false;
            } else {
                currNode = currNode.getNext();
            }
        }
        return null;
    }

    /**
     * Helper method that returns the last node in the linked list at the
     * specified index in the hash table
     * @param index Index in the hash table of the linked list
     * @return Node at the end of the linked list
     */
    private Node getLast(int index) {
        Node currNode = hashTable[index];
        while (currNode.getNext() != null) {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    /**
     * Helper method that returns the number of elements in the Hash Table array
     * that are non-null (using capacity).
     */
    private int capacityOccupation() {
        int capacityOccupation = 0;
        for (int i = 0; i < hashTable.length; i++) {
            if(hashTable[i] != null) {
                capacityOccupation++;
            }
        }
        return capacityOccupation;
    }

    /**
     * A Node class that holds (key, value) pairs and references to the next 
     * node in the linked list
     */
    protected class Node<K,V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * @param key key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * @param node the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && this.value.equals(other.value);
        }
    }
}
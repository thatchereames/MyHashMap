/**
 * Name: Thatcher Eames
 * Email: theames@ucsd.edu
 * PID: A17284279
 * Sources Used: PA 5 Write-Up, CSE 12 Textbook
 * 
 * The purpose of this file is to create a Hash Set that utilizes the methods of
 * the MyHashMap.java file. Only uses the keys of the hash map and stores a 
 * generic Object as the value.
 */

/**
 * This class creates a Hash Set that utilizes the methods created in the
 * MyHashMap.java file. It has the capability to construct instances of the Hash
 * Set as well as add, remove, and find methods. It stores the size of the Hash
 * Set and can return that value as well as clear the set.
 */
public class MyHashSet<E> {
    public static final Object DEFAULT_OBJECT = new Object();
    
    MyHashMap<E, Object> hashMap;

    /**
     * No arg constructor of the Hash Set. Instantiates the capacity to 5.
     */
    public MyHashSet() {
        hashMap = new MyHashMap<>();
    }
    /**
     * Constructor of Hash Set. Takes in a desired capacity and instantiates a 
     * Hash Set with that capacity. Throws an error if the capacity is 0 or less
     * than 0.
     * @param initialCapacity Int representing the desired capacity
     */
    public MyHashSet(int initialCapacity) {
        //Throws an error if the param is 0 or less than 0
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        //Initializes MyHashMap to the inputed capacity
        hashMap = new MyHashMap<>(initialCapacity);
    }

    /**
     * Adds a specified element to the Hash Set. Throws an error if the element
     * is null. Returns true if the element was not already in the Set and false
     * if it was already in the set.
     * @param element The element that is to be added to the Hash Set
     * @return Boolean value representing 
     */
    public boolean add(E element) {
        //Throws an error if the element is null
        if (element == null) {
            throw new NullPointerException();
        }
        //Adds the element to MyHashSet and stores the return value
        Object prevElement = hashMap.put(element, DEFAULT_OBJECT);
        //Checks if the return value indicates the set already contained the
        //element and returns the corresponding boolean value
        if (prevElement == null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Removes the inputed element from the hash set. Throws an error if the
     * specified element is null. Returns true if the element was in the hash
     * set and false if it was not.
     * @param element
     * @return
     */
    public boolean remove(E element) {
        //Removes the element from the MyHashTable and stores the return value
        Object removedElement = hashMap.remove(element);
        if (removedElement == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns the size of the MyHashSet
     * @return Int representing the size
     */
    public int size() {
        return hashMap.size();
    }
    /**
     * Deletes all of the values stored in the MyHashSet
     */
    public void clear() {
        hashMap.clear();
    }

    /**
     * Returns true if the MyHashSet is empty and false if it has elements 
     * stored in it
     * @return Boolean representing if MyHashSet is empty
     */
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }
}
/*
 * Name: Casey Hild
 * Email: child@ucsd.edu
 * PID: A16953257
 * Sources Used: JDK 17 Doc
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The custom tester for PA5, which covers some basic test cases.
 */
public class CustomTester{
    MyHashMap<String, Integer> emptyMap;
    MyHashMap<String, Integer> threeElementMap;
    MyHashSet<String> emptySet;
    MyHashSet<String> threeElementSet;

    /**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
    @Before
    public void setup() {
        // Empty map
        emptyMap = new MyHashMap<String, Integer>();

        // Map with 3 elements
        threeElementMap = new MyHashMap<String, Integer>();

        threeElementMap.put("A", 1);
        threeElementMap.put("B", 2);
        threeElementMap.put("F", 6);

        // Empty set
        emptySet = new MyHashSet<String>();

        //Set with 3 elements
        threeElementSet = new MyHashSet<String>();
        
        threeElementSet.add("A");
        threeElementSet.add("B");
        threeElementSet.add("F");
    }

    // ----------------MyHashMap class----------------

    /**
     * Test MyHashMap constructor with an invalid initial capacity
     */
    @Test
    public void testMyHashMapConstructorInvalidCapacity() {
        assertThrows(IllegalArgumentException.class , 
            () -> {new MyHashMap<Integer,Integer>(-3);});
    }

    /**
     * Test MyHashMap get with a null key
     */
    @Test
    public void testMyHashMapGetNullKey() {
        assertThrows(NullPointerException.class , 
        () -> {threeElementMap.get(null);});
    }

    /**
     * Test MyHashMap get with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapGetKeyDoesNotExist() {
        assertEquals( null, threeElementMap.get("C"));
        assertEquals(null , emptyMap.get("B"));
    }

    /**
     * Test MyHashMap put with a null key
     */
    @Test
    public void testMyHashMapPutNullKey() {
        assertThrows(NullPointerException.class , 
            () -> {emptyMap.put(null, 5);});
        assertEquals(0, emptyMap.size());
    }

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashMapPutKeyAlreadyExists() {
        assertEquals(Integer.valueOf(6), 
            threeElementMap.put("F", 3));
        assertEquals(3, threeElementMap.size());
        assertEquals(Integer.valueOf("3"), threeElementMap.get("F"));
    }

    /**
     * Test MyHashMap remove with a null key
     */
    @Test
    public void testMyHashMapRemoveNullKey() {
        assertThrows(NullPointerException.class, 
            () -> {threeElementMap.remove(null);});
    }

    /**
     * Test MyHashMap remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapRemoveKeyDoesNotExist() {
        assertEquals(null, threeElementMap.remove("C"));
        assertEquals(3, threeElementSet.size());
    }

    /**
     * Test MyHashMap getHash with a null key
     */
    @Test
    public void testMyHashMapGetHashNullKey() {
        assertThrows(NullPointerException.class , 
            () -> {threeElementMap.getHash(null, 3);});
    }

    // ----------------MyHashSet class----------------

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashSetAddAlreadyExists() {
        assertEquals(false, threeElementSet.add("A"));
        assertEquals(3, threeElementSet.size());
    }

    /**
     * Test MyHashSet remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashSetRemoveDoesNotExist() {
        assertEquals(false, threeElementSet.remove("C"));
        assertEquals(3, threeElementSet.size());
        assertEquals(false, emptySet.remove("C"));
    }

}
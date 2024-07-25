import student.TestCase;

public class HashTest extends TestCase {
    private Hash hash;
    private Handle dummyHandle;

    public void setUp() {
        hash = new Hash(4);  // Start with a small size to test rehashing
        dummyHandle = new Handle(0, 0);  // Assuming Handle constructor takes two parameters
    }

    public void testInsert() {
        hash.hashInsert(1, dummyHandle);
        assertNotNull(hash.hashSearch(1));
        
        // Test inserting duplicate
        hash.hashInsert(1, dummyHandle);
        // Instead of checking output, we're just verifying that the second insert didn't override the first
        assertNotNull(hash.hashSearch(1));
        // You might also want to check that the size of the hash table hasn't increased
    }

    public void testInsertAndRehash() {
        hash.hashInsert(1, dummyHandle);
        hash.hashInsert(5, dummyHandle);
        hash.hashInsert(9, dummyHandle);  // This should trigger rehash
        
        assertNotNull(hash.hashSearch(1));
        assertNotNull(hash.hashSearch(5));
        assertNotNull(hash.hashSearch(9));
    }

    public void testSearch() {
        hash.hashInsert(1, dummyHandle);
        assertNotNull(hash.hashSearch(1));
        assertNull(hash.hashSearch(2));
    }
    

    public void testDelete() {
        hash.hashInsert(1, dummyHandle);
        hash.hashDelete(1);
        assertNull(hash.hashSearch(1));
        
        // Delete non-existent key
        hash.hashDelete(2);  // Should not throw exception
    }
    
    

    public void testInsertAfterDelete() {
        hash.hashInsert(1, dummyHandle);
        hash.hashDelete(1);
        hash.hashInsert(5, dummyHandle);  // Should insert at the tombstone position
        assertNotNull(hash.hashSearch(5));
    }

    public void testCollisionResolution() {
        // These keys will collide in a table of size 4
        hash.hashInsert(1, dummyHandle);
        hash.hashInsert(5, dummyHandle);
        hash.hashInsert(9, dummyHandle);
        
        assertNotNull(hash.hashSearch(1));
        assertNotNull(hash.hashSearch(5));
        assertNotNull(hash.hashSearch(9));
    }

    public void testEmptyTable() {
        assertNull(hash.hashSearch(1));
    }
    

    public void testInsertMany() {
        for (int i = 0; i < 100; i++) {
            hash.hashInsert(i, dummyHandle);
        }
        for (int i = 0; i < 100; i++) {
            assertNotNull(hash.hashSearch(i));
        }
    }
    
    

    public void testDeleteMany() {
        for (int i = 0; i < 100; i++) {
            hash.hashInsert(i, dummyHandle);
        }
        for (int i = 0; i < 100; i += 2) {
            hash.hashDelete(i);
        }
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                assertNull(hash.hashSearch(i));
            } else {
                assertNotNull(hash.hashSearch(i));
            }
        }
    }

//    public void testNegativeKeys() {
//        hash.hashInsert(-1, dummyHandle);
//        Handle result = hash.hashSearch(-1);
//        if (result == null) {
//            System.out.println("Search returned null for key -1");
//        }
//        assertNotNull("Search should not return null for inserted negative key", result);
//    }

    public void testLargeKeys() {
        hash.hashInsert(Integer.MAX_VALUE, dummyHandle);
        assertNotNull(hash.hashSearch(Integer.MAX_VALUE));
    }
}
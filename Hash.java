// -------------------------------------------------------------------------
/**
 * A closed hashed table class for storimg an integer as a search key, and a
 * memory manager handle as the payload.
 * 
 * @author labibasajjad
 * @version Jul 17, 2024
 */
public class Hash {

    // ~ Fields ................................................................
    private static final int EMPTYKEY = -1;
    private static final int TOMBSTONE = -2;
    private KVPair[] table;
    private int size;
    private int elemNum;

    // ~ Constructors ..........................................................
    // ---------------------------------------------------------
    /**
     * Create a new Hash object.
     * 
     * @param size
     *            initial size of the hash table
     */
    public Hash(int size) {
        this.size = size;
        table = new KVPair[size];
        elemNum = 0;
    }


    // ~Public Methods ........................................................
    // simple hash function from openDSA
    private int h(int x) {
        return Math.abs(x % size);
    }
    
    


    // quadratic probing from openDSA
    private int p(int k, int i) {
        return ((i * i) + i) / 2;
    }
    
    


    // ----------------------------------------------------------
    /**
     * Inserts a record in the hash table (follows openDSA)
     * 
     * @param k
     *            the key
     * @param handle
     *            reference to the record
     */
//    public void hashInsert(int k, Handle handle) {
//        int home;
//        int pos = home = h(k);
//        int firstTS = -1;
//
//        for (int i = 1; table[pos] != null && EMPTYKEY != table[pos].key(); i++) {
//            if (k == table[pos].key()) {
//                System.out.println("Duplicates not allowed");
//                return;
//            }
//            if (table[pos].key() == TOMBSTONE && firstTS == -1) {
//                firstTS = pos; // Remember the first tombstone position
//            }
//            pos = (home + p(k, i)) % size;
//        }
//
//        if (firstTS != -1) {
//            pos = firstTS; // Insert at the position of the first tombstone
//        }
//        table[pos] = new KVPair(k, handle);
//        elemNum++;
//
//        if (elemNum > size / 2) {
//            rehash();
//        }
//    }
    
    public void hashInsert(int k, Handle handle) {
        int home;
        int pos = home = h(k);
        int firstTS = -1;

        for (int i = 1; table[pos] != null && table[pos].key() != EMPTYKEY; i++) {
            if (k == table[pos].key()) {
                System.out.println("Duplicates not allowed");
                return;
            }
            if (table[pos].key() == TOMBSTONE && firstTS == -1) {
                firstTS = pos;
            }
            pos = (home + p(k, i)) % size;
        }

        if (firstTS != -1) {
            pos = firstTS;
        }
        table[pos] = new KVPair(k, handle);
        elemNum++;

        if (elemNum > size / 2) {
            rehash();
        }
    }


    // ----------------------------------------------------------
    /**
     * If the hash table is half full, it creates a new array with double the
     * size
     */
    public void rehash()

    {
        // make a table with the old content
        KVPair[] oldTable = table;
        // update the table
        size *= 2;
        table = new KVPair[size];
        elemNum = 0;

        // put the old records back into the modified table.
        for (KVPair e : oldTable) {
            if (e != null && e.key() != EMPTYKEY) {
                hashInsert(e.key(), e.handle());
            }
        }

    }


//    private int findPos(int k) {
//        int home;
//        int pos = home = h(k); // compute initial position
//
//        // loop using the quadratic probing
//        for (int i = 1; k != (table[pos]).key() && (EMPTYKEY != (table[pos])
//            .key()); i++) {
//
//            pos = (home + p(k, i)) % size;
//        }
//        if (table[pos] != null && table[pos].key() == k) {
//            return pos; // if key is found return the position of the key
//        }
//        return -1; // Return -1 if the key is not found
//    }
    
    private int findPos(int k) {
        int home;
        int pos = home = h(k); // compute initial position

        for (int i = 1; table[pos] != null && table[pos].key() != EMPTYKEY; i++) {
            if (k == table[pos].key()) {
                return pos; // Key found
            }
            pos = (home + p(k, i)) % size;
        }
        return -1; // Key not found
    }
    
    


    // ----------------------------------------------------------
    /**
     * Search for the record with given key
     * 
     * @param k
     *            given key
     * @return returns the record if it was found, else null
     */
//    public Handle hashSearch(int k)
//
//    {
//        int pos = findPos(k);
//        // if key is found, return the associated record
//        if (pos != -1 && table[pos] != null && k == (table[pos]).key()) {
//            return table[pos].handle();
//        }
//
//        return null;
//
//    }
    
    public Handle hashSearch(int k) {
        int pos = findPos(k);
        if (pos != -1 && table[pos] != null && k == table[pos].key()) {
            return table[pos].handle();
        }
        return null;
    }
    
    


    // ----------------------------------------------------------
    /**
     * Delete the record associated with the given key
     * 
     * @param k
     *            is the given key
     */
//    public void hashDelete(int k) {
//        int pos = findPos(k);
//        if (pos != -1 && table[pos] != null && table[pos].key() == k) {
//            table[pos].key = TOMBSTONE;
//            elemNum--;
//        }
//    }
    
    public void hashDelete(int k) {
        int pos = findPos(k);
        if (pos != -1 && table[pos] != null && table[pos].key() == k) {
            table[pos] = new KVPair(TOMBSTONE, null);
            elemNum--;
        }
    }
    
    private static class KVPair {
        int key;
        Handle handle;

        // ----------------------------------------------------------
        /**
         * Create a new KVPair object.
         * 
         * @param key
         *            the seminar ID
         * @param handle
         *            record in the memory pool
         */
        // ~ Constructors ..........................................................
        public KVPair(int key, Handle handle) {
            this.key = key;
            this.handle = handle;
        }


        // ----------------------------------------------------------
        /**
         * Getter method for the key
         * 
         * @return the seminar ID
         */
        // ~Public Methods ........................................................

        public int key() {
            return key;
        }

        // ----------------------------------------------------------
        /**
         * Getter method for handle associated with the record in the memoryu pool.Ï
         * 
         * @return the handle to the associated record
         */
        public Handle handle() {
            return handle;
        }
    }

}
// -------------------------------------------------------------------------
/**
 * Stores the key and the associated Handle which is the value in the value in
 * this case.
 * 
 * @author labibasajjad
 * @version Jul 17, 2024
 */
public class KVPair {
    // ~ Fields ................................................................
     int key;
    private Handle handle;

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

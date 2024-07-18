// -------------------------------------------------------------------------
/**
 * A reference to the record stored in the memory pool byt keeping track of the
 * position and length of the rceord in the pool.
 * 
 * @author labibasajjad
 * @version Jul 17, 2024
 */
public class Handle {
    // ~ Fields ................................................................
    private int position;
    private int len;

    // ----------------------------------------------------------
    /**
     * Create a new Handle object.
     * 
     * @param position
     *            position of the record in pool
     * @param len
     *            length of the record in the memory pool
     */
    // ~ Constructors ..........................................................
    Handle(int position, int len) {
        this.position = position;
        this.len = len;
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Getter method for the position of the record
     * 
     * @return the position of the record
     */
    public int getPosition() {
        return position;
    }


    // ----------------------------------------------------------
    /**
     * Getter method for the length of the record
     * @return the length of the record
     */
    public int getLength() {
        return len;
    }

}

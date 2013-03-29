/**
 * @Class block
 * @File block.java
 * @Author Samuel Caguana and Vincent Giannone
 * Date last Modified: 3/28/2013
 * @Version 1.0
 * 
 * Read Me: The purpose of this class is to provide the functionality and data
 * associated with a block. A block is the basic unit of cache storage. 
 * 
 */

public class block {
	
	/** 
	 * Instance Variables 
	 * 	blockTag -> Stores tag of this address
	 *  blockValid -> States that this block has data or not
	 *  blockLru -> Stores the priority of this block 
	 *  relative to a set set of blocks
	 */
    protected long blockTag;
    protected boolean blockValid;
    protected int blockLru;
    
    /**
     * Constructs this block and its instance variables
     * 
     * @param lru
     */
    public block(int lru){
        blockValid = false;
        blockLru = lru;
        blockTag = -1;
    }
    
    /**
     * Compares this block tag to a tag parameter
     * 
     * @param tag
     */
    public boolean compareTag(long tag){
        if(tag == blockTag){
            return true;
        }
        return false;
    }
    
    /**
     * Sets this block with to the current tag of memory address
     * 
     * @param tag
     */
    public void setTag(long tag){
        this.blockValid = true;
        this.blockTag = tag;
    }
}
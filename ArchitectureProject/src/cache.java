/**
 * @Class cache
 * @File cache.java
 * @Author Samuel Caguana and Vincent Giannone
 * Date last Modified: 3/28/2013
 * @Version 1.3
 * 
 * Read Me: The purpose of this class is to provide the functionality and data
 * associated with a cache. A cache is the component of a processor that stores
 * data and addresses for future requests, so that they may be served faster. 
 * 
 */

public class cache {
	
	/** 
	 * Instance Variables 
	 * 	cacheSize -> The size, in bytes, of this cache 
	 *  blockSize -> The size, in bytes, of the blocks in this cache
	 *  setAssociativity -> The type of set associativity of this cache
	 *  setsInCache -> A pointer to an array of set objects in this cache
	 *  numOfBlocks -> The number of blocks in each set of this cache 
	 *  numOfSets -> The number of sets of blocks in this cache
	 *  offsetWidth -> The size of the offset binary string of this cache
	 *  indexWidth -> The size of the index binary string of this cache
	 *  tagWidth -> The size of the tag binary string of this cache
	 */
    private int cacheSize, blockSize, setAssociativity;
    private set setsInCache[];
    private int numOfBlocks, numOfSets, offsetWidth, indexWidth, tagWidth;
    
    /**
     * Constructs this cache and its instance variables. The calculations for 
     * several of the instance variables are performed in an external method.  
     * 
     * @param blockSize
     * @param cacheSize
     * @param setAssoc
     */
    public cache(int blockSize, int cacheSize, int setAssoc){
    	this.cacheSize = cacheSize;
    	this.blockSize = blockSize;
    	this.setAssociativity = setAssoc;
        calcCacheAttrib();
    	this.setsInCache = new set[numOfSets];
    	for(int i = 0; i < setsInCache.length; i++){
    		this.setsInCache[i] = new set(setAssociativity);
    	}
    }
    
    /**
     * A method, called by the constructor, that calculates the attributes
     * of this cache. 
     */
    public void calcCacheAttrib(){
    	this.numOfBlocks = cacheSize/blockSize;
    	this.numOfSets = numOfBlocks/setAssociativity;
    	this.offsetWidth = (int)(Math.log10(blockSize)/Math.log10(2));
    	this.indexWidth = (int)(Math.log10(numOfSets)/Math.log10(2));
    	this.tagWidth = 32 - offsetWidth - indexWidth;
    }
    
    /**
     * Simulates a memory access. An address is passed as a parameter, and split
     * to its subcomponents. The tagWidth, indexWidth and offsetWidth are calculated at
     * this caches construction. 
     * 
     * The values of tag and index are used to determine which set of blocks will be 
     * searched and there was a hit. Calling the searchBlocksInSet method, where the set 
     * called is determined by index, tag is passed. If there was a hit, this method returns 
     * true. If there was a miss, return false. 
     * 
     * @param address
     */
    public boolean accessMemory(String address){
        int l = 32-address.length();
        if(address.length() < 32){
            for(int i = 0; i < l; i++){
                address = '0' + address;
            }
        }
        String tag = address.substring(0, tagWidth);
        String index = address.substring(tagWidth, tagWidth + indexWidth);
        String offset = address.substring(tagWidth + indexWidth, tagWidth + indexWidth + offsetWidth);       
        long Tag = Long.parseLong(tag, 2);
        int Index = Integer.parseInt(index, 2);
        if(setsInCache[Index].searchBlocksInSet(Tag)){
            return true;
        }
        return false;
    }
}
/**
 * @Class set
 * @File set.java
 * @Author Samuel Caguana and Vincent Giannone
 * Date last Modified: 3/28/2013
 * @Version 1.5
 * 
 * Read Me: The purpose of this class is to provide the functionality and data
 * associated with a set. Determined by the layout of the cache, a set is a 
 * row of blocks. 
 * 
 */

public class set {
    
	/** 
	 * Instance Variables 
	 * 	numBlocks -> Stores the number of blocks in this set
	 *  blocksInSet -> A pointer to an array of block objects 
	 *  in this set
	 */
    private int numBlocks;
    private block blocksInSet[]; 
    
    /**
     * Constructs this set and its instance variables. Each block 
     * in this set is allocated to a number of blocks and referenced 
     * by i.
     * 
     * @param numBlocks
     */
    public set(int numBlocks){
        this.numBlocks = numBlocks;
        this.blocksInSet = new block[this.numBlocks];
    	for(int i = 0; i < blocksInSet.length; i++){
    		this.blocksInSet[i] = new block(i);  
    	}
    }
    
    /**
    * Searches all blocks in this set to find a tag. Once it has been 
    * determined whether or not there has been a hit, the priorities
    * of each block in this set are changed. If there has been a hit, 
    * return true, if not, return false. 
    * 
    * The following block LRU priority algorithm is used in this 
    * method. Each priority corresponds to a unique integer value 
    * that is assigned to each block in this set. The priorities 
    * are manipulated based on the status of hit and if all
    * blocks are valid. Blocks with the 
    * 		0 -> Highest priority
    * 		.
    * 		.
    * 		.
    * (#Blocks - 1) -> Lowest priority
    * 
    * @param tag
    */
    public boolean searchBlocksInSet(long tag){
        boolean hit = false;
        /*
         * Determine if there is a hit in this set.
         */
        int blockIndex = 0;
        for(int i = 0; i < numBlocks; i++){
            hit = blocksInSet[i].compareTag(tag);
            if(hit == true){
                blockIndex = i;
                break;
            }
        }
        /*
         * If there was a hit, execute code and return true.
         */
        if(hit == true){
            boolean valid = true; // Assume all blocks in this set are valid.
            int validNum = numBlocks; //Initializes to the number of blocks that are valid.
            /*
             * Loop to test whether all blocks are valid or not.
             */
            for(int i = 0; i < numBlocks; i++){
                /*
                 * Set validNum to number of blocks that are valid if 
                 * there are some invalid blocks.
                 */
                if(this.blocksInSet[i].blockValid = false){
                    valid = false;
                    validNum = i;       
                    break;
                }
            }
            /* 
             * If a hit, and blockLRU already has lowest priority.
             * 
             * Do not change any of the priorities.
             */
            if(blocksInSet[blockIndex].blockLru == numBlocks - 1){  
                writeBlock(tag);
            }
            else {
                /*
                 * If a hit, and blockLRU has neither highest nor lowest priority.
                 * 
                 * Change block that is hit to the lowest priority and decrement all
                 * other priorities down to the previous priority of that block. 
                 */
                if(blocksInSet[blockIndex].blockLru != 0){                    
                    int x = blocksInSet[blockIndex].blockLru;
                    blocksInSet[blockIndex].blockLru = numBlocks - 1;
                    for(int i = 0; i < validNum; i++){
                        if(blocksInSet[i].blockLru > x && i != blockIndex){
                            blocksInSet[i].blockLru -= 1;
                        }
                    }
                }
                /*
                 * If a hit, and blockLRU has highest priority.
                 * 
                 * Increment each priority.
                 */
                else{
                    for(int j = 0; j < validNum; j++){
                        blocksInSet[j].blockLru = (blocksInSet[j].blockLru + (numBlocks-1)) % numBlocks;
                    }
                }
                writeBlock(tag);
            }
            return true;
        }
        /*
         * If there was a miss, execute this code and return false.
         */
        else{
            for(int j = 0; j < numBlocks; j++){
                blocksInSet[j].blockLru = (blocksInSet[j].blockLru + (numBlocks-1)) % numBlocks;
            }
            writeBlock(tag);
            return false;
        }
    }
    
    /**
     * Writes a tag of referenced block in this set.
     * 
     * @param tag
     */
    public void writeBlock(long tag){
    	/*
    	 * Sets the tag in the block that has the lowest priority.
    	 */
        for(int i = 0; i < numBlocks; i++){
            if(blocksInSet[i].blockLru == numBlocks - 1){
                blocksInSet[i].setTag(tag);  
                break;
            }
        }
    }
}

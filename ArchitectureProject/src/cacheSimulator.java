/**
 * @Class cacheSimulator
 * @File cacheSimulator.java
 * @Author Samuel Caguana and Vincent Giannone
 * Date last Modified: 3/28/2013
 * @Version 2.1
 * 
 * Read Me: The purpose of this class is to provide the functionality and data
 * associated with a cacheSimulator. This file also includes main().
 * 
 */

import java.util.ArrayList;
import java.math.*;

public class cacheSimulator {
	
	/** 
	 * Instance Variables 
	 * 	accessCount -> Counts the number of addresses accessed in this 
	 *  cacheSimulator
	 * 	hitCount -> Counts the number of address hits in this cacheSimulator
	 *  cache -> A pointer to a cache object to be simulated
	 */
    private float accessCount = 0, hitCount = 0;
    private cache cache;
    
    /**
     * Constructs this cacheSimulator and its instance variables. 
     * 
     * A cache object is instantiated at first, passing parameters that will be used to 
     * construct its instance variables. Using the array list of addresses passed in this
     * method, each address is converted from its hexadecimal string data type, to a 
     * binary string. Each of these addresses are accessed in memory till check is equal to 
     * "null"
     * 
     * A count of the number of memory accesses and hits are changed at the end of each access 
     * in memory. If there was a hit, hitCount is incremented. accessCount is incremented 
     * each iteration. addNum is simply the index of address.
     * 
     * @param blockSize
     * @param cacheSize
     * @param setAssoc
     * @param addArr
     */
    public cacheSimulator(int blockSize, int cacheSize, int setAssoc, ArrayList<String> addArr){
        cache = new cache(blockSize, cacheSize, setAssoc);
        int addNum = 0;
        String address = "";
        String check;
        do{ 
        	check = addArr.get(addNum);
            if(!check.equals("null")){
            	address = Integer.toBinaryString((int)Long.parseLong(check, 16));
	        	if(cache.accessMemory(address)){
                 	hitCount++;
                 }
                 accessCount++;
            }
            addNum++;	
        }
        while(check != "null");
    }
    
    /**
     * This method drives the simulation of caches. It is a place where the original, defining
     * parameters of each cache are passed to cacheSimulator. It also allows the user to view 
     * the outcome of each simulation, printing the miss rate. In total, due to the 
     * constraints of this assignment, there are 48 variations of the cacheSimulators. 
     */
    public static void main(String[] args){
    	/*
    	 * This block of code defines all of the necessary data and objects that 
    	 * will be used throughout this program. The 48 variations of cacheSimulator
    	 * is derived from the number of unique subsets that can formed from the blockSize,
    	 * cacheSize and setAssoc sets of data. 
    	 * 
    	 * A file is taken from input by the user, and an array list of addresses is created.  
    	 */
        fileInput addIn = new fileInput();
        ArrayList<String> addArr = addIn.getAddArray();
        int blockSize[] = {8, 16, 32};
        int cacheSize[] = {1024, 2048, 4096, 8192};
        int setAssoc[] = {1, 2, 4, 8};
        cacheSimulator cacheSimArray[] = new cacheSimulator[48];
        /*
    	 * This block of code initializes and drives all 48 variations of cacheSimulator.
    	 * Each cacheSimultor is passed a blockSize, cacheSize, setAssoc and the an
    	 * array list of addresses. With this data, the program has the necessary data to 
    	 * perform its analysis of the miss rate. 
    	 */
        int cacheNum = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    try{
                    	cacheSimArray[cacheNum++] = new cacheSimulator(blockSize[i], cacheSize[j], setAssoc[k], addArr);
                    	System.out.println("Created cache: " + cacheNum);
                    } catch(Exception e){
                        System.out.println("Error in creating cache!");
                    }
                }
            }
        }
        /*
    	 * This block of code is executed after all 48 caches have been simulated. The loop iterates three times,
    	 * printing each table similar to what was shown in the project description file. Each iteration
    	 * is based on the block size of the cache. Each row of that iteration will print the miss rates for the 
    	 * respective set associativity of each cache size. 
    	 */
        for(int i = 0; i < 3; i++){
            System.out.println("\n");
            System.out.println("Miss rates for a " + blockSize[i] + "-Byte block");
            System.out.println("\t1024   2048   4096   8192");
            System.out.println("direct: " + new BigDecimal(((cacheSimArray[0 + i * 16].accessCount - cacheSimArray[0 + i * 16].hitCount)/cacheSimArray[0 + i * 16].accessCount) * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[4 + i * 16].accessCount - cacheSimArray[4 + i * 16].hitCount)/cacheSimArray[4 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[8 + i * 16].accessCount - cacheSimArray[8 + i * 16].hitCount)/cacheSimArray[8 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[12 + i * 16].accessCount - cacheSimArray[12 + i * 16].hitCount)/cacheSimArray[12 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
            System.out.println("2-way:  " + new BigDecimal((cacheSimArray[1 + i * 16].accessCount - cacheSimArray[1 + i * 16].hitCount)/cacheSimArray[1 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[5 + i * 16].accessCount - cacheSimArray[5 + i * 16].hitCount)/cacheSimArray[5 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[9 + i * 16].accessCount - cacheSimArray[9 + i * 16].hitCount)/cacheSimArray[9 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[13 + i * 16].accessCount - cacheSimArray[13 + i * 16].hitCount)/cacheSimArray[13 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
            System.out.println("4-way:  " + new BigDecimal(((cacheSimArray[2 + i * 16].accessCount - cacheSimArray[2 + i * 16].hitCount)/cacheSimArray[2 + i * 16].accessCount) * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[6 + i * 16].accessCount - cacheSimArray[6 + i * 16].hitCount)/cacheSimArray[6 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[10 + i * 16].accessCount - cacheSimArray[10 + i * 16].hitCount)/cacheSimArray[10 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[14 + i * 16].accessCount - cacheSimArray[14 + i * 16].hitCount)/cacheSimArray[14 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
            System.out.println("8-way:  " + new BigDecimal((cacheSimArray[3 + i * 16].accessCount - cacheSimArray[3 + i * 16].hitCount)/cacheSimArray[3 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[7 + i * 16].accessCount - cacheSimArray[7 + i * 16].hitCount)/cacheSimArray[7 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[11 + i * 16].accessCount - cacheSimArray[11 + i * 16].hitCount)/cacheSimArray[11 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP)
                    + "  " + new BigDecimal((cacheSimArray[15 + i * 16].accessCount - cacheSimArray[15 + i * 16].hitCount)/cacheSimArray[15 + i * 16].accessCount * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }
}

/**
 * @Class fileInput
 * @File fileInput.java
 * @Author Samuel Caguana and Vincent Giannone
 * Date last Modified: 3/28/2013
 * @Version 1.0
 * 
 * Read Me: The purpose of this class is to provide the functionality and data
 * associated with fileInput.
 * 
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class fileInput {
    
	/** 
	 * Instance Variables 
	 * 	fileStream -> Creates a stream of a file.
	 * 	in -> Reads data from a file stream.
	 *  addArr -> Address read from in are placed into a list.
	 */
    private FileInputStream fileStream = null;
	private BufferedReader in = null;
    private ArrayList<String> addArr = new ArrayList<String>();
    
    /**
     * Establishes a file stream where address will be read from a
     * file until "null" is returned by getAddress()
     */
    public fileInput(){
    	try {
			fileStream = new FileInputStream(readFile());
		} catch (FileNotFoundException e) {
			System.out.print("Error when reading a file from user!");
			e.printStackTrace();
		}
    	String check = "";
        do{ 
            check = getAddress();
            addArr.add(check);
        }
        while(check != "null");
    }
    
    /**
     * Reads a file name from the user. 
     * 
     * @return file
     */
    public String readFile(){
        in = new BufferedReader(new InputStreamReader(System.in));
    	String file = "";
        try{
            System.out.print("Please Enter File Name: ");
            file = in.readLine();
            System.out.println();
        }catch(Exception e){
            System.out.println("Error from getFile():");
            System.out.println(e);
            System.exit(1);
        }
        
        return file;
    }
    
    /**
     * Gets all the addresses from a file, assuming the addresses are 32-bits
     * each.
     * 
     * @return wordAddress
     */
    public String getAddress(){
        String wordAddress = "";
        try{
        	/*
        	 * Read and store 8 valid characters. The characters read
        	 * start from where the pointer was left off from the loop
        	 * before.  
        	 */
        	for(int i = 0; i < 8; ){
        		int c = fileStream.read();
                if(c == -1){
                	return "null";
                }
        		if(c >= 48 && c <= 57){
        			wordAddress += (char)c;
        			i++;
        		}
        		if(c >= 65 && c <= 90){
        			wordAddress += (char)c;
        			i++;
        		}
        		if(c >= 97 && c <= 122){
        			wordAddress += (char)c;
        			i++;
        		}
        	}
        }catch(IOException e){
        	System.out.println("Error from getAddress():");
			System.out.println(e);
        }
        return wordAddress;
    }
    
    /**
     * Returns an Array List of addresses
     * 
     * @return addArr
     */
    public ArrayList<String> getAddArray(){
    	return addArr;
    }
}

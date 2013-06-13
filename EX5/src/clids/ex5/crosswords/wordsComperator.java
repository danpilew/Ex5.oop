/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;

import java.util.Comparator;

/**
 *
 * @author t7639496
 */
public class wordsComperator implements Comparator<String>{
private final int REVERSED_ORDER = -1;
    @Override
    public int compare(String term1, String term2) {
        int lengthCompering = REVERSED_ORDER * Integer.compare(term1.length(), term2.length());
        if(lengthCompering == 0)
            return (REVERSED_ORDER * term1.compareTo(term2));
        return lengthCompering;
    }
    
}

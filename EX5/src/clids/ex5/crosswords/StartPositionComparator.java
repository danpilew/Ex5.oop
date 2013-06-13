/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */  
package clids.ex5.crosswords;

import java.util.Comparator;

    
    
/**
 *
 * @author Administrator
 */
public class StartPositionComparator implements Comparator <StartPosition>{


    @Override
    public int compare(StartPosition o1, StartPosition o2) {
       int compareLengthResult = Integer.compare(o1.getLength(), o2.getLength());
       if(compareLengthResult != 0)
           return -(compareLengthResult);
       int x1 = o1.getPos().getX();
       return 0;

    }   
}

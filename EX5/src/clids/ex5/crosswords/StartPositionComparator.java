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
    // return the better startPos as smaller
    public int compare(StartPosition o1, StartPosition o2) {
       //by length
        int compareLengthResult = Integer.compare(o1.getLength(), o2.getLength());
       if(compareLengthResult != 0)
           return -(compareLengthResult);
      //by X
       int compareXResult = Integer.compare(o1.getX(), o2.getX());
      if(compareXResult != 0)
           return (compareXResult);
      //by Y
      int compareYResult = Integer.compare(o1.getY(), o2.getY());
      if(compareYResult != 0)
           return (compareYResult);
      // by vertical
      if (o1.isVertical())
          return -1;
      else
          return 1;
    }   
}

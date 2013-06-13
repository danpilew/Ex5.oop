/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;

import clids.ex5.search.BoardMove;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author t7639496
 */
public class MovesIterator<M extends BoardMove> implements Iterator<M>{
    private TreeSet<String> unused;
    private TreeSet<StartPosition> startPoints;
    private int i,j;
    public MovesIterator(TreeSet<String> unused,  TreeSet<StartPosition> startPoints){
        this.startPoints = startPoints;
        this.unused = unused;
        i = 0;
        j = -1;
    }

    @Override
    public boolean hasNext() {
        return(i == startPoints.size() );
    }

    @Override
    public M next() {
        j++;
        if(j == unused.size()){
            j = 0;
            i++;
        }
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

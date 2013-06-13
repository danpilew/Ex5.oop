/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;

import clids.ex5.search.BoardMove;
import clids.ex5.search.SearchBoard;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

/**
 *
 * @author t7639496
 */
public class MyCrossword implements Crossword{
private TreeSet<String> unusedWords;
private Square[][] board;
    public MyCrossword(){
        
    }

    @Override
    public void attachDictionary(CrosswordDictionary dictionary) {
        unusedWords = new TreeSet<String>(new wordsComperator());
    }

    @Override
    public void attachStructure(CrosswordStructure structure) {
        board = new Square[structure.getHeight()][structure.getWidth()];
        for(int i = 0; i < board.length ; i++){
            for(int j = 0; j < board[0].length; j++){  
                 board[i][j] = new Square(structure.getSlotType(i,j));
            }
        }
    }

    @Override
    public Collection<CrosswordEntry> getCrosswordEntries() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isBestSolution() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<CrosswordEntry> getMovesIterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getQuality() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getQualityBound() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doMove(CrosswordEntry move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void undoMove(CrosswordEntry move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SearchBoard<CrosswordEntry> getCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private class MovesIterator<M extends BoardMove> implements Iterator<M>{
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
        updateIJ();
       
        return null;
    }
    
    private boolean isWordFit(String word, StartPosition position){
        if(position.getLength() < word.length())
            return false;
        if((position.getPos().isVertical() ) )
        for(int i = 0; i < word.length(); i++){
          //  if(word.charAt(i) != position.getPos().);
        }
        
        return false;
    }
    private void updateIJ(){
         j++;
        if(j == unused.size()){
            j = 0;
            i++;
        }
    }
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

}

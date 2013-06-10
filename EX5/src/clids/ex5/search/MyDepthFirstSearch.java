/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.search;

import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author t7639496
 */
public class MyDepthFirstSearch<B extends SearchBoard<M>, M extends BoardMove> implements DepthFirstSearch<B, M>{

private long endTime;
final private long DELAY;
private B bestBoard;
private int bestQuality;
private boolean reachedBestSolution;

    public MyDepthFirstSearch() {
        endTime = 0;
        DELAY = 60;
        bestBoard = null;
        bestQuality = 0;
        reachedBestSolution = false;
    }

    @Override
    public B performSearch(B startBoard, int maxDept, long timeOut) {
        endTime = System.currentTimeMillis() + timeOut - DELAY;
        updateBest(startBoard);
        recSearch(startBoard, maxDept);
        return bestBoard;
    }
    
    private void recSearch(B currentBoard , int depthToEnd){
                                                       //Conditions to stop search     
        if(System.currentTimeMillis() >= endTime ||  //if time passed
           depthToEnd == -1 ||                       //if depth passed
           currentBoard.getQualityBound() < bestQuality || // if bad branch.
           reachedBestSolution )                      //if another branch reached the best solution
            return;
        //if reached new bestQuality node
        if(currentBoard.getQuality() > bestQuality){
            updateBest(currentBoard);
            //if reached the best node possible, 'notice' the other nodes.
            if(currentBoard.isBestSolution()){
                reachedBestSolution = true;
                return;
            }
        }
        //change the depth for calling next levels
        depthToEnd--;
        Iterator<M> movesIterator = currentBoard.getMovesIterator();
        M currentMove;
        //Iterate over the next possible moves, and call them recursivly 
        while(movesIterator.hasNext()){
            currentMove = movesIterator.next();
            currentBoard.doMove(currentMove);
            recSearch(currentBoard, depthToEnd);
            currentBoard.undoMove(currentMove);
        }
    }

    private void updateBest(B currentBoard) {
        bestQuality = currentBoard.getQuality();
        //Check it with Chinn
        bestBoard = (B)currentBoard.getCopy();
    }
    
    
    
}

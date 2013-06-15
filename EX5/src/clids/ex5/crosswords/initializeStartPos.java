/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;

import java.util.LinkedList;
import java.util.TreeSet;

/**
 *
 * @author Administrator
 */
public class initializeStartPos {

    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    public static TreeSet<StartPosition> initialStartPos(Square[][] board) {
        TreeSet<StartPosition> tree = new TreeSet<>(new StartPositionComparator());

        for (int i = 0; i < board.length; i++) {
            LinkedList<StartPosition> arrStPoInLine = startPointsOfLineOrCol(i, HORIZONTAL, board);            
            tree.addAll(arrStPoInLine);
        }
        for (int j = 0; j < board[0].length; j++) {
            LinkedList<StartPosition> arrStPoInCol = startPointsOfLineOrCol(j, VERTICAL,board);
            tree.addAll(arrStPoInCol);
        }
        return tree;
    }

    private static LinkedList<StartPosition> startPointsOfLineOrCol(int lineOrCol, boolean isVertical, Square[][] board) {
        LinkedList<StartPosition> stPoList = new LinkedList<>();
        int firstInd = 0;
        int secInd;
        //CHECK IT
        try {
            //finish when firstInd ot secInd will be bigger than the array dimention 
            while (true) {
                // while there are frame slots
                if (isVertical) {
                    while (board[firstInd][lineOrCol].getOverRides() == -1) {
                        firstInd++;
                    }
                    secInd = firstInd;
                    while (board[secInd][lineOrCol].getOverRides() != -1) {
                        secInd++;
                    }
                    stPoList.add(new StartPosition(secInd - firstInd, HORIZONTAL, firstInd, lineOrCol));
                    firstInd = secInd;
                } else {
                    while (board[lineOrCol][firstInd].getOverRides() == -1) {
                        firstInd++;
                    }
                    secInd = firstInd;
                    while (board[lineOrCol][secInd].getOverRides() != -1) {
                        secInd++;
                    }
                    stPoList.add(new StartPosition(secInd - firstInd, HORIZONTAL, lineOrCol, firstInd));
                    firstInd = secInd;
                }
            }
        } finally {
            return stPoList;
        }
    }
}

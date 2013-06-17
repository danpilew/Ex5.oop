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
    private static final boolean FIRSTINDEX = true;
    private static final boolean SECONDINDEX = true;

    public static TreeSet<StartPosition> initialStartPos(Square[][] board) {
        TreeSet<StartPosition> tree = new TreeSet<>(new StartPositionComparator());

        for (int i = 0; i < board.length; i++) {
            LinkedList<StartPosition> arrStPoInLine = startPointsOfLineOrCol(i, HORIZONTAL, board);
            tree.addAll(arrStPoInLine);
        }
        for (int j = 0; j < board[0].length; j++) {
            LinkedList<StartPosition> arrStPoInCol = startPointsOfLineOrCol(j, VERTICAL, board);
            tree.addAll(arrStPoInCol);
        }
        return tree;
    }

    private static LinkedList<StartPosition> startPointsOfLineOrCol(int lineOrCol, boolean isVertical, Square[][] board) {
        LinkedList<StartPosition> stPoList = new LinkedList<>();
        int firstInd = 0;
        int secInd = 0;
        boolean outOFBoundInIndex = false;
        try {
            //finish when firstInd or secInd will be bigger than the array dimention 
            while (true) {
                // while there are frame slots
                if (isVertical) {
                    outOFBoundInIndex = FIRSTINDEX;
                    while (board[firstInd][lineOrCol].getOverRides() == -1) {
                        firstInd++;
                    }
                    secInd = firstInd;
                    outOFBoundInIndex = SECONDINDEX;
                    while (board[secInd][lineOrCol].getOverRides() != -1) {
                        secInd++;
                    }
                    stPoList.add(new StartPosition(secInd - firstInd, VERTICAL, firstInd, lineOrCol)); // CHECK +1
                    firstInd = secInd;
                } else {
                    outOFBoundInIndex = FIRSTINDEX;
                    while (board[lineOrCol][firstInd].getOverRides() == -1) {
                        firstInd++;
                    }
                    secInd = firstInd;
                    outOFBoundInIndex = SECONDINDEX;
                    while (board[lineOrCol][secInd].getOverRides() != -1) {
                        secInd++;
                    }
                    stPoList.add(new StartPosition(secInd - firstInd, HORIZONTAL, lineOrCol, firstInd));// CHECK +1
                    firstInd = secInd;
                }
            }
        } finally {
            if (outOFBoundInIndex == SECONDINDEX) {
                if (isVertical) {
                    stPoList.add(new StartPosition(secInd - firstInd, VERTICAL, firstInd, lineOrCol));
                } else {
                    stPoList.add(new StartPosition(secInd - firstInd, HORIZONTAL, lineOrCol, firstInd));
                }
            }
                    return stPoList;
        }
    }
}

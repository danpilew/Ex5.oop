/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;
/**
 *
 * @author Administrator
 */
public class StartPosition implements CrosswordPosition{
    
    private int length;
    private boolean isVertical;
    int X;
    int Y;
      
    public StartPosition(int length, boolean isVertical, int X, int Y){
        this.X = X;
        this.Y = Y;
        this.isVertical = isVertical;
        this.length = length;
    }
    
    public StartPosition getNext(){
        int newLength = length - 1;
        int newX = X;
        int newY = Y;
        if (isVertical)
            newY++;
        else
            newX++;
        return new StartPosition(newLength, isVertical, newX, newY);
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public boolean isVertical() {
        return isVertical;
    }
}

   
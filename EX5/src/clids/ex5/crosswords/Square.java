/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clids.ex5.crosswords;

/**
 *
 * @author t7639496
 */
public class Square {
    private int overRides;
    private boolean startOfVertical;
    private boolean startOfHorizontal;
    private char letter;
    public static final char DEFAULT_CHAR = '@';
    
    public Square(CrosswordStructure.SlotType type){
        this(0,DEFAULT_CHAR,false,false);
        switch(type){
            case FRAME_SLOT:
                setOverRides(-1);
                break;
            case UNUSED_SLOT:
                setOverRides(0);
                break;
        }
    }
    
    public Square(int overRides, char letter, boolean startOfHorizontal, boolean startOfVertical){
        this.overRides = overRides;
        this.letter = letter;
        this.startOfHorizontal = startOfHorizontal;
        this.startOfVertical = startOfVertical;
    }

    /**
     * @return the overRides
     */
    public int getOverRides() {
        return overRides;
    }

    /**
     * @param overRides the overRides to set
     */
    public void setOverRides(int overRides) {
        this.overRides = overRides;
    }

    /**
     * @return the startOfVertical
     */
    public boolean isStartOfVertical() {
        return startOfVertical;
    }

    /**
     * @param startOfVertical the startOfVertical to set
     */
    public void setStartOfVertical(boolean startOfVertical) {
        this.startOfVertical = startOfVertical;
    }

    /**
     * @return the startOfHorizontal
     */
    public boolean isStartOfHorizontal() {
        return startOfHorizontal;
    }

    /**
     * @param startOfHorizontal the startOfHorizontal to set
     */
    public void setStartOfHorizontal(boolean startOfHorizontal) {
        this.startOfHorizontal = startOfHorizontal;
    }

    /**
     * @return the letter
     */
    public char getLetter() {
        return letter;
    }

    /**
     * @param letter the letter to set
     */
    public void setLetter(char letter) {
        this.letter = letter;
    }
    
    
}
